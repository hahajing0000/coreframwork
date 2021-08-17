package com.zy.frameworkapp.mvvm.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.frameworkapp.mvvm.entity
 * @ClassName: UserEntity
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/16 16:49
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/16 16:49
 * @UpdateRemark:
 * @Version: 1.0
 */
public class UserEntity  extends BaseObservable {
    private String phoneNumber;
    private String name;
    private int age;
    private int sex;
    private String address;

    public UserEntity() {
    }

    public UserEntity(String phoneNumber, String name, int age, int sex, String address) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                '}';
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
