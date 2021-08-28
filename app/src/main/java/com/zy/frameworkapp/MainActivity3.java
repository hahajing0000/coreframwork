package com.zy.frameworkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zy.apt_router_annotation.ZRoute;
import com.zy.zrouter.ZRouter;

@ZRoute(path = "/main/activity3")
public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

    }
}