package com.zy.msgbus;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.msgbus
 * @ClassName: MsgBus
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/5 14:40
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/5 14:40
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MsgBus {
    private static class MsgBusHolder{
        private static MsgBus INSTANCE=new MsgBus();
    }

    private MsgBusImpl impl;
    private MsgBus(){
        impl=new MsgBusImpl();
    }
    public static MsgBus getInstance(){return MsgBusHolder.INSTANCE;}


    public MsgBusImpl getDefault(){
        return impl;
    }
}
