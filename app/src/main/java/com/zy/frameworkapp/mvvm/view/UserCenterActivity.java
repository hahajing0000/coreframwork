package com.zy.frameworkapp.mvvm.view;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.view.View;
import android.widget.Toast;

import com.zy.common.utils.ThreadUtils;
import com.zy.frameworkapp.BR;
import com.zy.frameworkapp.R;
import com.zy.frameworkapp.databinding.UserCenterDataSource;
import com.zy.frameworkapp.mvvm.entity.UserEntity;
import com.zy.frameworkapp.mvvm.viewmodel.UserCenterViewModel;
import com.zy.mvvmcore.view.BaseMVVMActivity;

import java.util.HashMap;

public class UserCenterActivity extends BaseMVVMActivity<UserCenterViewModel, UserCenterDataSource> {

    @Override
    protected UserCenterViewModel createViewModel() {
        return new UserCenterViewModel(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void loadData() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_center;
    }

    @Override
    protected void prepareSetVars(HashMap mMap) {
        mMap.put(BR.viewModel,mViewModel);
        mMap.put(BR.mine,this);
    }

    public void onRegister(View view, MutableLiveData<UserEntity> pageSource){
        mViewModel.register(pageSource.getValue()).observe(this, new Observer<UserEntity>() {
            @Override
            public void onChanged(final UserEntity entity) {
                if (ThreadUtils.isMainThread()){
                    Toast.makeText(UserCenterActivity.this, entity.toString(), Toast.LENGTH_SHORT).show();
                }else{
                    ThreadUtils.doTaskOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(UserCenterActivity.this, entity.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}