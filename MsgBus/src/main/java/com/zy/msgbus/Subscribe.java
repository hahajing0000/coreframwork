package com.zy.msgbus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.msgbus
 * @ClassName: Subscribe
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/5 14:43
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/5 14:43
 * @UpdateRemark:
 * @Version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {
    ThreadMode threadMode();
}
