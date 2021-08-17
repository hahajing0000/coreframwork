package com.zy.mvvmcore.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.zy.mvvmcore.viewmodel.BaseViewModel;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.mvvmcore.view
 * @ClassName: BaseActivity
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/16 16:26
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/16 16:26
 * @UpdateRemark:
 * @Version: 1.0
 */
public abstract class BaseMVVMActivity<VM extends BaseViewModel,Binding extends ViewDataBinding> extends AppCompatActivity {
    protected Binding mBinding;
    protected VM mViewModel;
    private HashMap<Integer,Object> mMap=new HashMap<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        //设置生命周期的所有者 如果不设置使用LiveData页面将无法获取到数据的更新
        mBinding.setLifecycleOwner(this);
        mViewModel=createViewModel();
        prepareSetVars(mMap);
        setVars(mBinding,mMap);

        loadData();
        initEvent();
    }

    /**
     * 创建ViewModel实例
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/17 8:59
     */ 
    protected abstract VM createViewModel();

    /**
     * 初始化事件
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/16 16:48
     */ 
    protected abstract void initEvent();

    /**
     * 初始化数据
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/16 16:46
     */ 
    protected abstract void loadData();

    /**
     * 设置变量
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/16 16:43
     */ 
    private void setVars(Binding mBinding, HashMap<Integer, Object> mMap) {
        if (mMap.size()==0){
            throw new RuntimeException("please set variable...");
        }
        
        for (Map.Entry<Integer,Object> entry:mMap.entrySet()){
            mBinding.setVariable(entry.getKey(),entry.getValue());
        }
    }

    /**
     * 准备设置数据源
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/17 8:59
     */ 
    protected abstract void prepareSetVars(HashMap<Integer, Object> mMap);

    /**
     * 获取布局资源id
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/8/17 9:00
     */ 
    protected abstract int getLayoutId();

}
