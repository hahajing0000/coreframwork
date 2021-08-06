package com.zy.frameworkapp;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.frameworkapp
 * @ClassName: MsgEvent1
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/6 10:18
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/6 10:18
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MsgEvent1 {

    public MsgEvent1(String msg) {
        this.msg = msg;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
