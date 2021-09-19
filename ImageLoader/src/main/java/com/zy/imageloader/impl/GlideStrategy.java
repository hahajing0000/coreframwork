package com.zy.imageloader.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zy.imageloader.IImageloader;
import com.zy.imageloader.ImageOptions;

import java.io.File;
import java.lang.ref.SoftReference;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.imageloader.impl
 * @ClassName: GlideStrategy
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/9/19 8:51
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/9/19 8:51
 * @UpdateRemark:
 * @Version: 1.0
 */
public class GlideStrategy implements IImageloader {
    @Override
    public void loadFileIntoImageView(Context context, File file, ImageView target, ImageOptions config) {
        loadImageView(context,file,target,config);
    }
    
    @Override
    public void loadResourceIntoImageView(Context context, int source, ImageView target, ImageOptions config) {
        loadImageView(context,source,target,config);
    }

    @Override
    public void loadByteArrayIntoImageView(Context context, byte[] bytes, ImageView target, ImageOptions config) {
        loadImageView(context,bytes,target,config);
    }

    @Override
    public void loadUriIntoImageView(Context context, Uri uri, ImageView target, ImageOptions config) {
        loadImageView(context,uri,target,config);
    }

    @Override
    public void loadIntoImageView(Context context, String url, ImageView target, ImageOptions config) {
        loadImageView(context,url,target,config);
    }

    /**
     * 加载图片资源到ImageView控件上
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/9/19 9:04
     */ 
    private void loadImageView(Context context, Object source, ImageView target, ImageOptions config){
        SoftReference<Context> ctx=new SoftReference<>(context);
        RequestBuilder<Drawable> builder = Glide.with(ctx.get()).load(source);
        setBuilderOptions(builder,config);
        builder.into(target);
    }

    /**
     * 设置图片参数
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/9/19 9:05
     */ 
    private void setBuilderOptions(RequestBuilder<Drawable> builder, ImageOptions config) {
        if (null==config){
            return;
//            throw new IllegalStateException("image optons is null...");
        }
        RequestOptions options=new RequestOptions();
        if(config.getPlaceImage()!=0){
            options.placeholder(config.getPlaceImage());
        }
        if (config.getErrorImage()!=0){
            options.error(config.getErrorImage());
        }
        if (config.isStaticImg()){

        }
        if (config.isGif()){

        }
        if (config.getImageSize()!=null){
            if (config.getImageSize().length!=2){
                throw new IllegalStateException("please set imageSize length size is 2");
            }
            options.override(config.getImageSize()[0],config.getImageSize()[1]);
        }
        if (config.isSkipMemoryCache()){
            options.skipMemoryCache(true);
        }
        if (config.isSkipDiskCache()){
            options.diskCacheStrategy(DiskCacheStrategy.NONE);
        }

        builder.apply(options);

    }
}
