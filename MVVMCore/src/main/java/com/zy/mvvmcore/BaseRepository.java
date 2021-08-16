package com.zy.mvvmcore;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.mvvmcore
 * @ClassName: BaseRepository
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/16 16:22
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/16 16:22
 * @UpdateRemark:
 * @Version: 1.0
 */
public abstract class BaseRepository<M extends IModel> {
    protected M mModel;
    public BaseRepository(){
        mModel= createModel();
    }

    /**
     * 创建并初始化Model
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/8/16 16:24
     */ 
    protected abstract M createModel();
}
