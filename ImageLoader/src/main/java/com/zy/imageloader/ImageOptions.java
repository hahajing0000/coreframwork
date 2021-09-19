package com.zy.imageloader;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.imageloader
 * @ClassName: ImageConfig
 * @Description:图片设置
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/9/19 8:24
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/9/19 8:24
 * @UpdateRemark:
 * @Version: 1.0
 */
public class ImageOptions {
    /**
     * 占位图
     */
    private int placeImage;
    /**
     * 错误图
     */
    private int errorImage;
    /**
     * 静态图片 Gif取第一帧
     */
    private boolean isStaticImg;
    /**
     * Gif 动图
     */
    private boolean isGif;
    /**
     * 图片大小
     */
    private int[] imageSize;
    /**
     * 关闭内存缓存
     */
    private boolean skipMemoryCache;
    /**
     * 关闭磁盘缓存
     */
    private boolean skipDiskCache;
    private ImageOptions(int _placeImage,
                         int _errorImage,
                         boolean _isStaticImg,
                         boolean _isGif,
                         int[] _imageSize,
                         boolean _skipMemoryCache,
                         boolean _skipDiskCache){
        this.placeImage=_placeImage;
        this.errorImage=_errorImage;
        this.isStaticImg=_isStaticImg;
        this.isGif=_isGif;
        if (this.isStaticImg&&this.isGif){
            throw new IllegalStateException("[isStaticImg] [isGif] not at the same time set up is True");
        }
        this.imageSize=_imageSize;
        this.skipDiskCache=_skipDiskCache;
        this.skipMemoryCache=_skipMemoryCache;
    }

    public int getPlaceImage() {
        return placeImage;
    }

    public int getErrorImage() {
        return errorImage;
    }

    public boolean isStaticImg() {
        return isStaticImg;
    }

    public boolean isGif() {
        return isGif;
    }

    public int[] getImageSize() {
        return imageSize;
    }

    public boolean isSkipMemoryCache() {
        return skipMemoryCache;
    }

    public boolean isSkipDiskCache() {
        return skipDiskCache;
    }

    public static class Builder{
        /**
         * 占位图
         */
        private int placeImage;
        /**
         * 错误图
         */
        private int errorImage;
        /**
         * 静态图片 Gif取第一帧
         */
        private boolean isStaticImg;
        /**
         * Gif 动图
         */
        private boolean isGif;
        /**
         * 图片大小
         */
        private int[] imageSize;
        /**
         * 关闭内存缓存
         */
        private boolean skipMemoryCache;
        /**
         * 关闭磁盘缓存
         */
        private boolean skipDiskCache;

        /**
         * 设置占位图
         * @param placeImage 占位图片资源id
         * @return 
         * @author zhangyue
         * @time 2021/9/19 9:07
         */ 
        public Builder setPlaceImage(int placeImage) {
            this.placeImage = placeImage;
            return this;
        }

        /**
         * 设置错误图片
         * @param errorImage 错误图片资源id
         * @return 
         * @author zhangyue
         * @time 2021/9/19 9:07
         */ 
        public Builder setErrorImage(int errorImage) {
            this.errorImage = errorImage;
            return this;
        }

        /**
         * 设置静态图片显示 Gif图显示第一帧
         * @param staticImg 开启静态图片显示
         * @return 
         * @author zhangyue
         * @time 2021/9/19 9:08
         */ 
        public Builder setStaticImg(boolean staticImg) {
            isStaticImg = staticImg;
            return this;
        }

        /**
         * 设置Gif图片显示
         * @param gif 开启Gif图片显示
         * @return 
         * @author zhangyue
         * @time 2021/9/19 9:08
         */ 
        public Builder setGif(boolean gif) {
            isGif = gif;
            return this;
        }

        /**
         * 设置图片大小
         * @param imageSize 图片大小 【0】宽 【1】高
         * @return 
         * @author zhangyue
         * @time 2021/9/19 9:09
         */ 
        public Builder setImageSize(int[] imageSize) {
            this.imageSize = imageSize;
            return this;
        }

        /**
         * 关闭内存缓存 默认：开启
         * @param 
         * @return
         * @author zhangyue
         * @time 2021/9/19 9:09
         */ 
        public Builder setSkipMemoryCache() {
            this.skipMemoryCache = true;
            return this;
        }

        /**
         * 关闭磁盘缓存
         * @param
         * @return 
         * @author zhangyue
         * @time 2021/9/19 9:31
         */ 
        public Builder setSkipDiskCache() {
            this.skipDiskCache = true;
            return this;
        }

        public ImageOptions build(){

            return new ImageOptions(placeImage,errorImage,isStaticImg,isGif,imageSize,skipMemoryCache,skipDiskCache);
        }
    }
}
