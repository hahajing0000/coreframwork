package com.zy.logger;

import android.os.Build;
import android.util.Log;

import com.zy.logger.common.LoggerLevel;
import com.zy.logger.common.LoggerType;
import com.zy.logger.impl.LogcatLogger;

import java.util.PropertyResourceBundle;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.logger
 * @ClassName: Logger
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/21 10:21
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/21 10:21
 * @UpdateRemark:
 * @Version: 1.0
 */
public class Logger {
    private ILogger logger;

    private static Logger instance=null;
//    private Logger(){}
    private Logger(String TAG,LoggerLevel level,LoggerType loggerType,boolean isDebug,String saveUrl){
        this.TAG=TAG;
        this.level=level;
        this.loggerType=loggerType;
        this.isDebug=isDebug;
        this.saveUrl=saveUrl;
        logger=getLogger(TAG,level,loggerType,isDebug,saveUrl);
    }

    /**
     * 创建Logger
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/21 10:46
     */ 
    private ILogger getLogger(String tag, LoggerLevel level, LoggerType loggerType,boolean isDebug,String saveUrl) {
        switch (loggerType){
            case EMAIL:
                throw new IllegalStateException("该方案还未实现...");
            case LOGCAT:
                logger=new LogcatLogger();
                break;
            case FILE:
                throw new IllegalStateException("该方案还未实现...");
            case NET:
                throw new IllegalStateException("该方案还未实现...");
            default:
                logger=new LogcatLogger();
                break;
        }
        logger.setDebug(isDebug);
        logger.setLoggerLevel(level);
        logger.setLogTAG(tag);
        logger.setSaveUrl(saveUrl);
        return logger;
    }

    /**
     * 输出Debug log
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/21 10:57
     */ 
    public void d(String Tag,String log){
        logger.d(Tag,log);
    }

    /**
     * 输出Verbose log
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/21 10:57
     */ 
    public void v(String Tag,String log){
        logger.v(Tag,log);
    }
    
    /**
     * 输出Info log
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/21 10:57
     */ 
    public void i(String Tag,String log){
        logger.i(Tag,log);
    }
    
    /**
     * 输出Warn log
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/21 10:58
     */ 
    public void w(String Tag,String log){
        logger.w(Tag,log);
    }
    
    /**
     * 输出Errorlog
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/21 10:58
     */ 
    public void e(String Tag,String log){
        logger.e(Tag,log);    
    }

//    private static class Handler{
//        private static Logger INSTANCE=new Logger();
//    }
//
//    public static Logger getInstance(){
//        return Handler.INSTANCE;
//    }

    private String TAG="";
    private LoggerLevel level;
    private LoggerType loggerType=LoggerType.LOGCAT;
    private boolean isDebug=true;
    private String saveUrl="";

    /**
     * 建造者模式构建Logger
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/8/21 10:47
     */ 
   public static class Builder{
        private String TAG;
        private LoggerLevel level;
        private LoggerType loggerType;
        private boolean isDebug;
        private String url;

        /**
         * 设置TAG
         * @param TAG
         */
        public Builder setTAG(String TAG) {
            this.TAG = TAG;
            return this;
        }

        /**
         * 设置最小输入日志等级
         * @param level
         */
        public Builder setLevel(LoggerLevel level) {
            this.level = level;
            return this;
        }

        /**
         * 设置日志输出类型
         * @param loggerType
         */
        public Builder setLoggerType(LoggerType loggerType) {
            this.loggerType = loggerType;
            return this;
        }

        public Builder setDebug(boolean debug) {
            isDebug = debug;
            return this;
        }

        public Builder setSaveUrl(String url) {
            this.url = url;
            return this;
        }

        public Logger build(){
            return new Logger(TAG,level,loggerType,isDebug,url);
        }

    }
}
