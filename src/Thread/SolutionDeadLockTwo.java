package Thread;

/**
 * @Author: Daryliu
 * @Date: 2020/8/31 16:09
 * @describe:线程间通信2--在前面的基础上解决线程安全问题
 * @return:
 */

/*
在加入循环和判断使数据效果好一点后，不同姓名对应不同的值，现在出现问题：1、同一个数据出现多次2、name和age不匹配
原因：1、Cpu的一点时间片足够执行很多次 2、线程运行的随机性---线程安全问题（多条语句操作共享数据）
解决方案：加锁
        生成者消费者模式需要对双方都加锁
        锁必须是同一把
*/

//(资源类)缓冲区类：student
class Student1 {
    String name;
    int age;
}

//设置学生数据：SetThread（生产者）
class SetThread1 implements Runnable {
    private Student1 s;
    private int x = 0;

    public SetThread1(Student1 s) { //使用构造函数将资源类的对象传递进来，保证只用一个资源
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (s) {//s保证了是同一把锁
                if (x % 2 == 0) {
                    s.name = "ldy";//可能走到这之后被抢到了执行权，内存中age还是11，但是name变成了ldy
                    s.age = 12;
                } else {
                    s.name = "wwx";//可能走到这之后被抢到了执行权，内存中age还是12，但是name变成了wwx
                    s.age = 11;
                }
                x++;
            }
        }
    }
}

//获取学生数据：GetThread（消费者）
class GetThread1 implements Runnable {
    private Student1 s;

    public GetThread1(Student1 s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (s) {
                System.out.println("姓名：" + s.name + "年龄：" + s.age);
            }
        }
    }
}

//测试类
public class SolutionDeadLockTwo {

    public static void main(String[] args) {
        Student1 s = new Student1();//在外部声明一个资源类，保证只使用一个资源类缓冲区

        SetThread1 setThread = new SetThread1(s);//生产者
        GetThread1 getThread = new GetThread1(s);//消费者
        //线程类
        Thread t1 = new Thread(setThread);
        Thread t2 = new Thread(getThread);
        //启动线程
        t1.start();
        t2.start();
    }
}