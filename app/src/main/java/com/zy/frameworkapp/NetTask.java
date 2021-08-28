package com.zy.frameworkapp;

import com.zy.common.utils.NetUtil;
import com.zy.frameworkapp.schedule.PlanResultCallback;
import com.zy.frameworkapp.schedule.RealTask;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.frameworkapp
 * @ClassName: NetTask
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/26 8:31
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/26 8:31
 * @UpdateRemark:
 * @Version: 1.0
 */
public class NetTask extends RealTask {
    @Override
    protected <String> void doFinalTask(PlanResultCallback<String> callback) {
        boolean networkAvailable = NetUtil.isNetworkAvailable(MyApplication.getContext());
        if (networkAvailable){
//            super.cancelTimer();
//            callback.Success((String) "有网");
//            callback.Completed();
            callback.Failed(new IllegalStateException("没网"));
        }else{
            callback.Failed(new IllegalStateException("没网"));
        }
    }
}
