package Thread;

/**
 * @Author: Daryliu
 * @Date: 2020/8/31 17:55
 * @describe:等待唤醒机制 线程间通信2未能解决的问题：   1、若消费者先抢到cpu的执行权，就会去消费数据，但是初始数据是默认值（空）；应该等数据有意义再消费
 * 2、若生产者先抢到cpu执行权，就会产生数据；但是产生完数据后还有执行权，会继续产生数据。应该等消费者消费完再生产。
 * --------------------正常思路：--------------------------
 * 1、生产者：先看是否有数据，有就等待，没有就生产，生产完之后通知消费者消费
 * 2、消费者：先看是否有数据，有就消费，没有就等待，消费完之后通知生产者生产
 * <p>
 * 实现上面思路的方式：等待唤醒机制！！！！！！！
 * @return:而今虽然数据安全了，但是执行的时候一下出来一大片而且不停止------>可通过“等待唤醒机制”解决
 * 因为是任意锁，所以是Object类中提供(wait()、notify()、notifyAll()函数)，这些方法的调用必须通过锁对象！！！！
 */


//(资源类)缓冲区类：student
class StudentWaitWake {
    //优化   不让其他人随意修改
    private String name;
    private int age;
    private boolean flag;//默认情况下没有数据，没给就是false

    public synchronized void set(String name,int age) {     //因为成员都私有了，所以用方法来调用；且要求同步，每次只能一个线程进入
        if (this.flag) {//有数据就等待
            try {
                this.wait();//同步方法对象用this
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //没有数据就设置数据
        this.name = name;
        this.age = age;

        //修改标记
        this.flag = true;
        //唤醒
        this.notify();
    }

    public synchronized void get() {
        if (!this.flag) {//如果没有数据就等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //有数据就消费数据
        System.out.println(this.name + "---" +this.age);

        //修改标记
        this.flag = false;
        //唤醒
        this.notify();
    }
}

//设置学生数据：SetThread（生产者）
class SetThreadWake implements Runnable {
    private StudentWaitWake s;
    private int x = 0;

    public SetThreadWake(StudentWaitWake s) { //使用构造函数将资源类的对象传递进来，保证只用一个资源
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
          /*  synchronized (s) {//s保证了是同一把锁
                //判断有没有数据
                if (s.flag) {
                    try {
                        s.wait();//有就等待-------并释放锁；醒过来的时候从此处醒过来，而不是重新跑一遍
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
                if (x % 2 == 0) {
                    s.set("ldy",12);
                    /*s.name = "ldy";//可能走到这之后被抢到了执行权，内存中age还是11，但是name变成了ldy
                    s.age = 12;*/
                } else {
                    s.set("wwx",11);
                    /*s.name = "wwx";//可能走到这之后被抢到了执行权，内存中age还是12，但是name变成了wwx
                    s.age = 11;*/
                }
                x++;
            /*
                s.flag = true;//因为生产者已经生产了数据，修改标记为true
                //唤醒消费线程----唤醒不代表立马可以执行，还需要抢占cpu执行权
                s.notify();
            }*/
        }
    }
}

//获取学生数据：GetThread（消费者）
class GetThreadWake implements Runnable {
    private StudentWaitWake s;

    public GetThreadWake(StudentWaitWake s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            s.get();
            /*synchronized (s) {
                if (!s.flag) {//没有数据就等待------并释放锁；醒过来的时候从此处醒过来，而不是重新跑一遍
                    try {
                        s.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("姓名：" + s.name + "年龄：" + s.age);//有数据就消费*/

                /*s.flag = false;//消费完就修改标记为false，表示没数据
                s.notify();//唤醒生产线程
            }*/
        }
    }
}

//测试类
public class WaitWakeUp {

    public static void main(String[] args) {
        StudentWaitWake s = new StudentWaitWake();//在外部声明一个资源类，保证只使用一个资源类缓冲区

        SetThreadWake setThread = new SetThreadWake(s);//生产者
        GetThreadWake getThread = new GetThreadWake(s);//消费者
        //线程类
        Thread t1 = new Thread(setThread);
        Thread t2 = new Thread(getThread);
        //启动线程
        t1.start();
        t2.start();//两个线程随机抢占
    }
}