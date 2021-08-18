package com.zy.zrouter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.apt_router_processor
 * @ClassName: MyRouter
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/18 14:00
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/18 14:00
 * @UpdateRemark:
 * @Version: 1.0
 */
public class ZRouter {
    private static Map<String,Class<? extends Activity>> map;
    private Context mContext=null;
    private static ZRouter instance=null;
    private ZRouter(){
        map=new HashMap<>();
    }
    private static class Handler{
        private static final ZRouter INSTANCE=new ZRouter();
    }

    public static final ZRouter getInstance(){
        return Handler.INSTANCE;
    }
    
    /**
     * 将路径与class存储到map
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/8/18 14:09
     */ 
    public void put(String activityName,Class clazz){
        if (null!=clazz&&!TextUtils.isEmpty(activityName)){
            map.put(activityName,clazz);
        }
    }

    public void jump(String activityName){
        jump(activityName,null);
    }

    public void jump(String activityName, Bundle bundle){
        Intent intent=new Intent();
        Class<? extends Activity> aClass = map.get(activityName);
        if (aClass==null){
            return;
        }
        if (bundle!=null){
            intent.putExtras(bundle);
        }
        intent.setClass(mContext,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}
