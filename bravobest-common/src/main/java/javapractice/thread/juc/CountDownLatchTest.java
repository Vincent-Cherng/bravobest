package javapractice.thread.juc;


import java.util.concurrent.CountDownLatch;

/**
 * 倒数计数器
 */
public class CountDownLatchTest {

    /*每次调用countDown方法都会让计数器减一，当计数器为0后会执行await后的代码。
    * 每个线程执行完成后一起执行后面的代码
    * */

    public static void main(String[] args) {

        int count = 3;
        CountDownLatch countDownLatch = new CountDownLatch(count);

        for (int i = 0;i < count; i++){
            new Thread(() -> {
                System.out.println("线程" + Thread.currentThread().getName() + "开始执行！");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "结束执行！");
                countDownLatch.countDown();
            },"Thread-" + i).start();
        }
        System.out.println("主线程开始执行！");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程结束执行！");

    }
}
