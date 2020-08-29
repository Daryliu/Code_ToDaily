package Thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Daryliu
 * @Date: 2020/8/29 11:03
 * @param:为了看清在哪里加锁和释放锁：JDK5之后使用锁对象Lock（提供了比synchronized方法更多的锁定操作）
 * @return:lock()获取锁;unlock()释放锁
 * @describe:ReentrantLock是Lock的实现类
 */
public class ThreadLock implements Runnable {
    private int tickets = 100;
    //定义锁对象
    private Lock lock = new ReentrantLock();//ReentrantLock是Lock的实现类

    @Override
    public void run() {
        while (true) {
            try {
                //加锁
                lock.lock();
                if (tickets > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "正在售票" + (tickets--) + "张票");
                }
            } finally {
                //释放锁
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ThreadLock l = new ThreadLock();

        Thread t1 = new Thread(l, "窗口1");
        Thread t2 = new Thread(l, "窗口2");
        Thread t3 = new Thread(l, "窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
