package com.zy.mvvmcore.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.zy.mvvmcore.R;
import com.zy.mvvmcore.utils.StatusBarCompat;

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
    /**
     * Screen information
     */
    protected int mScreenWidth = 0;
    protected int mScreenHeight = 0;
    protected float mScreenDensity = 0.0f;

    /**
     * context
     */
    protected Context mContext = null;

    protected Toolbar mToolbar;

    protected boolean statusBarCompat = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
        super.onCreate(savedInstanceState);
        mContext = this;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        mScreenDensity = displayMetrics.density;
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;

        if (statusBarCompat) {
            StatusBarCompat.compat(this, getPrimaryDarkColor());
            transparent19and20();
        }
        setContentView(getLayoutId());
        initEnv();
        mLoadingDialog=createLoadingDialog();
        loadData();
        initEvent();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = crateToolBar();
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * 获取ToolBar
     * @param
     * @return
     * @author zhangyue
     * @time 2021/9/29 10:41
     */
    protected abstract Toolbar crateToolBar();

    /**
     * 获取系题主题色
     * @param
     * @return
     * @author zhangyue
     * @time 2021/9/29 10:38
     */ 
    protected int getPrimaryDarkColor(){
        return Color.TRANSPARENT;
    }

    //获取布局资源id
    protected abstract int getLayoutId();

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
     * 初始化Loading对话框
     * @param
     * @return
     * @author zhangyue
     * @time 2021/9/29 10:44
     */
    protected abstract Dialog createLoadingDialog();

    protected void transparent19and20() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    
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

    Dialog mLoadingDialog=null;
    /**
     * 显示刷新Loadding
     */
    protected void showLoadingDialog() {
        try {
            mLoadingDialog.setTitle(null);
            mLoadingDialog.setCancelable(false);
            mLoadingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                        hideLoadingDialog();
                    }
                    return true;
                }
            });
            if (!isFinishing()) {
                mLoadingDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏刷新Loadding
     */
    protected void hideLoadingDialog() {
        try {
            if (mLoadingDialog != null) {
                mLoadingDialog.dismiss();
                mLoadingDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
