package com.zy.frameworkapp.testview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.frameworkapp.testview
 * @ClassName: MyListView
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/9/24 15:41
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/9/24 15:41
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MyListView extends ListView {
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                super.requestDisallowInterceptTouchEvent(true);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
