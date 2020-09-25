package ThreadGroup;

/**
 * @Author: Daryliu
 * @Date: 2020/9/25 20:04
 * @param:一般在开发中随时添加线程可以采用“匿名内部类”的方式
 *
 * 匿名内部类的格式：
 *          new 类名/接口名() {
 *              重写方法;
 *          };
 *          本质是：该类或者接口的子类对象
 * @return:都是通过匿名内部类实现多线程
 */

public class InnerClassDemo {
    public static void main(String[] args) {
        //使用继承Thread类实现多线程
        new Thread() {                  //匿名内部类
            @Override
            public void run() {
                for (int x=0;x<5;x++){
                    System.out.println(x);
                }
            }
        }.start();

        //实现Runnable接口来实现多线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x=0;x<5;x++){
                    System.out.println(x);
                }
            }
        }) {

        }.start();


        //因为实现runnable接口时是在runnable中重写的run方法，而不是在匿名内部类中写的，而其内部也可以重写run方法，若“两者都重写了则不会报错，输出的是匿名内部类的值”
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x=0;x<5;x++){
                    System.out.println("hello" +":"+x);
                }
            }
        }) {
            @Override
            public void run() {
                for (int x=0;x<5;x++){
                    System.out.println("world" +":"+x);
                }
            }
        }.start();
    }
}
