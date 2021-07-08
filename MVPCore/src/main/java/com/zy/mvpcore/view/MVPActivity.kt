package com.zy.mvpcore.view

import android.os.Build
import androidx.annotation.RequiresApi
import com.zy.mvpcore.BasePresenter
import com.zy.mvpcore.annotation.PAnnotation

/**
 *@author:zhangyue
 *@date:2021/6/29
 */
@RequiresApi(Build.VERSION_CODES.P)
abstract class MVPActivity<P:BasePresenter<*,*>>: BaseActivity() {
    init {
        injectP()
    }

    /**
     * 解析绑定P层 支持一个V对应多个P层逻辑
     */
    @RequiresApi(Build.VERSION_CODES.P)
    private fun injectP() {
        val java = this::class.java
        val declaredFields = java.declaredFields
        if (declaredFields.size>0){
            for (field in declaredFields){
                val annotation = field.getAnnotation(PAnnotation::class.java)
                if (annotation!=null){
                    field.isAccessible=true


                    val name =field.genericType.typeName
                    val fieldClass = Class.forName(name)
                    val constructors = fieldClass.constructors
                    if (constructors.size>0){
                        val newInstance = constructors.get(0).newInstance(this)
                        field.set(this,newInstance)
                    }

                }
            }
        }
    }

}