package com.zy.mvpcore.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
        init();
        initData()
        initEvent()

    }

    override fun onStop() {
        super.onStop()
        releaseResource()
    }

    /**
     * 资源释放
     */
    abstract fun releaseResource()

    /**
     * 资源初始化
     */
    abstract fun init()

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
     * 显示短消息提示
     */
    fun showShortMsg(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

    /**
     * 显示长消息提示
     */
    fun showLongMsg(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }

    /**
     * 跳转到目标Activity 无参数
     */
    fun jumpActivity(target:Class<*>){
        startActivity(Intent(this@BaseActivity,target))
    }

    /**
     * 跳转到目标Activity 有参数 参数key固定为params
     */
    fun jumpActivity(target: Class<*>,params:Bundle?){
        val intent:Intent= Intent(this@BaseActivity,target)
        intent.putExtra("params",params)
        startActivity(intent)
    }

    /**
     * 跳转到目标Activity 并接收返回结果
     */
    fun jumpActivityForResult(target: Class<*>,requestCode:Int=0){
        startActivityForResult(Intent(this@BaseActivity,target),requestCode)
    }

    /**
     * 跳转到目标Activity 有参数 并接收返回结果
      */
    fun jumpActivityForResult(target: Class<*>,params:Bundle?,requestCode:Int=0){
        val intent:Intent= Intent(this@BaseActivity,target)
        intent.putExtra("params",params)
        startActivityForResult(intent,requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        getReulstForActivity(requestCode,resultCode,data)
    }

    /**
     * 获取Activity返回的结果数据
     */
    abstract fun getReulstForActivity(requestCode: Int, resultCode: Int, data: Intent?)
}