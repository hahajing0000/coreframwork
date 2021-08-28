package com.zy.frameworkapp.schedule;

import android.util.ArrayMap;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.frameworkapp.schedule
 * @ClassName: ITask
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/26 7:57
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/26 7:57
 * @UpdateRemark:
 * @Version: 1.0
 */
public interface ITask {
    /**
     * 执行任务
     * @param callback 回调结果
     * @param <T>
     */
    <T> void doTask(PlanResultCallback<T> callback);

}
