package Thread;

/**
 * @Author: Daryliu
 * @Date: 2020/8/28 16:21
 * @describe: 线程调度和线程优先级
 *      分时调度模型：所有线程轮流使用CPU，平均分配CPU时间片
 *      抢占式调度模型：优先级高的优先，同一优先级则随机选。线程高的使用CPU时间多【Java使用抢占式】
 *
 *      线程默认优先级5   范围是1-10
 */
public class Priority extends Thread{
    @Override
    public void run() {
        for (int x = 0; x < 100; x ++) {
            System.out.println(getName() + x);
        }
    }


    //设置setPriority()和获取getPriority()线程对象的优先级
    public static void main(String[] args) {
        Priority p0 = new Priority();
        Priority p1 = new Priority();
        Priority p2 = new Priority();
        //设置线程的名字
        p0.setName("零");
        p1.setName("壹");
        p2.setName("贰");

        //设置线程对象的优先级  默认5
        p0.setPriority(1);
        p1.setPriority(5);
        p2.setPriority(10);
        //获取线程对象的优先级
        System.out.println(p0.getPriority());
        System.out.println(p1.getPriority());
        System.out.println(p2.getPriority());

        p0.start();
        p1.start();
        p2.start();
    }
}
