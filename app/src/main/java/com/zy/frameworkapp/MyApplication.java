package com.zy.frameworkapp;

import android.app.Application;

import com.zy.apt_router_annotation.ZRoute;
import com.zy.zrouter.ZRouter;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.frameworkapp
 * @ClassName: MyApplication
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/19 17:53
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/19 17:53
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ZRouter.getInstance().init(this);
    }
}
