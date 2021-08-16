package com.zy.frameworkapp.mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zy.common.utils.ThreadUtils;
import com.zy.frameworkapp.mvvm.entity.UserEntity;
import com.zy.mvvmcore.IModel;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.frameworkapp.mvvm
 * @ClassName: UserCenterLocalModel
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/16 16:48
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/16 16:48
 * @UpdateRemark:
 * @Version: 1.0
 */
public class UserCenterLocalModel implements IModel {

    public LiveData<UserEntity> register(UserEntity entity){
        MutableLiveData<UserEntity> entityMutableLiveData=new MutableLiveData<>();
        UserEntity entity1=new UserEntity("18011111111","小明",21,0,"天津");
        if(ThreadUtils.isMainThread()){
            entityMutableLiveData.setValue(entity1);
        }else{
            entityMutableLiveData.postValue(entity1);
        }
        return entityMutableLiveData;
    }

}
