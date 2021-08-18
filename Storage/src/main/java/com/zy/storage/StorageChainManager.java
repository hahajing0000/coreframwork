package com.zy.storage;

import android.util.ArrayMap;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.storage
 * @ClassName: StorageChainManager
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/18 16:31
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/18 16:31
 * @UpdateRemark:
 * @Version: 1.0
 */
public class StorageChainManager {
//    ArrayMap<String,String> map=new ArrayMap<>(); HashMap HashTable
    private static ConcurrentHashMap<String,StorageChain> chainMap;
    private static StorageChainManager instance=null;
    private StorageChainManager(){
        chainMap=new ConcurrentHashMap<>();
    }
    private static class Handler{
        private static StorageChainManager INSTANCE=new StorageChainManager();
    }

    public static StorageChainManager getInstance(){
        return Handler.INSTANCE;
    }


}
