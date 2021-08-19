package com.zy.frameworkapp.storagechain;

import android.util.Log;

import com.zy.storage.StorageChain;
import com.zy.storage.callback.ResultCallback;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.frameworkapp.storagechain
 * @ClassName: NetStorageChain
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/19 9:17
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/19 9:17
 * @UpdateRemark:
 * @Version: 1.0
 */
public class NetStorageChain extends StorageChain {
    @Override
    protected void saveData(String key, Object data) {
        Log.d("123", "saveData: ....");
    }

    @Override
    protected void getData(String key, ResultCallback callback) {
        Log.d("123", "getData: ...");
    }

    @Override
    protected void removeAtKey(String key) {

    }

    @Override
    protected void clear() {

    }
}
