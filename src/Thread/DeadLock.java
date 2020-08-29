package Thread;

/**
 * @Author: Daryliu
 * @Date: 2020/8/29 15:13
 *         同步弊端：1、效率低   2、若出现同步嵌套，易出现死锁问题
 * @param:死锁问题：多个线程各自占有一些共享资源，并且互相等待其他线程占有的资源才能进行，而导致两个或者多个线程都在等待对方释放资源，都停止执行的情形。
 * @return:
 */
public class DeadLock extends Thread{
    private boolean flag;

    public DeadLock(boolean flag) {
        this.flag = flag;
    }
    @Override
    public void run() {
        if (flag) {
            synchronized (lock.obj) {
                System.out.println("if objA");//有可能obj执行到这之后开始直接下面else中的obj1
                synchronized (lock.obj1) {
                    System.out.println("if objB");
                }
            }
        }else {
            synchronized (lock.obj1) {
                System.out.println("else objB");//而此时obj1也需要obj的东西
                synchronized (lock.obj) {
                    System.out.println("else objA");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock(true);
        DeadLock deadLock1 = new DeadLock(false);
        deadLock.start();
        deadLock1.start();
    }
}

class lock {
    //创建2个锁对象   加final是为了将来不变
    public static final Object obj = new Object();
    public static final Object obj1 = new Object();
}