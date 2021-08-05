package com.zy.mvpcore

import android.os.Build
import com.zy.common.InjectUtils
import com.zy.mvpcore.annotation.Model

/**
 *@author:zhangyue
 *@date:2021/6/29
 */
abstract class BaseRepository {
    init {
        initModel()
    }

    /**
     * 初始化Model 实现一个Repo对应多个Model
     * 使用注解结合反射来完成Model属性的获取及对应修饰Model类型的初始化
     */
    private fun initModel() {
        InjectUtils.injectM(this)
    }
}