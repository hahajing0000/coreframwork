package com.zy.frameworkapp.schedule;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.frameworkapp.schedule
 * @ClassName: PlanResultCallback
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/26 7:55
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/26 7:55
 * @UpdateRemark:
 * @Version: 1.0
 */
public interface PlanResultCallback<T> {
    /**
     * 成功
     * @param result
     */
    void Success(T result);

    /**
     * 失败
     * @param error
     */
    void Failed(Throwable error);

    /**
     * 完成
     */
    void Completed();
}
