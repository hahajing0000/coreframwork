package com.zy.mvvmcore.repository;

import com.zy.mvvmcore.common.MVVMModelException;
import com.zy.mvvmcore.model.Model;

import java.lang.reflect.Field;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.mvvmcore
 * @ClassName: BaseRepository
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/16 16:22
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/16 16:22
 * @UpdateRemark:
 * @Version: 1.0
 */
public abstract class BaseRepository {

    public BaseRepository(){
        injectModel();
    }

    /**
     * 反射设置Model的实例
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/17 10:04
     */ 
    private void injectModel() {
        Class<? extends BaseRepository> clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields==null||fields.length==0){
            throw new MVVMModelException("no have any fields info...");
        }
        boolean flag=false;
        for (Field field:fields){
            Model annotation = field.getAnnotation(Model.class);
            if (null!=annotation){
                flag=true;
//                String value = annotation.value();
                field.setAccessible(true);
                String fieldClassName = field.getType().getName();
                try {
                    Class<?> fieldClazz = Class.forName(fieldClassName);
                    Object fieldInstance = fieldClazz.newInstance();
                    field.set(this,fieldInstance);
                } catch (ClassNotFoundException e) {
                    throw new MVVMModelException(e.getMessage());
                } catch (IllegalAccessException e) {
                    throw new MVVMModelException(e.getMessage());
                } catch (InstantiationException e) {
                    throw new MVVMModelException(e.getMessage());
                }

            }
        }
        if (!flag){
            throw new MVVMModelException("no set any model.....");
        }
    }

}
