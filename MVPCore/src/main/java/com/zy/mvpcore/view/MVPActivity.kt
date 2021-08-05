package com.zy.mvpcore.view

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.zy.common.InjectUtils
import com.zy.mvpcore.annotation.Presenter

/**
 *@author:zhangyue
 *@date:2021/6/29
 */
abstract class MVPActivity: BaseActivity() {
    init {
        injectP()
    }

    /**
     * 解析绑定P层 支持一个V对应多个P层逻辑
     */
    private fun injectP() {
        InjectUtils.injectP(this)
    }



}