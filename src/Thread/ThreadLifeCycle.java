package Thread;

/**
 * @Author: Daryliu
 * @Date: 2020/8/28 19:47
 * @describe:线程的生命周期
 *      新建：创建线程对象
 *      就绪：有执行资格，没有执行权
 *      运行：有执行资格，有执行权（若被其他线程抢到CPU执行权则回到就绪状态）
 *          阻塞：由于一些操作sleep()、wait()让线程处于没有执行资格和没有执行权的状态（可激活sleep()时间到/notify()，并处于就绪状态）
 *      死亡：线程对象变成垃圾等待被回收
 */
public class ThreadLifeCycle {
}
