package com.zy.mvvmcore.viewmodel;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.zy.mvvmcore.repository.BaseRepository;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.mvvmcore
 * @ClassName: BaseViewModel
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/16 16:24
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/16 16:24
 * @UpdateRemark:
 * @Version: 1.0
 */
public abstract class BaseViewModel<Repo extends BaseRepository> extends ViewModel implements LifecycleObserver {
    protected Repo mRepository;
    private LifecycleOwner mOwner;
    public BaseViewModel(LifecycleOwner owner){
        mRepository=createRepository();
        mOwner=owner;
        mOwner.getLifecycle().addObserver(this);
    }


    /**
     * 创建并初始化Repository
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/8/16 16:26
     */ 
    protected abstract Repo createRepository();

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void uiConnection(){
        initResource();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void disConnection(){
        releaseResource();
        mOwner.getLifecycle().removeObserver(this);
    }

    /**
     * 释放资源
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/17 9:16
     */
    protected  void releaseResource(){}

    /**
     * 资源的初始化
     * @param
     * @return
     * @author zhangyue
     * @time 2021/8/17 9:16
     */
    protected void initResource(){}


}
