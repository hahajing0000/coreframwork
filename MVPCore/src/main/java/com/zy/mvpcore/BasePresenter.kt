package com.zy.mvpcore

import java.lang.ref.SoftReference

/**
 *@author:zhangyue
 *@date:2021/6/29
 */
abstract class BasePresenter<Repo : BaseRepository,V:IView> {
    protected lateinit var mView:SoftReference<V>
    protected lateinit var mRepo:Repo
    constructor(v:V){
        mView= SoftReference(v)
        mRepo=createRepository()
    }

    /**
     * 创建Repository
     */
    abstract fun createRepository(): Repo

    /**
     * 获取V层
     */
    fun getV(): V? {
        return mView.get()
    }
}