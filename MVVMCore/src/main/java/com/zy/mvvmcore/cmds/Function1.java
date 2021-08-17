package com.zy.mvvmcore.cmds;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.mvvmcore.cmds
 * @ClassName: Function1
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/17 10:06
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/17 10:06
 * @UpdateRemark:
 * @Version: 1.0
 */
public interface Function1<R,T> {
    R Execute(T param);
}
