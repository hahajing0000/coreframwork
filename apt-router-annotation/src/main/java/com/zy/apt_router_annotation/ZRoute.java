package com.zy.apt_router_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.apt_router_annotation
 * @ClassName: Route
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/11 16:23
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/11 16:23
 * @UpdateRemark:
 * @Version: 1.0
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public  @interface ZRoute {
    String path();
}
