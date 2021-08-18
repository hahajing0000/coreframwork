package com.zy.storage.utils;

import android.util.LruCache;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.storage.utils
 * @ClassName: LRUCacheUtils
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/18 11:05
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/18 11:05
 * @UpdateRemark:
 * @Version: 1.0
 */
public class LRUCacheUtils<V> {
    int maxSize= (int) (Runtime.getRuntime().totalMemory()/8);
    private LRUCacheUtils(){
        lruCache=new LruCache<String, V>(maxSize);
    }
    private static volatile LRUCacheUtils instance=null;
    public static LRUCacheUtils getInstance(){
        if (null==instance){
            synchronized (LRUCacheUtils.class){
                if (null==instance){
                    instance=new LRUCacheUtils();
                }
            }
        }

        return instance;
    }

    LruCache<String,V> lruCache=null;

    /**
     * 按Key存储值
     * @param key
     * @param value
     */
    public void putValue(String key,V value){
        lruCache.put(key,value);
    }

    /**
     * 按Key获取值
     * @param key
     * @return
     */
    public V getValue(String key){
        return lruCache.get(key);
    }

    /**
     * 按Key删除指定值
     * @param key
     */
    public void removeValue(String key){
        lruCache.remove(key);
    }

    /**
     * 清空
     */
    public void clear(){
        lruCache.evictAll();
    }
}
