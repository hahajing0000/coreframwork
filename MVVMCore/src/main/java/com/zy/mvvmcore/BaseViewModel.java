package com.zy.mvvmcore;

import androidx.lifecycle.ViewModel;

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
public abstract class BaseViewModel<Repo extends BaseRepository> extends ViewModel {
    protected Repo mRepository;
    public BaseViewModel(){
        mRepository=createRepository();
    }


    /**
     * 创建并初始化Repository
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/8/16 16:26
     */ 
    protected abstract Repo createRepository();
    
}
