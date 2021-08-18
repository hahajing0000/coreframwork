package com.zy.zrouter;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.zrouter
 * @ClassName: RouterUtils
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/18 14:29
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/18 14:29
 * @UpdateRemark:
 * @Version: 1.0
 */
public class RouterUtils implements IRouter {
    @Override
    public void putActivity() {
        ZRouter.getInstance().put("",null);
    }
}
