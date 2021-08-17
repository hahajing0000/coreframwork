package com.zy.mvvmcore;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.mvvmcore
 * @ClassName: Model
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/17 9:26
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/17 9:26
 * @UpdateRemark:
 * @Version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public  @interface Model {
    String value() default "";
}
