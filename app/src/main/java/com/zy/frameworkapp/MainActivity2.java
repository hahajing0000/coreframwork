package com.zy.frameworkapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.zy.apt_router_annotation.ZRoute;
import com.zy.logger.Logger;
import com.zy.logger.common.LoggerLevel;
import com.zy.logger.common.LoggerType;
import com.zy.zrouter.ZRouter;

//@ZRoute(path = "/main/activity2")
public class MainActivity2 extends AppCompatActivity {
    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnTest = (Button) findViewById(R.id.btn_test);

        final Logger logger = new Logger.Builder()
                .setDebug(BuildConfig.DEBUG)
                .setLevel(LoggerLevel.Debug)
                .setLoggerType(LoggerType.LOGCAT)
                .setTAG("zhangyue")
                .build();
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ZRouter.getInstance().jump("/main/activity3");
//                ZRouter.getInstance().jump("/usercenter/login");
                logger.d("123","这是一个log");
            }
        });

    }
}