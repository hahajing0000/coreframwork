package com.zy.msgbus;

import android.os.Handler;
import android.os.Looper;

import androidx.collection.ArrayMap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.msgbus
 * @ClassName: MsyBusImpl
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/5 14:50
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/5 14:50
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MsgBusImpl {
    /**
     * key 订阅者的class名称，value订阅者的封装结构
     */
    private ArrayMap<String,SubscriberState> subscribers=new ArrayMap<>();

    /**
     * key 订阅者中方法参数的类型名称，value订阅者封装结构集合
     */
    private ArrayMap<String,List<SubscriberMethodState>> paramsSubscriberMap=new ArrayMap<>();

    /**
     * 注册订阅者
     * @return 订阅结果
     * @author zhangyue
     * @time 2021/8/5 15:01
     */ 
    public Boolean register(Object subscriber){
        String key=subscriber.getClass().getName();
        if (subscribers.containsKey(key)){
            return false;
        }
        List<SubscriberMethodState> methodStates=getMethodStateFormClass(subscriber);
        if (methodStates==null||methodStates.size()==0){
            throw new IllegalStateException("no have @Subscriber and public method");
        }
        SubscriberState subscriberState = createSubscriberState(key, subscriber, methodStates);
        subscribers.put(key,subscriberState);

        return true;
    }


    /**
     * 发布消息
     * @param data
     * @param <T>
     * @return
     */
    public <T> Boolean post(final T data){
        List<SubscriberMethodState> subscribers = paramsSubscriberMap.get(data.getClass().getName());
        if (subscribers==null||subscribers.size()==0){
            return false;
        }
        for (final SubscriberMethodState subscriber:subscribers){

            /**
             * 处理线程切换
             */
            switch (subscriber.getThreadMode()){
                    case Main:
                        doMain(new Runnable() {
                            @Override
                            public void run() {
                               execMethod(subscriber.getMethod(),subscriber.getOwner(),data);
                            }
                        });
                        break;
                    case Default:
                        execMethod(subscriber.getMethod(),subscriber.getOwner(),data);
                        break;
                    case Async:
                        doAsync(new Runnable() {
                            @Override
                            public void run() {
                                execMethod(subscriber.getMethod(),subscriber.getOwner(),data);
                            }
                        });
                        break;
                    case Background:
                        if (isMainThread()){
                            doAsync(new Runnable() {
                                @Override
                                public void run() {
                                    execMethod(subscriber.getMethod(),subscriber.getOwner(),data);
                                }
                            });
                        }else{
                            execMethod(subscriber.getMethod(),subscriber.getOwner(),data);
                        }
                        break;
                    default:
                        execMethod(subscriber.getMethod(),subscriber.getOwner(),data);
                        break;
                }
        }
        return false;
    }

    /**
     * 注销订阅
     * @param subscriber
     * @return
     */
    public Boolean unregister(Object subscriber){
        String key=subscriber.getClass().getName();
        if (subscribers.containsKey(key)){
            subscribers.remove(key);

        }
        removeFromParamsSubscribeMap(subscriber);
        return true;
    }

    /**
     * 设置线程池
     * @param executorService
     */
    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    /**
     * 删除存在的订阅者
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/6 8:53
     */ 
    private void removeFromParamsSubscribeMap(Object subscriber) {
        Set<Map.Entry<String, List<SubscriberMethodState>>> entries = paramsSubscriberMap.entrySet();
        for (Map.Entry<String,List<SubscriberMethodState>> entry:entries){
            SubscriberMethodState tempState=getSubscriberMethodStateBySubscriber(subscriber,entry.getValue());
            if (entry.getValue().contains(tempState)){
                entry.getValue().remove(tempState);
            }
        }
    }

    /**
     * 从集合中根据订阅者获取对应的SubscriberMethodState实体
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/8/6 9:58
     */ 
    private SubscriberMethodState getSubscriberMethodStateBySubscriber(Object subscriber, List<SubscriberMethodState> value) {
        if (value==null||value.size()==0){
            return null;
        }
        for (SubscriberMethodState state:value){
            if (state.getOwner()!=null&&state.getOwner().equals(subscriber)){
                return state;
            }
        }
        return null;
    }


    /**
     * 判断是否主线程
     * @param
     * @return 结果
     * @author zhangyue
     * @time 2021/8/5 15:04
     */
    private Boolean isMainThread(){
        if (Looper.getMainLooper().getThread()==Thread.currentThread()){
            return true;
        }
        return false;
    }

    /**
     * 线程池对象
     * @author zhangyue
     * @time 2021/8/5 15:09
     */ 
    private ExecutorService executorService;

    /**
     * 获取线程池
     * @return
     */
    private ExecutorService getExecutorService() {
        if (null==executorService){
            executorService=Executors.newCachedThreadPool();
        }
        return executorService;
    }

    /**
     * 线程池执行
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/5 15:08
     */ 
    private void doAsync(Runnable runnable){
        getExecutorService().submit(runnable);
    }
    
    private Handler mHandler=new Handler(Looper.getMainLooper());
    /**
     * 主线程执行
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/5 15:08
     */ 
    private void doMain(Runnable runnable){
        mHandler.post(runnable);
    }

    /**
     * 创建SubscriberState实例
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/5 18:09
     */
    private SubscriberState createSubscriberState(String key,Object subscriber,List<SubscriberMethodState> methodStates){
        SubscriberState subscriberState=new SubscriberState();
        subscriberState.methodStateList=methodStates;
        subscriberState.name=key;
        subscriberState.subscriber=subscriber;
        return subscriberState;
    }

    /**
     * 通过类型获取拥有@Subscriber并且访问修饰符为Public的方法
     * @param subscriber 订阅者
     * @return
     * @author zhangyue
     * @time 2021/8/5 18:11
     */
    private List<SubscriberMethodState> getMethodStateFormClass(Object subscriber) {
        Class<?> clazz=subscriber.getClass();
        List<SubscriberMethodState> subscriberMethodStates=null;
        Method[] methods = clazz.getMethods();
        if (methods!=null&&methods.length>0){
            subscriberMethodStates=new ArrayList<>();
            for (Method method:methods){
                Subscribe annotation = method.getAnnotation(Subscribe.class);
                //必须有@Subscribe注解
                if (null==annotation){
                    continue;
                }
                Class<?>[] parameters = method.getParameterTypes();
                //必须是一个参数
                if (parameters.length!=1){
                    continue;
                }
                //获取订阅者期待的线程模式
                ThreadMode threadMode = annotation.threadMode();
                String paramsTypeName=parameters[0].getName();
                SubscriberMethodState methodState=new SubscriberMethodState(threadMode,method.getName(),method,paramsTypeName,subscriber);
                subscriberMethodStates.add(methodState);
                putParamsSubscribeMap(paramsTypeName,methodState);
            }
        }

        return subscriberMethodStates;
    }

    /**
     * 向参数类型订阅者集合map中写入值
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/6 8:22
     */
    private Boolean putParamsSubscribeMap(String paramsTypeName, SubscriberMethodState subscriber) {
        return saveToParamsSubscribeMap(paramsTypeName,subscriber);
    }

    /**
     * 判断参数方法类型名称及订阅者是否已经存在
     * @param
     * @return true存在 false不存在
     * @author zhangyue
     * @time 2021/8/6 8:34
     */
    private boolean saveToParamsSubscribeMap(String paramsTypeName, SubscriberMethodState subscriber) {
        Set<Map.Entry<String, List<SubscriberMethodState>>> entries = paramsSubscriberMap.entrySet();
        for (Map.Entry<String,List<SubscriberMethodState>> entry:entries){
            if (entry.getKey().equals(paramsTypeName)&&entry.getValue().contains(subscriber)){//key存在并且list中已经包含该订阅者
                return true;
            }else if (entry.getKey().equals(paramsTypeName)){//key 存在的情况 但value的list中不包含该订阅者
                if (entry.getValue()==null){//如果value为null进行初始化后加入集合中
                    entry.setValue(new ArrayList<SubscriberMethodState>()).add(subscriber);
                }
                else{//集合不为空 直接加入
                    entry.getValue().add(subscriber);
                }
                return true;
            }
        }
        //key和value都不存在的情况
        List<SubscriberMethodState> tempSubscribers=new ArrayList<>();
        tempSubscribers.add(subscriber);
        paramsSubscriberMap.put(paramsTypeName,tempSubscribers);

        return false;
    }

    /**
     * 反射执行方法
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/6 10:11
     */
    private <T> void execMethod(Method method,Object obj,T data){
        try {
            method.invoke(obj,data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 订阅者
     */
    class SubscriberState{
        private String name;
        private Object subscriber;
        private List<SubscriberMethodState> methodStateList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getSubscriber() {
            return subscriber;
        }

        public void setSubscriber(Object subscriber) {
            this.subscriber = subscriber;
        }

        public List<SubscriberMethodState> getMethodStateList() {
            return methodStateList;
        }

        public void setMethodStateList(List<SubscriberMethodState> methodStateList) {
            this.methodStateList = methodStateList;
        }
    }

    /**
     * 订阅者中包含的@Subscriber方法
     */
    class SubscriberMethodState{
        //线程模型
        private ThreadMode threadMode;
        //方法名称
        private String methodName;
        //方法
        private Method method;
        //参数类型名称
        private String paramsTypeName;
        //订阅者
        private Object owner;

        public SubscriberMethodState(ThreadMode threadMode, String methodName, Method method, String paramsTypeName, Object owner) {
            this.threadMode = threadMode;
            this.methodName = methodName;
            this.method = method;
            this.paramsTypeName = paramsTypeName;
            this.owner = owner;
        }

        public String getParamsTypeName() {
            return paramsTypeName;
        }

        public void setParamsTypeName(String paramsTypeName) {
            this.paramsTypeName = paramsTypeName;
        }

        public ThreadMode getThreadMode() {
            return threadMode;
        }

        public void setThreadMode(ThreadMode threadMode) {
            this.threadMode = threadMode;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public Object getOwner() {
            return owner;
        }

        public void setOwner(Object owner) {
            this.owner = owner;
        }
    }

}
