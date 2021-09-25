package com.zy.storage.impl;

import com.zy.storage.StorageChain;
import com.zy.storage.callback.ResultCallback;
import com.zy.storage.utils.LRUCacheUtils;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.storage.impl
 * @ClassName: MemoryChain
 * @Description:内存上的存储链节点逻辑
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/18 15:39
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/18 15:39
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MemoryChain<T> extends StorageChain<T> {
    @Override
    protected void saveData(String key, T data) {
        LRUCacheUtils.getInstance().putValue(key,data);
    }

    @Override
    protected void getData(String key, ResultCallback<T> callback) {
        T value = (T) LRUCacheUtils.getInstance().getValue(key);
        if (null!=callback){
            callback.Success(value);
        }

    }

    @Override
    protected void removeAtKey(String key) {
        LRUCacheUtils.getInstance().removeValue(key);
    }

    @Override
    protected void clear() {
        LRUCacheUtils.getInstance().clear();
    }
}
