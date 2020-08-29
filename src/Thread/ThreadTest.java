package Thread;

/**
 * @Author: Daryliu
 * @Date: 2020/8/28 20:44
 * @return:线程程序练习：某电影院共100张票，三个窗口卖，设计程序模拟卖票
 * @return:“线程安全问题”（1、是否是多线程环境2、是否是共享数据3、是否有多条语句操作共享数据） 解决线程安全问题：想办法解决第三条，因为前两天解决不了
 * ！！把多条语句操作共享数据的代码包成一个整体，让某个线程执行的时候其他线程不能进入
 * -------------使用“同步机制”解决线程安全问题：----------------
 *      synchronized(对象){    任意对象！！    (该对象如同锁的功能,多个线程同一把锁)
 *          需要同步的代码;
 *      }
 *
 *      同步的特点和利弊：
 *             同步的前提：1、多个线程 2、多个线程使用的是同一个对象
 *             同步的好处：同步的出现解决了多线程的安全问题
 *             同步的弊端：当线程相当多时，每个线程都会去判断同步上的锁，耗费资源，降低运行效率
 *
 *       “同步方法”的格式和锁对象问题：
 *              把同步关键字加载方法上：private synchronized void Test(){}
 *              “同步方法的锁对象”：this
 *
 *        “静态方法”及锁对象问题：
 *              “静态方法”的锁对象：当前类的.class（即类的字节码文件对象）
 */

//方法二实现
public class ThreadTest implements Runnable {
    private int tickets = 100;
    private Object obj = new Object();//创建锁对象----多个线程同一把锁
    @Override
    public void run() {
        while (true) {
            //使用同步机制解决线程安全问题
            synchronized (obj) {
                if (tickets > 0) {
                    //【线程安全问题】为了模拟真实场景：因为网络售票不是实时的，需要延迟时间【延迟可能会导致重复买票（因为前面的线程睡着了，执行后面的线程）或者负数票】
                    //满足线程安全问题的条件
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "正在出售" + (tickets--) + "张票");
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadTest t = new ThreadTest();//之创建了一次，所以之创建了tickets一次
        Thread t1 = new Thread(t, "窗口1");
        Thread t2 = new Thread(t, "窗口2");
        Thread t3 = new Thread(t, "窗口3");
        t1.start();
        t2.start();
        t3.start();
    }
}


//方法一实现
/*public class ThreadTest extends Thread{
    private static int tickets = 100;//让多个线程对象共享总共100张票，所以要用static
    @Override
    public void run() {
        while (true){//为了模拟一直有票
            if (tickets > 0) {
                System.out.println(getName()+"正在出售" + (tickets--) +"张票");
            }
        }
    }

    public static void main(String[] args) {
        //创建三个窗口
        ThreadTest t1 = new ThreadTest();
        ThreadTest t2 = new ThreadTest();
        ThreadTest t3 = new ThreadTest();
        //给线程对象起名
        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");
        //启动线程
        t1.start();
        t2.start();
        t3.start();
    }
}*/