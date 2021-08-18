package com.zy.frameworkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zy.apt_router_annotation.ZRoute;

@ZRoute(path = "/main/activity2")
public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}