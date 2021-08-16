package com.zy.frameworkapp.mvvm.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zy.common.utils.ThreadUtils;
import com.zy.frameworkapp.mvvm.entity.UserEntity;
import com.zy.frameworkapp.mvvm.repository.UserCenterRepository;
import com.zy.mvvmcore.BaseViewModel;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.frameworkapp.mvvm.viewmodel
 * @ClassName: UserCenterViewModel
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/16 16:56
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/16 16:56
 * @UpdateRemark:
 * @Version: 1.0
 */
public class UserCenterViewModel extends BaseViewModel<UserCenterRepository> {
    public MutableLiveData<UserEntity> pageDataSource=new MutableLiveData<>();;
    public UserCenterViewModel(){
        UserEntity userEntity=new UserEntity();
        if (ThreadUtils.isMainThread()){
            pageDataSource.setValue(userEntity);
        }else{
            pageDataSource.postValue(userEntity);
        }
    }

    @Override
    protected UserCenterRepository createRepository() {

        return new UserCenterRepository();
    }

    public LiveData<UserEntity> register(UserEntity entity){
       return mRepository.register(entity);
    }
}
