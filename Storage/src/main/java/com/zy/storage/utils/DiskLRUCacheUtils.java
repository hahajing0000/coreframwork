package com.zy.storage.utils;

import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.storage.utils
 * @ClassName: DiskLRUCacheUtils
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/18 11:05
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/18 11:05
 * @UpdateRemark:
 * @Version: 1.0
 */
public final class DiskLRUCacheUtils<V> {
    private DiskLruCache diskLruCache;
    private static DiskLRUCacheUtils instance=new DiskLRUCacheUtils();
    /**
     * 容量上限200M
     */
    private static final int MAX_SIZE=200*1024*1024;
    private DiskLRUCacheUtils(){
        /**
         * 如下 初始化DiskLruCache
         */
        String diskCachePath = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"bawei6diskcache";
        File file=new File(diskCachePath);
        if (!file.exists()){
            file.mkdirs();
        }
        try {
            diskLruCache = DiskLruCache.open(file, 1, 1, MAX_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static DiskLRUCacheUtils getInstance(){
        return instance;
    }

    public void putValue(String key, V data) {

        String mKey= MD5.encrypt(key);
        OutputStream outputStream = null;
        DiskLruCache.Editor edit=null;
        try {
            edit = diskLruCache.edit(mKey);
            if (edit!=null){

                //对象转byte数组
                byte[] bytes= ObjUtils.obj2ByteArray(data);


                outputStream = edit.newOutputStream(0);
                outputStream.write(bytes);
                edit.commit();
                diskLruCache.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (edit!=null){
                try {
                    edit.abort();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }finally {
            if (outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public V getValue(String key) {
        InputStream is=null;
        try {
            List<Byte> data = new ArrayList<>();
            String mKey = MD5.encrypt(key);
            DiskLruCache.Snapshot snapShot = diskLruCache.get(mKey);
            if (snapShot != null) {
                is = snapShot.getInputStream(0);
                byte[] bytes = new byte[2048];
                int len;
                while ((len = is.read(bytes)) != -1) {
                    for (int i = 0; i < len; i++) {
                        data.add(bytes[i]);
                    }
                }
                bytes = new byte[data.size()];
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = data.get(i);
                }
                return ObjUtils.byteArray2Object(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void removeValue(String key) {
        String mKey=MD5.encrypt(key);
        try {
            diskLruCache.remove(mKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        try {
            diskLruCache.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
