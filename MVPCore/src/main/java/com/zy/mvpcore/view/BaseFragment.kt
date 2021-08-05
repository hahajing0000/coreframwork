package com.zy.mvpcore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 *
 * @ProjectName: FrameworkApp
 * @Package: com.zy.mvpcore.view
 * @ClassName: BaseFragment
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/5 13:42
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/5 13:42
 * @UpdateRemark:
 * @Version: 1.0
 */
abstract class BaseFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(getLayoutId(),container)
        return view
    }

    abstract fun getLayoutId(): Int


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init();
        initData();
        initEvent();
    }

    override fun onDestroyView() {
        super.onDestroyView()
        releaseResource()
    }

    /**
     * 释放资源
     */
    abstract fun releaseResource()

    /**
     * 初始化事件
     */
    abstract fun initEvent()

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化资源
     */
    abstract fun init()


    /**
     * 切换Fragment
     */
    protected fun changeFragment(container:Int,fragment:Fragment){
        this.activity?.supportFragmentManager?.beginTransaction()?.replace(container,fragment)?.commit()
    }

}