package com.zy.frameworkapp.schedule;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.frameworkapp.schedule
 * @ClassName: ScheduleEntity
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/26 8:03
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/26 8:03
 * @UpdateRemark:
 * @Version: 1.0
 */
public class ScheduleEntity {
    private int times;
    private int interval;

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public ScheduleEntity(int times, int interval) {
        this.times = times;
        this.interval = interval;
    }
}
