package com.zy.mvvmcore.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.mvvmcore.view
 * @ClassName: BaseActivity
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/27 18:23
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/27 18:23
 * @UpdateRemark:
 * @Version: 1.0
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEnv();

        loadData();
        initEvent();
    }

    /**
     * 初始化事件
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/28 7:40
     */ 
    protected abstract void initEvent();

    /**
     * 初始化数据
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/28 7:40
     */ 
    protected abstract void loadData();

    /**
     * 初始化环境
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/27 18:25
     */
    protected abstract void initEnv();
    
    /**
     * toast 提示消息
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/28 7:42
     */ 
    protected void showMsg(final String msg){
        if (Looper.getMainLooper().getThread()==Thread.currentThread()){
            showShortMsg(msg);
        }else{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showShortMsg(msg);
                }
            });
        }
    }
    
    private void showShortMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    
    /**
     * 跳转到指定activity
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/28 7:43
     */ 
    protected void jumpToActivity(Class<?> target){
        Intent intent=new Intent(this,target);
        startActivity(intent);
    }
    
    /**
     * 跳转到指定activity携带参数
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/8/28 7:48
     */ 
    protected void jumpToActivity(Class<?> target,Bundle bundle){
        Intent intent=new Intent(this,target);
        intent.putExtra("params",bundle);
        startActivity(intent);
    }
}
