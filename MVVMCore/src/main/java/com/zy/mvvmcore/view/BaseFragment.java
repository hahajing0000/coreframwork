package com.zy.mvvmcore.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.ref.WeakReference;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.mvvmcore.view
 * @ClassName: BaseFragment
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/28 7:48
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/28 7:48
 * @UpdateRemark:
 * @Version: 1.0
 */
public abstract class BaseFragment extends Fragment {
    private WeakReference<View> contentView;
    protected WeakReference<Context> appContext;
    protected WeakReference<Activity> mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appContext=new WeakReference<>(getActivity().getApplicationContext());
        mActivity=new WeakReference<Activity>(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView=new WeakReference<View>(createView(inflater,container,savedInstanceState));
        return contentView.get();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
        initEvent();
    }

    /**
     * 初始化事件
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/28 7:50
     */ 
    protected abstract void initEvent();

    /**
     * 初始化数据
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/28 7:50
     */ 
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 创建视图
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/28 7:50
     */ 
    protected abstract View createView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState);
    
    /**
     * 提示消息
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/8/28 7:51
     */ 
    protected void showMsg(String msg){
        showShortMsg(msg);
    }
    
    private void showShortMsg(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    protected <T extends View> T findViewById(int id){
        if (contentView!=null){
            return contentView.get().findViewById(id);
        }
        return null;
    }

    /**
     * 跳转到指定activity
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/28 7:53
     */
    protected void jumpActivity(Class<?> target){
        Intent intent=new Intent(getActivity(),target);
        getActivity().startActivity(intent);
    }

    /**
     * 跳转到指定activity携带参数
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/28 7:54
     */
    protected void jumpActivity(Class<?> target,Bundle params){
        Intent intent=new Intent(getActivity(),target);
        intent.putExtra("params",params);
        getActivity().startActivity(intent);
    }

    /**
     * 切换fragment
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/28 7:58
     */ 
    protected void jumpFragment(int containerViewId,Fragment fragment){
        getFragmentManager().beginTransaction().replace(containerViewId,fragment).commit();
    }
}
