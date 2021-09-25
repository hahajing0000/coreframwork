package com.zy.frameworkapp.testview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.frameworkapp.testview
 * @ClassName: MyScrollView
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/9/24 15:30
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/9/24 15:30
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MyScrollView extends ScrollView {

    private Rect rect;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        /**
         * 判断触摸的位置是否在ListView中，如果在就返回false不拦截 否则返回true拦截
         */
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        /**
         * 找到listview
         */
        if (rect==null){
            getListViewBounds(this);
        }

        if (rect.contains(x,y)){
            return false;
        }
        else{
            return true;
        }
//        return super.onInterceptTouchEvent(ev);
    }

    private void getListViewBounds(ViewGroup target) {
        int childCount = target.getChildCount();
        for (int i=0;i<childCount;i++){
            View subView = target.getChildAt(i);
            if (subView instanceof ListView){
                rect = new Rect(subView.getLeft(),subView.getTop(),subView.getRight(),subView.getBottom());
            }else if (subView instanceof ViewGroup){
                getListViewBounds((ViewGroup) subView);
            }
        }
    }
}
