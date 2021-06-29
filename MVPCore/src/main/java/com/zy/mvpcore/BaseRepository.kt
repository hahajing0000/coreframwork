package com.zy.mvpcore

/**
 *@author:zhangyue
 *@date:2021/6/29
 */
abstract class BaseRepository<T:IModle> {
    protected lateinit var mModle:T
    init {
        mModle=createModle()
    }

    abstract fun createModle(): T
}