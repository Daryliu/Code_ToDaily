package Thread;

import java.util.Date;

/**
 * @Author: Daryliu
 * @Date: 2020/8/28 17:03
 * @describe:线程控制
 *      1、休眠线程sleep():在指定的毫秒数加指定的纳秒数内让当前正在执行的线程休眠（暂停执行）。该线程不丢失任何监视器的所属权。
 *      2、线程加入join()：等待该线程终止后才继续执行其他线程
 *      3、线程礼让yield()：暂停当前正在执行的线程对象，并执行其他线程,（使线程执行更和谐但是不能保证线程一人一次）
 *      4、后台线程setDaemon()：(设置为后台/守护线程后，主线程结束则守护线程结束)将该线程标记为守护线程或用户线程。当正在运行的线程都是守护线程时，Java 虚拟机退出。
 *         该方法必须在启动线程前调用。
 *      5、中断线程stop()：睡眠超过时间后就中断线程线程,后面的代码都不再执行，不建议使用
 *          interrupt()中断当前执行，但是会跳到InterruptedException异常中
 */
public class ThreadControl extends Thread{
    @Override
    public void run() {
        for (int x = 0; x < 100; x ++) {
            System.out.println(getName() + x + ",日期：" +new Date());

            //休眠
            try {
                Thread.sleep(10000);//休眠1秒后执行
            } catch (InterruptedException e) {
                System.out.println("线程被终止了");
            }

            //礼让
            Thread.yield();
        }
    }

    //public static void sleep(long millis,int nanos)throws InterruptedException休眠函数
    public static void main(String[] args) throws InterruptedException {
        ThreadControl t1 = new ThreadControl();
        ThreadControl t2 = new ThreadControl();
        ThreadControl t3 = new ThreadControl();

        t1.setName("一一一");
        t2.setName("二二二");
        t3.setName("三三三");

        //后台线程：启动线程前调用
        t1.setDaemon(true);
        t2.setDaemon(true);
        t3.setDaemon(true);

        t1.start();
        //t1终止后执行其他线程
        t1.join();//线程加入
        t2.start();
        t3.start();

        try{
            Thread.sleep(3000);
            t1.stop();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }



        Thread.currentThread().setName("主线程");//用于后台线程的主线程
        for (int x = 0; x< 5; x++){
            System.out.println(Thread.currentThread().getName()+x);
        }
    }
}
