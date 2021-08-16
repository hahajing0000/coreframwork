package com.zy.common.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executors;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.common.utils
 * @ClassName: ThreadUtils
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/16 16:51
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/16 16:51
 * @UpdateRemark:
 * @Version: 1.0
 */
public class ThreadUtils {
    public static void doTaskOnAsync(Runnable runnable){
        Executors.newCachedThreadPool().submit(runnable);
    }

    private static Handler mHandler=new Handler(Looper.getMainLooper());
    public static void doTaskOnMainThread(Runnable runnable){
        mHandler.post(runnable);
    }

    public static boolean isMainThread(){
        if (Looper.getMainLooper().getThread()==Thread.currentThread()){
            return true;
        }
        return false;
    }
}


