package com.zy.mvvmcore.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;

import com.zy.mvvmcore.R;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.mvvmcore.utils
 * @ClassName: StatusBarCompat
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/9/29 10:27
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/9/29 10:27
 * @UpdateRemark:
 * @Version: 1.0
 */
public class StatusBarCompat {
    private static final int INVALID_VAL = -1;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static View compat(Activity activity, int statusColor) {
//        int color = ContextCompat.getColor(activity, statusColor);
        int color=statusColor;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (statusColor != INVALID_VAL) {
                color = statusColor;
            }
            activity.getWindow().setStatusBarColor(color);
            return null;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
            if (statusColor != INVALID_VAL) {
                color = statusColor;
            }
            View statusBarView = contentView.getChildAt(0);
            if (statusBarView != null && statusBarView.getMeasuredHeight() == getStatusBarHeight(activity)) {
                statusBarView.setBackgroundColor(color);
                return statusBarView;
            }
            statusBarView = new View(activity);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(activity));
            statusBarView.setBackgroundColor(color);
            contentView.addView(statusBarView, lp);
            return statusBarView;
        }
        return null;

    }

    public static void compat(Activity activity) {
        compat(activity, INVALID_VAL);
    }


    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
