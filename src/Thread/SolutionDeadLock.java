package Thread;

/**
 * @Author: Daryliu
 * @Date: 2020/8/29 17:07
 * @param:线程间通信：（不同种类的线程对同一个资源操作）生产者（设置线程）---（同一资源）---(获取线程)消费者
 * @return:
 */

    /*
    资源类：student
    设置学生数据：SetThread（生产者）
    获取学生数据：GetThread（消费者）
    测试类：SolutionDeadLock
    */
public class SolutionDeadLock {
}

class GetThread {//获取学生数据：GetThread（消费者）

}

class SetThread {//设置学生数据：SetThread（生产者）

}

class Student {//资源类：student
    String name;
    int age;
}
