package com.zy.common

import android.os.Build
import com.zy.mvpcore.annotation.Model
import com.zy.mvpcore.annotation.Presenter

/**
 *
 * @ProjectName: FrameworkApp
 * @Package: com.zy.common
 * @ClassName: InjectUtils
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/5 13:57
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/5 13:57
 * @UpdateRemark:
 * @Version: 1.0
 */
object InjectUtils {

    /**
     * 反射+注解 初始化P层
     */
    fun injectP(obj:Any) {
        val java = obj.javaClass
        val declaredFields = java.declaredFields
        if (declaredFields.size>0){
            for (field in declaredFields){
                val annotation = field.getAnnotation(Presenter::class.java)
                if (annotation!=null){
                    field.isAccessible=true
                    var typeName:String
                    if (Build.VERSION.SDK_INT>=28){
                        typeName = field.genericType.typeName
                    }
                    else{
                        typeName= field.type.name
                    }

                    val fieldClass = Class.forName(typeName)
                    val constructors = fieldClass.constructors
                    if (constructors.size>0){
                        val newInstance = constructors.get(0).newInstance(obj)
                        field.set(obj,newInstance)
                    }
                }
            }
        }
    }

    /**
     * 反射+注解 初始化M层
     */
    fun injectM(obj:Any) {
        val thatType = obj.javaClass
        val declaredFields = thatType.declaredFields
        //获取Repo的所有字段 进行判断
        if (declaredFields.size>0){
            var hasModel:Boolean=false
            //循环遍历所有的Field
            for (field in declaredFields){
                val annotation = field.getAnnotation(Model::class.java)
                //已找到Model注销修饰的字段
                if (annotation!=null){
                    //打开当前Field的可访问性
                    field.isAccessible=true
                    var typeName:String =""
                    if (Build.VERSION.SDK_INT>=28){
                        typeName = field.genericType.typeName
                    }
                    else{
                        typeName= field.type.name
                    }

                    val fieldType = Class.forName(typeName)
                    val instance = fieldType.newInstance()
                    field.set(obj,instance)
                    hasModel=true
                }
            }
            if (!hasModel){
                throw java.lang.IllegalStateException("no set any model....")
            }

        }else{
            throw IllegalStateException("no set any field....")
        }
    }
}