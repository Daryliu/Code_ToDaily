package Thread;

/**
 * @Author: Daryliu
 * @Date: 2020/8/28 19:59
 * @param：多线程实现方式一：继承Thread类
 * //      1、自定义类继承Thread类
 * //      2、自定义类中重写run()方法------>不是类中的所有代码都要被线程执行，run()方法是为了包含那些需要被线程执行的代码  ||直接调用run()方法相当于普通方法的调用，所以看到的是单线程的效果
 * //      3、创建对象
 * //      4、启动线程
 * @return:多线程实现方式二：声明类实现runnable接口-----------解决了单继承的局限性；适合于多个相同的程序代码解决同一个资源的情况，把线程同程序的代码、数据分离，体现了面向对象的设计思想（见第三步/30行）
 *          1、自定义类实现runnable接口
 *          2、重写run()方法
 *          3、创建自定义类的对象
 *          4、创建Thread类的实例对象，并将（第三步中的对象）自定义类的对象作为参数传递进来
 */


public class Theory2 implements Runnable{       //1、自定义类实现runnable接口
    @Override
    public void run() {     //2、重写run()方法
        for (int x= 0; x < 100; x ++) {
            //由于使用接口的方式不能直接用geyName方法，所以使用Thread.currentThread().getName()
            System.out.println(Thread.currentThread().getName() + "：" + x);
        }
    }


    public static void main(String[] args) {
        Theory2 t = new Theory2();     //3、创建自定义类的对象
        Thread t1 = new Thread(t);  //4、创建Thread类的实例对象，并将（第三步中的对象）自定义类的对象作为参数传递进来
        Thread t2 = new Thread(t);
        t1.setName("一一");
        t2.setName("二二");//可直接写为Thread t2 = new Thread(t,"线程名");

        t1.start();
        t2.start();
    }
}
