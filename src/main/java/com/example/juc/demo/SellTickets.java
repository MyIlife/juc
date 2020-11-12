package com.example.juc.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 两种方式实现卖票
 * 1.加锁（这里把synchronized和ReentrantLock当成一种）
 * 2.cas（原子类）
 */
public class SellTickets {
    public static int i = 1000;
    public static AtomicInteger ai = new AtomicInteger(1000);
    static class LockSell implements Runnable{

        @Override
        public void run() {
            while(i>0){
                synchronized (this){
                    if(i>0){
                        i--;
                        System.out.println(String.format("当前线程：%s,卖出票号：%s",Thread.currentThread().getName(),i));
                    }
                }
            }
        }
    }
   static class AtomicSell implements Runnable{

        @Override
        public void run() {
            int si;
            while ((si = ai.decrementAndGet())>0){
                System.out.println(String.format("当前线程：%s,卖出票号：%s",Thread.currentThread().getName(),si));
            }
        }
    }

    public static void main(String[] args) {
        Runnable LSell = new LockSell();
        Runnable ASell = new AtomicSell();
        for (int i = 0; i < 5; i++) {
            new Thread(ASell,"thread"+i).start();
        }
    }
}
