package com.zy.frameworkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zy.storage.callback.ResultCallback;
import com.zy.storage.impl.DiskChain;
import com.zy.storage.impl.MemoryChain;

public class StorageActivity extends AppCompatActivity {
    private Button btnTestStorage;

    private Button btnTestStorageGet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        final MemoryChain<String> memoryChain=new MemoryChain<>();
        DiskChain<String> diskChain=new DiskChain<>();
        memoryChain.setNextChain(diskChain);

        btnTestStorage = (Button) findViewById(R.id.btn_test_storage);
        btnTestStorageGet = (Button) findViewById(R.id.btn_test_storage_get);

        btnTestStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memoryChain.putValue("11","zhangsan");
            }
        });

        btnTestStorageGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memoryChain.getValue("11", new ResultCallback<String>() {
                    @Override
                    public void Sucess(String s) {
                        Log.d("123", "Sucess: "+s);
                    }
                });
            }
        });
    }
}