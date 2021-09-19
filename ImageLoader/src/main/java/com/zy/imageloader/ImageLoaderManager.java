package com.zy.imageloader;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.zy.imageloader.impl.GlideStrategy;

import java.io.File;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.imageloader
 * @ClassName: ImageLoaderManager
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/9/19 9:24
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/9/19 9:24
 * @UpdateRemark:
 * @Version: 1.0
 */
public class ImageLoaderManager implements IImageloader {

    IImageloader imageloader=null;

    public ImageLoaderManager(StrategyType type){
        switch (type){
            case Glide:
                imageloader=new GlideStrategy();
                break;
            case Fresco:
                throw new RuntimeException("this strategy is not implement...");
            case Picasso:
                throw new RuntimeException("this strategy is not implement...");
        }
    }

    /**加载文件类型的图片到指定图片控件
     * @param
     * @return
     * @author zhangyue
     * @time 2021/9/19 8:45
     */
    @Override
    public void loadFileIntoImageView(Context context, File file, ImageView target, ImageOptions config) {
        imageloader.loadFileIntoImageView(context,file,target,config);
    }

    /**
     * 加载资源类型的图片到指定图片控件
     * @param
     * @return
     * @author zhangyue
     * @time 2021/9/19 8:46
     */
    @Override
    public void loadResourceIntoImageView(Context context, int source, ImageView target, ImageOptions config) {
        imageloader.loadResourceIntoImageView(context,source,target,config);
    }

    /**
     * 加载二进制数组类型的图片到指定图片控件
     * @param
     * @return
     * @author zhangyue
     * @time 2021/9/19 8:46
     */
    @Override
    public void loadByteArrayIntoImageView(Context context, byte[] bytes, ImageView target, ImageOptions config) {
        imageloader.loadByteArrayIntoImageView(context,bytes,target,config);
    }

    /**
     * 加载Uri类型的图片到指定图片控件
     * @param
     * @return
     * @author zhangyue
     * @time 2021/9/19 8:46
     */
    @Override
    public void loadUriIntoImageView(Context context, Uri uri, ImageView target, ImageOptions config) {
        imageloader.loadUriIntoImageView(context,uri,target,config);
    }

    /**
     * 加载远程地址图片到指定图片控件
     * @param
     * @return
     * @author zhangyue
     * @time 2021/9/19 8:47
     */
    @Override
    public void loadIntoImageView(Context context, String url, ImageView target, ImageOptions config) {
        imageloader.loadIntoImageView(context,url,target,config);
    }
}
