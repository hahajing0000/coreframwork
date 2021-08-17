package com.zy.frameworkapp.mvvm.repository;

import androidx.lifecycle.LiveData;

import com.zy.frameworkapp.mvvm.model.UserCenterLocalModel;
import com.zy.frameworkapp.mvvm.entity.UserEntity;
import com.zy.mvvmcore.BaseRepository;
import com.zy.mvvmcore.Model;

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
public class UserCenterRepository extends BaseRepository {

    @Model
    UserCenterLocalModel localModel;

    public LiveData<UserEntity> register(UserEntity entity){
        /**
         * 如果有网我们用的Remote的Model
         * 如果没网我们用的Local的Model
         */

       return localModel.register(entity);
    }
}
