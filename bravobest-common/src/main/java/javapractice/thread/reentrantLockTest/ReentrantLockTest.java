package javapractice.thread.reentrantLockTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockTest {

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private boolean has = false;

    private Lock fairLock = new ReentrantLock(true);//公平锁
    private Lock unFairLock = new ReentrantLock();//非公平锁

    //读写锁
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    private void lockTest(){
        lock.lock();
        try{
            for (int i = 0;i <10 ; i++){
                System.out.println("线程"+ Thread.currentThread().getName() + "---打印---" + i);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }


    private void lockInterruptiblyTest() throws InterruptedException {
        /*
        lockInterruptibly()为中断方法，当通过这个方法去获取锁时，如果线程正在处于获取锁状态，
         则该线程能够响应中断（中断线程的等待状态），抛出中断异常。也就使说，当两个线程同时通过
         lock.lockInterruptibly()获取某个锁时，如果线程A获取到了锁，而线程B在等待状态，
         那么对线程B调用threadB.interrupt()方法能够中断线程B的等待状态
         */
        lock.lockInterruptibly();
        try{
            System.out.println("线程"+ Thread.currentThread().getName() + "获得锁！");
            while (true) {
//                System.out.println("线程"+ Thread.currentThread().getName() + "---打印---");
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("线程"+ Thread.currentThread().getName() + "异常！");
        }
        finally {
            lock.unlock();
            System.out.println("线程"+ Thread.currentThread().getName() + "释放锁！");
        }
    }

    private void conditionAwaitTest(){
        lock.lock();
        try{
            System.out.println("线程" + Thread.currentThread().getName() + "获得了锁");
            System.out.println("线程" + Thread.currentThread().getName() + "等待");
            //等待
            condition1.await();
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            System.out.println("线程" + Thread.currentThread().getName() + "释放了锁");
            lock.unlock();
        }
    }

    private void conditionSignalTest(){
        lock.lock();
        try{
            System.out.println("线程" + Thread.currentThread().getName() + "获得了锁");
            System.out.println("线程" + Thread.currentThread().getName() + "唤醒");
            //通知
            condition1.signal();
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            System.out.println("线程" + Thread.currentThread().getName() + "释放了锁");
            lock.unlock();
        }
    }


    /**
     * 生产
     */
    private void produce(){
        lock.lock();
        try{
//            System.out.println("线程" + Thread.currentThread().getName() + "获得了锁");
            while (has == true){
                condition1.await();//有就等待暂时不生产
            }
            System.out.println("线程" + Thread.currentThread().getName() + "开始生产");
            has = true;//开始生产
            condition1.signal();//通知消费
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    /**
     * 消费
     */
    private void consume() {
        lock.lock();
        try {
//            System.out.println("线程" + Thread.currentThread().getName() + "获得了锁");
            while (has == false) {
                condition1.await();//没有了暂停消费
            }
            System.out.println("线程" + Thread.currentThread().getName() + "开始消费");
            has = false;//消费完了
            condition1.signal();//通知生产
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void fairLockTest(){
        fairLock.lock();
        System.out.println(Thread.currentThread().getName() + "获得了锁！");
        fairLock.unlock();
    }

    private void unFairLockTest(){
        unFairLock.lock();
        System.out.println(Thread.currentThread().getName() + "获得了锁！");
        unFairLock.unlock();
    }

    private void readLockTest() throws InterruptedException {
        readWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "获得了读锁！");
        // 睡眠，保证B线程进来时A线程还是获取锁状态
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + "释放了读锁！");
        readWriteLock.readLock().unlock();
    }

    private void writeLockTest() throws InterruptedException {
        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "获得了写锁！");
        // 睡眠，保证B线程进来时A线程还是获取锁状态
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + "释放了写锁！");
        readWriteLock.writeLock().unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        //1.lock和unlock的使用
        /*
        ReentrantLockTest lockTest = new ReentrantLockTest();
        //线程1
        System.out.println("线程1准备");
        new Thread(() -> lockTest.lockTest()).start();
        //线程2
        System.out.println("线程2准备");
        new Thread(() -> lockTest.lockTest()).start();
        */


        //2.使用lockInterruptibly
        /*
        ReentrantLockTest lockTest1 = new ReentrantLockTest();
        Thread t1 = new Thread(() -> {
            try {
                lockTest1.lockInterruptiblyTest();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("线程1中断！");
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                lockTest1.lockInterruptiblyTest();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("线程2中断！");
            }
        });

        t1.setName("T1");
        t2.setName("T2");

        t1.start();
        t2.start();
        Thread.sleep(2000);

        //在t2线程等待锁的时候，将其中断
        t2.interrupt();
        */

        //3.利用 Condition的await()方法和signal()方法实现等待通知机制；
        /*
        ReentrantLockTest lockTest2 = new ReentrantLockTest();
        Thread t1 = new Thread(() -> lockTest2.conditionAwaitTest());
        t1.setName("T1");
        t1.start();
        t1.sleep(3000);
        //唤醒
        lockTest2.conditionSignalTest();
        */


        //4.使用Condition实现生产消费模式
        /*
        ReentrantLockTest lockTest3 = new ReentrantLockTest();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                lockTest3.produce();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                lockTest3.consume();
            }
        });
        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
        */

        //Lock 锁分为公平锁和非公平锁，公平锁表示通过线程加锁的顺序获取锁，非公平锁表示通过抢占机制获取锁
        //5.公平锁
        /*ReentrantLockTest lockTest4 = new ReentrantLockTest();
        for (int i = 0;i < 200;i++){
            Thread thread = new Thread(() -> lockTest4.fairLockTest());
            thread.setName(i + "");
            thread.start();
        }*/

        //6.非公平锁
        /*ReentrantLockTest lockTest5 = new ReentrantLockTest();
        for (int i = 0;i < 200;i++){
            Thread thread = new Thread(() -> lockTest5.unFairLockTest());
            thread.setName(i + "");
            thread.start();
        }*/


       /*ReentrantLock 与  synchronized 都是 独占锁， 安全性较高，但是相对来说效率低下；
        ReentrantReadWriteLock读写锁提供了 readLock()和writeLock()用来获取读锁和写锁；
        读锁之间读取数据不互斥（故读锁也成为共享锁）。读锁写锁之间互斥，写锁写锁之间互斥；*/
        //7.读锁与读锁
        /*
        ReentrantLockTest lockTest6 = new ReentrantLockTest();
        Thread t1 = new Thread(() -> {
            try {
                lockTest6.readLockTest();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.setName("线程1");
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                lockTest6.readLockTest();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.setName("线程2");
        t2.start();
        */

        //8.写锁与写锁
       /* ReentrantLockTest lockTest7 = new ReentrantLockTest();
        Thread t1 = new Thread(() -> {
            try {
                lockTest7.writeLockTest();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.setName("线程1");
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                lockTest7.writeLockTest();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.setName("线程2");
        t2.start();*/

        //9.读锁与写锁
        ReentrantLockTest lockTest8 = new ReentrantLockTest();
        Thread t1 = new Thread(() -> {
            try {
                lockTest8.readLockTest();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.setName("线程1");
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                lockTest8.writeLockTest();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.setName("线程2");
        t2.start();

    }
}
