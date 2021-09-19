package com.zy.imageloader;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import java.io.File;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.imageloader
 * @ClassName: IImageloader
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/9/19 8:21
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/9/19 8:21
 * @UpdateRemark:
 * @Version: 1.0
 */
public interface IImageloader {
    /**加载文件类型的图片到指定图片控件
     * @param
     * @return
     * @author zhangyue
     * @time 2021/9/19 8:45
     */
    void loadFileIntoImageView(Context context, File file, ImageView target, ImageOptions config);

    /**
     * 加载资源类型的图片到指定图片控件
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/9/19 8:46
     */ 
    void loadResourceIntoImageView(Context context, int source, ImageView target, ImageOptions config);

    /**
     * 加载二进制数组类型的图片到指定图片控件
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/9/19 8:46
     */ 
    void loadByteArrayIntoImageView(Context context, byte[] bytes, ImageView target, ImageOptions config);

    /**
     * 加载Uri类型的图片到指定图片控件
     * @param
     * @return
     * @author zhangyue
     * @time 2021/9/19 8:46
     */ 
    void loadUriIntoImageView(Context context, Uri uri, ImageView target, ImageOptions config);

    /**
     * 加载远程地址图片到指定图片控件
     * @param
     * @return
     * @author zhangyue
     * @time 2021/9/19 8:47
     */
    void loadIntoImageView(Context context, String url, ImageView target, ImageOptions config);
}
