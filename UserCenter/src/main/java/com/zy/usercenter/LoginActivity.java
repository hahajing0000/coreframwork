package com.zy.usercenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zy.apt_router_annotation.ZRoute;
import com.zy.router.ActivityTools$$UserCenter;

@ZRoute(path = "/usercenter/login")
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}