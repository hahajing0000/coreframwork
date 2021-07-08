package com.zy.mvpcore.view

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.zy.common.StatusBarUtils

/**
 *@author:zhangyue
 *@date:2021/6/29
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (hideStatusBar()){
            StatusBarUtils.initBar(this)
        }
        setContentView(getLayoutId())

        initData()
        initEvent()

    }

    /**
     * 初始化页面加载数据
     */
    abstract fun initData()

    /**
     * 是否异常状态类实现沉浸式体验
     */
    protected open fun hideStatusBar():Boolean{
        return true
    }

    /**
     * 初始化事件
     */
    abstract fun initEvent()

    /**
     * 获取布局资源id
     */
    abstract fun getLayoutId(): Int

    /**
     * 查找资源
     */
    fun <T : View> Find(id:Int):T{
        return findViewById<T>(id)
    }

    /**
     * 显示消息提示
     */
    fun showMsg(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }


}