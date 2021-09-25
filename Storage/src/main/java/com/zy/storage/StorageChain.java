package com.zy.storage;

import com.zy.storage.callback.ResultCallback;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.storage
 * @ClassName: StorageChain
 * @Description:
 * 存储链节点的父类提供一些通用的默认实现
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/18 11:18
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/18 11:18
 * @UpdateRemark:
 * @Version: 1.0
 */
public abstract class StorageChain<T> {

    /**
     * 前一个节点
     */
    protected StorageChain previousChain;

    /**
     * 后一个节点
     */
    protected StorageChain nextChain;

    /**
     * 存储值
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/18 11:21
     */
    protected abstract void saveData(String key,T data);

    /**
     * 获取值
     * @param key
     * @param callback
     */
    protected abstract void getData(String key, ResultCallback<T> callback);

    /**
     * 按key删除具体的值
     * @param key
     */
    protected abstract void removeAtKey(String key);

    /**
     * 清空所有的数据
     */
    protected abstract void clear();

    /**
     * 设置下一个节点及设置下个节点的上一个节点是自己
     * @param nextChain
     */
    public void setNextChain(StorageChain nextChain){
        this.nextChain=nextChain;
        nextChain.previousChain=this;
    }

    public StorageChain getNextChain() {
        return nextChain;
    }
    
    /**
     * 判断是否有下级节点
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/8/19 9:16
     */ 
    public boolean hasNext(){
        if (nextChain!=null){
            return true;
        }
        return false;
    }

    /**
     * 存入值暴露给别人使用 已经适配了对应关系
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/18 15:25
     */
    public void putValue(String key,T value){
        //当前节点已经存储
        saveData(key,value);
        if (this.nextChain!=null){
            this.nextChain.putValue(key,value);
        }
    }

    /**
     * 取值暴露给别人使用 已经适配取的对应关系
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/18 15:30
     */
    public void getValue(final String key, final ResultCallback<T> callback){
        getData(key, new ResultCallback<T>() {
            @Override
            public void Success(T t) {
                //当前链节点没有取到对应数据则向下级节点获取
                if (null==t){
                    nextChain.getValue(key,callback);
                }
                //如果已经取到同步前面的节点
                else{
                    if (null!=previousChain){
                        previousChain.putValue(key,t);
                    }
                }

                callback.Success(t);
            }

            @Override
            public void Failed(Throwable throwable) {

            }
        });
    }

    /**
     * 根据key删除数据暴露给别人使用 已经适配取的对应关系
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/8/18 15:37
     */ 
    public void removeValue(String key){
        removeAtKey(key);
        if (this.nextChain!=null){
            this.nextChain.removeValue(key);
        }
    }

    /**
     * 清空数据
     */
    public void removeAll(){
        clear();
        if (this.nextChain!=null){
            this.nextChain.removeAll();
        }
    }

    /**
     * 获取首节点
     * @return
     */
    public StorageChain getFirstChain(){
        StorageChain own=this;
        while (own.previousChain!=null){
            own=previousChain;
        }
        return own;
    }

}
