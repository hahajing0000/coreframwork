MsgEvent使用方式：

1、创建MsgEvent 消息事件类型，如：

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

2、订阅消息事件 2.1、MsgBus.getInstance().default.register(this)

2.2、编写接收消息事件方法，如： @Subscribe(threadMode = ThreadMode.Main) fun getMsg(event1: MsgEvent1){ Log.d("123", "getMsg: thread:${Thread.currentThread().name} msg:${event1.msg}") }

@Subscribe(threadMode = ThreadMode.Async) fun getMsg2(event2:MsgEvent1){ Log.d("123", "getMsg2: thread:${Thread.currentThread().name} msg:${event2.msg}") }

3、发布消息事件

MsgBus.getInstance().getDefault().post(new MsgEvent1("xiaoming"));
