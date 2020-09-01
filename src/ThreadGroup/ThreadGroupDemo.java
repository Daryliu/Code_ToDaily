package ThreadGroup;

/**
 * @Author: Daryliu
 * @Date: 2020/9/1 14:44
 * @describe:线程组：直接对一批线程进行分类管理（虽然Java没有直接操作线程的类，但是可以直接操作线程组）-------!!!所有线程默认属于main线程组!!!
 * @return:修改线程所在的组：
 *         1、创建线程所在的组
 *         2、创建线程时将线程组指定为自己创建的线程组
 */
public class ThreadGroupDemo implements Runnable{
    @Override
    public void run() {
        for (int x = 0; x < 100; x ++) {
            System.out.println(Thread.currentThread().getName() + x);
        }
    }
}

class ThreadGroupTest {
    public static void main(String[] args) {
        defalutThreadGroup();

        ownerThreadGroup();
    }

    /*自己创建的线程组*/
    private static void ownerThreadGroup() {
        //修改线程所在的组
        //1、创建线程所在的组
        ThreadGroup tg = new ThreadGroup("自己创建的组");
        //2、创建线程时将线程组指定为自己创建的线程组    Thread(ThreadGroup group,Runnable target,String name)
        ThreadGroupDemo  threadGroupDemo = new ThreadGroupDemo();
        Thread t3 = new Thread(tg,threadGroupDemo,"ldy");
        Thread t4 = new Thread(tg,threadGroupDemo,"wwx");

        System.out.println(t3.getThreadGroup().getName());
        System.out.println(t4.getThreadGroup().getName());
        //通过组名称设置全部组中的线程为后台线程
        tg.setDaemon(true);
        //设置整个线程组的优先级
        tg.setMaxPriority(10);
    }

    /*默认线程组*/
    private static void defalutThreadGroup() {
        ThreadGroupDemo threadGroupDemo = new ThreadGroupDemo();
        //创建2个线程
        Thread t1 = new Thread(threadGroupDemo,"ldy");
        Thread t2 = new Thread(threadGroupDemo,"wwx");

        //线程类中的方法：返回线程所在的线程组getThreadGroup()
        ThreadGroup threadGroup =  t1.getThreadGroup();
        ThreadGroup threadGroup1 =  t2.getThreadGroup();

        //线程组中的方法：返回线程所在线程组的名称getName()
        String name1 = threadGroup.getName();
        String name2 = threadGroup1.getName();
        System.out.println(name1);
        System.out.println(name2);
        System.out.println(Thread.currentThread().getThreadGroup().getName());
    }
}