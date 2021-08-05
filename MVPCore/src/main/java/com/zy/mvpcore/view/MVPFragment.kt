package com.zy.mvpcore.view

import com.zy.common.InjectUtils

/**
 *
 * @ProjectName: FrameworkApp
 * @Package: com.zy.mvpcore.view
 * @ClassName: MVPFragment
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/5 13:52
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/5 13:52
 * @UpdateRemark:
 * @Version: 1.0
 */
abstract class MVPFragment: BaseFragment() {
    init {
        injectP()
    }

    private fun injectP() {
        InjectUtils.injectP(this)
    }
}