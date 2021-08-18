package com.zy.mvvmcore.cmds;

import android.os.Build;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.mvvmcore.cmds
 * @ClassName: BindCommand
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/17 10:11
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/17 10:11
 * @UpdateRemark:
 * @Version: 1.0
 */
public class BindCommand<T,R> {
    private Action action;
    private Action1 action1;
    private Function function;
    private Function1 function1;

    public BindCommand(Action _action){
        this.action= _action;
    }

    public BindCommand(Action1 _action){
        this.action1= _action;
    }

    public BindCommand(Function _function){
        this.function= _function;
    }

    public BindCommand(Function1 _function){
        this.function1= _function;
    }

    public Action getAction() {
        return action;
    }

    public Action1 getAction1() {
        return action1;
    }

    public Function getFunction() {
        return function;
    }

    public Function1 getFunction1() {
        return function1;
    }

    public void execute(){
        if (action!=null){
            action.Execute();
        }
    }

    public void execute(T param){
        if (action1!=null){
            action1.Execute(param);
        }
    }

    public R fExecute(){
        if (function!=null){
           return (R) function.Execute();
        }
        return null;
    }

    public R fExecute(T param){
        if (function1!=null){
            return (R) function1.Execute(param);
        }
        return null;
    }

}
