package com.zy.frameworkapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.zy.apt_router_annotation.ZRoute;
import com.zy.frameworkapp.schedule.PlanA;
import com.zy.frameworkapp.schedule.ScheduleEntity;
import com.zy.logger.Logger;
import com.zy.logger.common.LoggerLevel;
import com.zy.logger.common.LoggerType;
import com.zy.zrouter.ZRouter;

import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@ZRoute(path = "/main/activity2")
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

        logger.d("MainActivity","test log...");
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ZRouter.getInstance().jump("/main/activity3");
//                ZRouter.getInstance().jump("/usercenter/login");
//                logger.d("123","这是一个log");
//                Stack<ScheduleEntity> stack=new Stack<>();
//                stack.push(new ScheduleEntity(3,1000));
//                stack.push(new ScheduleEntity(5,2000));
//                PlanA planA=new PlanA(stack,new NetTask());
//                planA.doTask();

                new MyTask().execute("我是入口参数");

//                Future<String> submit = Executors.newFixedThreadPool(2).submit(new Callable<String>() {
//                    @Override
//                    public String call() throws Exception {
//                        return "result";
//                    }
//                });
//
//                try {
//                    String s = submit.get(10, TimeUnit.SECONDS);
//                    submit.isDone();
//                    submit.isCancelled();
//                    submit.cancel(true);
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (TimeoutException e) {
//                    e.printStackTrace();
//                }
            }
        });

    }

    class MyTask extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... strings) {
            Log.d("123", " doInBackground: Params="+strings[0]+"  CurrentThread:"+Thread.currentThread().getName());
            for (int i=1;i<=100;i++){
                SystemClock.sleep(200);
                onProgressUpdate(i);
            }
            return "Success";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.d("123", "onPreExecute: ..."+" CurrentThread:"+Thread.currentThread().getName());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("123", "onPostExecute: "+s+" CurrentThread:"+Thread.currentThread().getName());
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d("123", "onProgressUpdate: "+values[0]);
        }
    }
}