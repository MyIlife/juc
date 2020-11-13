package com.example.juc.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo implements Runnable{
    public ReentrantLock lock;
    public Condition condition = lock.newCondition();
    public ReentrantLockDemo(boolean fair) {
        this.lock = new ReentrantLock(fair);
    }

    @Override
    public void run() {
        //测试公平和非公平
        fairTest();
        //
    }

    /**
     * 公平和非公平
     */
    public void fairTest(){
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        ReentrantLockDemo r = new ReentrantLockDemo(false);
        for (int i = 0; i < 10; i++) {
            new Thread(r,"thread"+i).start();
        }
    }
}
