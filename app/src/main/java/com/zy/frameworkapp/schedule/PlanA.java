package com.zy.frameworkapp.schedule;

import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

import java.util.Stack;
import java.util.concurrent.Executors;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.frameworkapp.schedule
 * @ClassName: PlanA
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/26 7:44
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/26 7:44
 * @UpdateRemark:
 * @Version: 1.0
 */
public class PlanA {
    private Stack<ScheduleEntity> times;
    private RealTask task;
    public PlanA(Stack<ScheduleEntity> _times,RealTask _task){
        this.times=_times;
        this.task=_task;
    }

    /**
     * 设置计划
     * @param times
     */
    public void setTimes(Stack<ScheduleEntity> times) {
        this.times = times;
    }

    /**
     * 执行任务
     */
    public void doTask(){
        Executors.newFixedThreadPool(4).submit(new Runnable() {
            @Override
            public void run() {
                doTask2();
            }
        });
    }

    private void doTask2(){
        if (times==null||times.size()==0){
            throw new IllegalArgumentException("计划任务栈为空，或者为Size为0");
        }
        flag:for (ScheduleEntity entity:times){
            if (task.getTaskState()==TaskState.Completed){
                break flag;
            }
            //时间间隔
            int interval = entity.getInterval();
            //执行次数
            int times = entity.getTimes();
            if (times>0){
                task.setTaskState(TaskState.Ready);
                while (task.getTaskState()!=TaskState.Completed){
                    task.setInterval(interval);
                    task.setTimes(times);
                    task.doTask(new PlanResultCallback<Object>() {
                        @Override
                        public void Success(Object result) {
                            Log.d("123", "Task is exec Success: ...");
                            task.setTaskState(TaskState.Completed);

                        }

                        @Override
                        public void Failed(Throwable error) {
                            Log.d("123", "Task is exec Failed: ...");
                        }

                        @Override
                        public void Completed() {
                            Log.d("123", "Task is exec Completed: ...");
                            task.setTaskState(TaskState.Completed);
                        }
                    });
                }
            }
        }

        //设置任务完成状态
        task.setTaskState(TaskState.Completed);
    }

    /**
     * 执行计划任务的Handler
     */
    private Handler timer=new Handler();
}
