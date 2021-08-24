package com.zy.logger;

import com.zy.logger.common.LoggerLevel;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.logger
 * @ClassName: ILogger
 * @Description: Log接口
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/5 13:31
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/5 13:31
 * @UpdateRemark:
 * @Version: 1.0
 */
public interface ILogger {
    void d(String Tag,String log);
    void v(String Tag,String log);
    void i(String Tag,String log);
    void w(String Tag,String log);
    void e(String Tag,String log);

    /**
     * 是否Debug
     */
    void setDebug(boolean isDebug);
    /**
     * 默认TAG
     */
    void setLogTAG(String tag);
    /**
     * 默认日志等级
     */
    void setLoggerLevel(LoggerLevel level);

    /**
     * Log存储位置 可以是本地路径 可以是网络路径 可以是Email地址
     */
    void setSaveUrl(String url);

}
