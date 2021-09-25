package com.zy.frameworkapp.testview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zy.frameworkapp.R;

public class EventActivity extends AppCompatActivity {
    private ListView lvEventTest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        lvEventTest = (ListView) findViewById(R.id.lv_event_test);

        String dataSource[]={"aa","bb","cc","aa","bb","cc","aa","bb","cc","aa","bb","cc","aa","bb","cc","aa","bb","cc","aa","bb","cc","aa","bb","cc","aa","bb","cc"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, androidx.appcompat.R.layout.abc_action_menu_item_layout,dataSource);
        lvEventTest.setAdapter(arrayAdapter);
    }
}