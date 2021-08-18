package com.zy.frameworkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zy.msgbus.MsgBus
import com.zy.msgbus.Subscribe
import com.zy.msgbus.ThreadMode
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_main_Test.setOnClickListener {
            startService(Intent(this@MainActivity,TestService::class.java))
        }
        MsgBus.getInstance().default.register(this)
    }

    @Subscribe(threadMode = ThreadMode.Main)
    fun getMsg(event1: MsgEvent1){
        Log.d("123", "getMsg: thread:${Thread.currentThread().name} msg:${event1.msg}")
    }

    @Subscribe(threadMode = ThreadMode.Async)
    fun getMsg2(event2:MsgEvent1){
        Log.d("123", "getMsg2: thread:${Thread.currentThread().name} msg:${event2.msg}")
    }

    override fun onStop() {
        super.onStop()
        MsgBus.getInstance().default.unregister(this)
    }
}