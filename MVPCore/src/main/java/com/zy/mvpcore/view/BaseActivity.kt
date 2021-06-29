package com.zy.mvpcore.view

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 *@author:zhangyue
 *@date:2021/6/29
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(getLayoutId())
    }

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