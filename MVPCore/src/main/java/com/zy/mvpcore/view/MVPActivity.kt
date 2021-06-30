package com.zy.mvpcore.view

import com.zy.mvpcore.BasePresenter

/**
 *@author:zhangyue
 *@date:2021/6/29
 */
abstract class MVPActivity<P:BasePresenter<*,*>>: BaseActivity() {
    protected lateinit var mPresenter: P
    init {
        mPresenter= createPresenter()
    }

    abstract fun createPresenter(): P
}