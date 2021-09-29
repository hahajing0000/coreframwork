package com.zy.storage;

import android.util.ArrayMap;

import com.zy.storage.callback.ResultCallback;
import com.zy.storage.impl.DiskChain;
import com.zy.storage.impl.MemoryChain;

import java.sql.SQLTransactionRollbackException;
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

    /**
     * 第一个节点 内存
     */
    private MemoryChain memoryChain=null;
    /**
     * 第二个节点 磁盘
     */
    private DiskChain diskChain=null;

    /**
     * 初始化链
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/8/19 9:00
     */ 
    private StorageChain initChain(String key){
        memoryChain=new MemoryChain();
        diskChain=new DiskChain();
        memoryChain.setNextChain(diskChain);

        chainMap.put(key,memoryChain);
        return diskChain;
    }

    /**
     * 追加链节点
     * @param key-链的key标识
     * @return
     * @author zhangyue
     * @time 2021/8/19 9:03
     */
    public StorageChainManager addChain(String key,StorageChain storageChain){
        if (chainMap.containsKey(key)){
            StorageChain chain = chainMap.get(key);
            while (chain.hasNext()){
                chain=chain.getNextChain();
            }

            chain.setNextChain(storageChain);
        }else{
            StorageChain lastestChain = initChain(key);
            lastestChain.setNextChain(storageChain);
            chainMap.put(key,lastestChain.getFirstChain());
        }
        return this;
    }
    
    /**
     * 获取指定链上的值
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/8/19 9:15
     */ 
    public void getValue(String chainKey,String key, ResultCallback callback){
        if (chainMap.containsKey(chainKey)){
            chainMap.get(chainKey).getValue(key,callback);
        }
    }

    /**
     * 获取到对应链然后存储值
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/8/19 9:16
     */ 
    public <T> void putValue(String chainKey,String key,T data){
        if (chainMap.containsKey(chainKey)){
            chainMap.get(chainKey).putValue(key,data);
        }
    }
}
