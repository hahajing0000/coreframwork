package com.zy.msgbus;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.msgbus
 * @ClassName: ThreadMode
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/5 14:44
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/5 14:44
 * @UpdateRemark:
 * @Version: 1.0
 */
public enum ThreadMode {
    /**
     * 线程类型枚举
     * @param
     * Main        —— 主线程执行
     * Background  —— 如果是子线程直接使用，否则创建子线程
     * Async       —— 无论是否子线程都创建新的线程
     * Default     —— 和发布者同一线程
     * @return
     * @author zhangyue
     * @time 2021/8/5 14:48
     */
    Main,Background,Async,Default
}
