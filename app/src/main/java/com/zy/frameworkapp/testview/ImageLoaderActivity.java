package com.zy.frameworkapp.testview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.zy.frameworkapp.R;
import com.zy.imageloader.ImageLoaderManager;
import com.zy.imageloader.ImageOptions;
import com.zy.imageloader.StrategyType;

public class ImageLoaderActivity extends AppCompatActivity {
    private ImageView ivImageloaderTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);

        ivImageloaderTest = (ImageView) findViewById(R.id.iv_imageloader_test);

        ImageLoaderManager manager=new ImageLoaderManager(StrategyType.Glide);
        ImageOptions options=new ImageOptions.Builder()
                .setPlaceImage(R.mipmap.ic_launcher)
                .setErrorImage(R.mipmap.ic_launcher)
                .setSkipMemoryCache()
                .setSkipDiskCache()
                .build();
        String imgUrl="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic13.nipic.com%2F20110324%2F6999402_050849259122_2.jpg&refer=http%3A%2F%2Fpic13.nipic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634607623&t=be4a718762c5f4aadb0e640961437e41";
        manager.loadIntoImageView(this,imgUrl,ivImageloaderTest,options);
    }
}