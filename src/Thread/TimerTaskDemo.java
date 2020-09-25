package Thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: Daryliu
 * @Date: 2020/9/25 20:21
 * @param:定时器：调度多个定时任务以后台多线程的形式执行（某个时间做某个事情）。 通过Timer（定时器）和TimerTask（任务）的方式来定于调度的功能
 * @return: Timer
 * public Timer()
 * public void schedule(TimerTask task,long delay)  在指定的延迟时间后执行指定的任务
 * public void schedule(TimerTask task,long delay,long period)  在指定的延迟时间后开始“重复”的固定延迟执行
 * TimerTask
 * public abstract void run()   要在子类中重写实现
 * public boolean cancel()
 * 正式开发中可以使用Quartz（开源调度框架）
 */
public class TimerTaskDemo {
    /*  只实现一次的定时器任务
    public static void main(String[] args) {
        //创建定时器对象
        Timer t = new Timer();
        //3秒后执行爆炸任务
        *//*t.schedule(new Task(),3000);//只有这个不会结束任务*//*
        t.schedule(new Task(t),3000);
    }*/

    //实现多次的定时器任务demo
    public static void main(String[] args) {
        Timer t = new Timer();
        //3秒后执行爆炸任务，如果没有成功，则每隔2秒继续炸
        t.schedule(new Task2(),3000,2000);
    }
}

//先定义一个任务，（只实现一次的定时器任务）
class Task extends TimerTask {
    private Timer t;

    public Task() {
    }

    public Task(Timer t) {//构造函数实现当传递参数时获取参数并实现结束定时任务
        this.t = t;
    }

    @Override
    public void run() {
        System.out.println("定时炸弹爆炸");
        t.cancel();
    }
}

//实现多次的定时器任务
class Task2 extends TimerTask {
    @Override
    public void run() {
        System.out.println("定时炸弹爆炸");
    }
}
