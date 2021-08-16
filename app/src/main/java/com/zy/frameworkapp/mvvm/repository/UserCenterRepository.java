package com.zy.frameworkapp.mvvm.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zy.common.utils.ThreadUtils;
import com.zy.frameworkapp.mvvm.UserCenterLocalModel;
import com.zy.frameworkapp.mvvm.entity.UserEntity;
import com.zy.mvvmcore.BaseRepository;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.frameworkapp.mvvm.repository
 * @ClassName: UserCenterRepository
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/16 16:55
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/16 16:55
 * @UpdateRemark:
 * @Version: 1.0
 */
public class UserCenterRepository extends BaseRepository<UserCenterLocalModel> {
    @Override
    protected UserCenterLocalModel createModel() {
        return new UserCenterLocalModel();
    }

    public LiveData<UserEntity> register(UserEntity entity){
       return mModel.register(entity);
    }
}
