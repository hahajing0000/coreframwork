package com.zy.frameworkapp.schedule;


import android.os.Handler;
import android.os.Looper;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.frameworkapp.schedule
 * @ClassName: RealTask
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/26 8:16
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/26 8:16
 * @UpdateRemark:
 * @Version: 1.0
 */
public abstract class RealTask implements ITask {
    private TaskState taskState;
    /**
     * 任务执行周期间隔
     */
    private int mInterval;
    /**
     * 总执行次数
     */
    private int times;

    public TaskState getTaskState() {
        return taskState;
    }

    public void setTaskState(TaskState taskState) {
        this.taskState = taskState;
    }

    public int getInterval() {
        return mInterval;
    }

    public void setInterval(int mInterval) {
        this.mInterval = mInterval;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    /**
     * 当前任务执行次数
     */
    private int currentTime=0;
    private PlanResultCallback mCallback;
    @Override
    public <T> void doTask(final PlanResultCallback<T> callback) {
        synchronized (RealTask.class){
            if (getTaskState()==TaskState.Ready){
                this.mCallback=callback;
                setTaskState(TaskState.Running);
                final int interval = getInterval();

                if (timer!=null){
                    timer.schedule(timerTask,interval,getTimes());
                }


            }
        }

    }

    Timer timer=new Timer();
    TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
            if (currentTime++<getTimes()){
                doFinalTask(mCallback);

            }
        }
    };

    protected void cancelTimer(){
        if (timer!=null){
            timer.cancel();
            timerTask.cancel();
            timer=null;
            timerTask=null;
        }
    }

    /**
     * 交给子类实现真实的任务
     * @param callback
     * @param <T>
     */
    protected abstract  <T> void doFinalTask(PlanResultCallback<T> callback);
}
