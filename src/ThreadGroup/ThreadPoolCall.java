package ThreadGroup;

import java.util.concurrent.*;

/**
 * @Author: Daryliu
 * @Date: 2020/9/25 19:32
 * @return:
 * *          创建线程池的代码：
 *  *                  1、创建一个线程池对象，控制要创建的几个线程对象
 *  *                  2、这种线程池的线程可以执行runnable和callable对象代表的线程
 *  *                          做一个类实现runnable接口
 *  *                  3、调用如下方法即可：
 *  *                          3.1 Future<?> submit(Runnable task)     run方法是返回的void
 *  *                          3.2 <T> Future<T> submit(Callable<T> task)   "本例实现Callable（带‘返回值’ 泛型的）接口"
 *  *                  4、结束线程池
 */

            /*实现求和的多线程操作
            * 每个线程执行部分求和操作
            * */
public class ThreadPoolCall implements Callable<Integer> {
    private int num;

    public ThreadPoolCall(int num) {
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {//runnable不可抛异常，但是callable可以

        int sum = 0;

        for (int x = 1; x <= num; x++) {
            sum += x;
        }
        return sum;//该泛型指的是返回值的类型
    }
}


class callDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建线程池对象  包含2个线程
        ExecutorService pool = Executors.newFixedThreadPool(2);

        //可以执行runnable或者callable对象代表的线程
        //Future<>用来接收get方法计算的返回值
        Future<Integer> f1 = pool.submit(new ThreadPoolCall(100));
        Future<Integer> f2 = pool.submit(new ThreadPoolCall(50));

        //V get()
        Integer i1 = f1.get();
        Integer i2 = f2.get();

        System.out.println(i1);
        System.out.println(i2);

        //结束线程
        pool.shutdown();
    }
}
