package javapractice.thread.juc;

import java.io.FileInputStream;
import java.util.concurrent.Semaphore;

/**
 * 信号量
 */
public class SemaphoreTest {

    /*
    semaphore的中文意思就是信号，它的作用也类似于一个信号，传统情况下添加锁操作后，同一时刻只允许一个线程来访问我们的代码，
    semaphore的话可以传入一个值，这个值代表了允许访问代码的最大线程数，也就是说使用semaphore来控制代码块是允许多个线程来访问的。
    */

    public static void main(String[] args) {

        int total = 6;
        Semaphore semaphore = new Semaphore(2,true);
//        Semaphore semaphore = new Semaphore(2);

        for (int i = 0 ; i < total ; i++){
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("线程" + Thread.currentThread().getName() + "开始执行业务");
                    Thread.sleep(3000);
                    System.out.println("线程" + Thread.currentThread().getName() + "执行业务完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            },"Thread--" + i).start();
        }
    }
}
