package com.zy.frameworkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zy.frameworkapp.storagechain.NetStorageChain;
import com.zy.frameworkapp.storagechain.OtherStroageChain;
import com.zy.storage.StorageChainManager;
import com.zy.storage.callback.ResultCallback;
import com.zy.storage.impl.DiskChain;
import com.zy.storage.impl.MemoryChain;

public class StorageActivity extends AppCompatActivity {
    private Button btnTestStorage;

    private Button btnTestStorageGet;


    private final static String CHAINKEY="test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        //追加链节点
        StorageChainManager.getInstance()
                .addChain(CHAINKEY,new NetStorageChain())
                .addChain(CHAINKEY,new OtherStroageChain());

        btnTestStorage = (Button) findViewById(R.id.btn_test_storage);
        btnTestStorageGet = (Button) findViewById(R.id.btn_test_storage_get);

        btnTestStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorageChainManager.getInstance().putValue(CHAINKEY,"testkey","111222");
            }
        });

        btnTestStorageGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorageChainManager.getInstance().getValue(CHAINKEY, "testkey", new ResultCallback() {
                    @Override
                    public void Sucess(Object o) {
                        Log.d("123", "Sucess: "+o.toString());
                    }
                });
            }
        });
    }
}