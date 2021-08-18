package com.zy.mvvmcore.adapter;

import android.util.Log;
import android.view.View;

import androidx.databinding.BindingAdapter;

import com.jakewharton.rxbinding2.view.RxView;
import com.zy.mvvmcore.cmds.BindCommand;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.mvvmcore.cmds
 * @ClassName: ViewAdapter
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/17 10:27
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/17 10:27
 * @UpdateRemark:
 * @Version: 1.0
 */
public class ViewAdapter {

    @BindingAdapter(value = {"onClickCmd"},requireAll = false)
    public static void onClickCmd(View view, final BindCommand clickCmd){
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null!=clickCmd){
//                    clickCmd.execute();
//                }
//            }
//        });
        RxView.clicks(view).throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (null!=clickCmd){
                            if(clickCmd.getAction()!=null){
                                clickCmd.execute();
                            }else if (clickCmd.getAction1()!=null){
                                clickCmd.execute();
                            }

                        }
                    }
                });

    }
}
