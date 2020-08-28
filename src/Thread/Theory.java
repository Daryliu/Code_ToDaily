package Thread;

/**
 * @Author: Daryliu
 * @Date: 2020/8/27 20:46
 * @param:
 * @return:
 *          JVM运行原理：
 *          由java命令启动JVM，JVM启动相当于启动一个进程；随后由该进程创建一个主线程去调用main方法。
 *
 *          JVM虚拟机的启动是多线程的：（最低两个线程）
 *                  “主线程”+“垃圾回收线程”也要先启动，否则会出现内存溢出。
 *
 */

//目的：实现多线程的程序

//因为线程依赖于进程而存在，所以要先创建一个进程；
// 进程是由系统创建的，所以要先去调用系统功能创建一个进程
//Java不能直接调用系统功能，所以没有办法直接实现多线程程序
//但是Java可以调用C/C++写好的程序实现多线程程序
//C/C++提供一些类供Java实现多线程------->Thread类

//两种实现多线程程序的方式
//方式一：继承Thread类
//      1、自定义类继承Thread类
//      2、自定义类中重写run()方法------>不是类中的所有代码都要被线程执行，run()方法是为了包含那些需要被线程执行的代码  ||直接调用run()方法相当于普通方法的调用，所以看到的是单线程的效果
//      3、创建对象
//      4、启动线程
public class Theory extends Thread {        //1、自定义类继承Thread类
    @Override
    public void run() {     //2、自定义类中重写run()方法
        //一般来说，被执行的代码肯定是比较耗时的，所以需要循环来改进
        for (int x= 0; x < 100; x++) {
            //getName()获取线程名称
            System.out.println(getName() + "---" + x);
        }
    }

    public static void main(String[] args) {
        /*Theory theory = new Theory();       //3、创建对象
        theory.run();                       //4、启动线程
        theory.run();
        //直接调用run()方法相当于普通方法的调用，所以看到的是单线程的效果    想要看到多线程执行，需要用到start()方法*/

        /*面试题：run()和start()的区别
        run()仅仅封装被线程执行的代码，直接调用是普通方法（未启动线程）       可重复调用
        start()1、首先启动了线程，2、然后由JVM去调用该线程的run()方法      不可重复调用
        */

        //获取当前main线程的名称
        System.out.println(Thread.currentThread().getName());
        Theory theory = new Theory();//线程0 Thread0
        theory.setName("设置名称线程0-");
        theory.start();
        Theory t1 = new Theory();//线程1 Thread1
        t1.setName("设置名称线程1-");
        t1.start();
    }
}
