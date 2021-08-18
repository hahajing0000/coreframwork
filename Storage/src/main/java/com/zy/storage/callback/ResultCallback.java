package com.zy.storage.callback;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.storage.callback
 * @ClassName: ResultCallback
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/18 11:22
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/18 11:22
 * @UpdateRemark:
 * @Version: 1.0
 */
public interface ResultCallback<T> {
    void Sucess(T t);
}
