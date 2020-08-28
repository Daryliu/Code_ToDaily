package Thread;

/**
 * @Author: Daryliu
 * @Date: 2020/8/28 20:44
 * @return:线程程序练习：某电影院共100张票，三个窗口卖，设计程序模拟卖票
 */

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


//方法二实现
public class ThreadTest implements Runnable{
    private int tickets = 100;
    @Override
    public void run() {
        while (true){
            if (tickets > 0) {
                System.out.println(Thread.currentThread().getName()+"正在出售"+(tickets--) + "张票");
            }
        }
    }

    public static void main(String[] args) {
        ThreadTest t = new ThreadTest();//之创建了一次，所以之创建了tickets一次
        Thread t1 = new Thread(t,"窗口1");
        Thread t2 = new Thread(t,"窗口2");
        Thread t3 = new Thread(t,"窗口3");
        t1.start();
        t2.start();
        t3.start();
    }
}
