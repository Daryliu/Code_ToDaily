package ThreadGroup;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Daryliu
 * @Date: 2020/9/1 16:55
 * @describe:线程池
 *          启动一个新线程成本很高，会涉及到和操作系统的交互；
 *          使用线程池来提高性能（----------用线程池来存储“大量”、“生存周期很短”的线程------------）
 *          线程池中线程结束后会回到线程池中变为空闲状态，等待下一个对象使用（可以只是用少量的线程进行多次不同的执行）提高性能
 *
 *          Executor工厂类专门来生产线程池，以下方法都返回ExecutorService对象，其可以执行runnable和callable对象代表的线程
 *                  ExecutorService newCacheThreadPool()
 *                  ExecutorService newFixedThreadPool(int nThreads)
 *                  ExecutorService newSingleThreadExecutor()
 *
 *          创建线程池的代码：
 *                  1、创建一个线程池对象，控制要创建的几个线程对象
 *                  2、这种线程池的线程可以执行runnable和callable对象代表的线程
 *                          做一个类实现runnable接口
 *                  3、调用如下方法即可：
 *                          3.1 Future<?> submit(Runnable task)
 *                          3.2 <T> Future<T> submit(Callable<T> task)
 *                  4、结束线程池
 * @return:
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        /*1、创建一个线程池对象，控制要创建的几个线程对象*/
        ExecutorService pool = Executors.newFixedThreadPool(2);
        /*2、这种线程池的线程可以执行runnable和callable对象代表的线程*/
        /*调用方法*/
        pool.submit(new MyRunnale());//线程池内第一个线程
        pool.submit(new MyRunnale());//线程池内第二个线程
        //打印出来是pool-1-thread-2:90表示线程池1里面的线程2-------------不会结束，因为线程会回到线程池内！！！！
        //pool-1-thread-1:12
        /*结束线程*/
        pool.shutdown();
    }
}

class MyRunnale implements Runnable{
    @Override
    public void run() {
        for (int x = 0; x < 100; x++) {
            System.out.println(Thread.currentThread().getName() +":"+ x);
        }
    }
}
