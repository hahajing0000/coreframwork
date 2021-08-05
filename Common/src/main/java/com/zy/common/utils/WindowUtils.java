package com.zy.common.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.view.Surface;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.common.utils
 * @ClassName: WindowUtils
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/5 11:05
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/5 11:05
 * @UpdateRemark:
 * @Version: 1.0
 */
public final class WindowUtils {
    /**
     * Don't let anyone instantiate this class.
     */
    private WindowUtils() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * 获取当前窗口的旋转角度
     *
     * @param activity
     * @return
     */
    @TargetApi(Build.VERSION_CODES.FROYO)
    public static int getDisplayRotation(Activity activity) {
        switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;
            default:
                return 0;
        }
    }

    /**
     * 当前是否是横屏
     *
     * @param context
     * @return
     */
    public static boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * 当前是否是竖屏
     *
     * @param context
     * @return
     */
    public static boolean isPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }
}
