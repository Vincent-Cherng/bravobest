package javapractice.thread.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 正计数器（可循环使用的栅栏）
 */
public class CyclicBarrierTest {

    /*
    CyclicBarrier是一个正计数器，它也可以通过构造函数来指定计数器的大小，
    同时CyclicBarrier在构造函数中还可以传递一个线程进去，当计数器达到指定阈值后，
    先执行传递的线程，再执行每个线程的后续操作，同时CyclicBarrier是可重复利用度。
    */

    /*
    CountDownLatch和CyclicBarrier的区别
        1.CountDownLatch是倒计数，CyclicBarrier是正计数
        2.CountDownLatch不可复用，CyclicBarrier可复用，CyclicBarrier构造函数还可传入一个线程
        3.CountDownLatch是多个线程达到临界点后某一线程向下执行，CyclicBarrier则是多个线程达到临界点后一起向下执行
    */


    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,new Thread(() -> System.out.println("每个线程全部执行完成了！继续向后执行准备......")));

        for (int i = 0;i < 7 ;i++){
            new Thread(() -> {
                System.out.println("线程" + Thread.currentThread().getName() + "开始执行！");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "接着执行！");
            },"Thread--" + i).start();
        }

        //上面执行完了，下面可接着使用此CyclicBarrier对象，意为可循环使用

    }
}
