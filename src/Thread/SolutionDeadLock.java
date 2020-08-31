package Thread;

/**
 * @Author: Daryliu
 * @Date: 2020/8/29 17:07
 * @param:线程间通信：（不同种类的线程对同一个资源操作）生产者（设置线程）---（同一资源）缓冲区---(获取线程)消费者
 * 缓冲区实现了生产者和消费者分离：生产者线程只需要往缓冲区里面放置数据，而不需要管消费者消费的情况;同样，消费者只需要从缓冲区拿数据处理即可，也不需要管生产者生产的情况
 * @return:
 */

    /*
    资源类：student
    设置学生数据：SetThread（生产者）
    获取学生数据：GetThread（消费者）
    测试类：SolutionDeadLock

    生产者和消费者类中不能都创建新的资源对象，他们应该使用同一个资源类！否则会出现打印的代码为null和0
     如何实现生产者和消费者公用一个资源缓冲区？
            在外界创建好，通过构造方法传递给其他的类
    */

class Student {//(资源类)缓冲区类：student
    String name;
    int age;
}

class SetThread implements Runnable{//设置学生数据：SetThread（生产者）
    private Student s;
    public SetThread(Student s) { //使用构造函数将资源类的对象传递进来，保证只用一个资源
        this.s = s;
    }
    @Override
    public void run() {
        s.name = "ldy";
        s.age = 12;
    }
}

class GetThread implements Runnable{//获取学生数据：GetThread（消费者）
    private Student s;
    public GetThread(Student s) {
        this.s = s;
    }
    @Override
    public void run() {
        System.out.println("姓名："+s.name + "年龄：" +s.age);
    }
}

public class SolutionDeadLock {//测试类
    public static void main(String[] args) {
        Student s = new Student();//在外部声明一个资源类，保证只使用一个资源类缓冲区
        SetThread setThread = new SetThread(s);//生产者
        GetThread getThread = new GetThread(s);//消费者
        //线程类
        Thread t1 = new Thread(setThread);
        Thread t2 = new Thread(getThread);
        //启动线程
        t1.start();
        t2.start();
    }
}
