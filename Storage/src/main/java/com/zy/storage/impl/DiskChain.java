package com.zy.storage.impl;

import com.zy.storage.StorageChain;
import com.zy.storage.callback.ResultCallback;
import com.zy.storage.utils.DiskLRUCacheUtils;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.storage.impl
 * @ClassName: DiskChain
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/18 15:42
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/18 15:42
 * @UpdateRemark:
 * @Version: 1.0
 */
public class DiskChain<T> extends StorageChain<T> {
    @Override
    protected void saveData(String key, T data) {
        DiskLRUCacheUtils.getInstance().putValue(key,data);
    }

    @Override
    protected void getData(String key, ResultCallback<T> callback) {
        T value = (T) DiskLRUCacheUtils.getInstance().getValue(key);
        callback.Sucess(value);
    }

    @Override
    protected void removeAtKey(String key) {
        DiskLRUCacheUtils.getInstance().removeValue(key);
    }

    @Override
    protected void clear() {
        DiskLRUCacheUtils.getInstance().clear();
    }
}
