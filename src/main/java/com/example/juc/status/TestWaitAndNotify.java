package com.example.juc.status;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class TestWaitAndNotify implements Runnable {
    public  AtomicInteger i = new AtomicInteger(10000);
    public ReentrantLock lock = new ReentrantLock(false);

    public static void main(String[] args) {
        TestWaitAndNotify t = new TestWaitAndNotify();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
    }

    @SneakyThrows
    @Override
    public void run() {
        int j;
        while ((j = i.decrementAndGet()) > 0) {
                //Thread.sleep(1000);
            System.out.println(String.format("执行线程：%s，卖出：%s", Thread.currentThread().getName(), j));
        }
    }
}
