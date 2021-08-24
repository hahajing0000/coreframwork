package com.zy.logger.common;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.logger
 * @ClassName: LoggerLevel
 * @Description:日志等级
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/21 10:11
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/21 10:11
 * @UpdateRemark:
 * @Version: 1.0
 */
public enum LoggerLevel {

    Verbose(1),Debug(2),Info(3),Warn(4),Error(5);

    private int value=0;
    LoggerLevel(int _value){
        this.value=_value;
    }
}
