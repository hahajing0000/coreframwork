package com.zy.common

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

/**
 *@author:zhangyue
 *@date:2021/6/30
 */
object StatusBarUtils {
    /**
     * 隐藏状态栏完成沉浸式布局体验
     */
    fun initBar(context:AppCompatActivity) {
        context.window.requestFeature(Window.FEATURE_NO_TITLE) //取消状态栏的标题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //判断SDK的版本是否>=21
            val window: Window = context.window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION) //允许页面可以拉伸到顶部状态栏并且定义顶部状态栏透名
            window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or  //设置全屏显示
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            )
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(Color.TRANSPARENT) //设置状态栏为透明
            window.setNavigationBarColor(Color.TRANSPARENT) //设置虚拟键为透明
        }
        val actionBar: ActionBar? = context.supportActionBar
        actionBar?.hide() //将actionBar隐藏
    }
}