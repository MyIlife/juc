package com.example.juc.classLockAndObjectLock;

public class ClassLockAndObjectLock {
    static class ClassLock implements Runnable{

        @Override
        public void run() {
            synchronized (ClassLockAndObjectLock.class){
                System.out.println("进入了类锁");
                try {
                    Thread.sleep(5000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static class ObjectLock implements Runnable{
        private ClassLockAndObjectLock c ;

        public ObjectLock(ClassLockAndObjectLock c) {
            this.c = c;
        }

        @Override
        public void run() {
            synchronized (this){
                System.out.println("进入了对象锁");
                try {
                    Thread.sleep(5000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ClassLock()).start();
        new Thread(new ObjectLock(new ClassLockAndObjectLock())).start();
    }
}
