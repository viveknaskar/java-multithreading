package com.viveknaskar;

public class SynchronizedKeywordDemo {

    private int count = 0;

    /**
     *  All synchronized blocks synchronized on the same object can only have one thread
     *  executing inside them at a time. All other threads attempting to enter
     *  the synchronized block are blocked until the thread inside the
     *  synchronized block exits the block.
     */
    public synchronized void increment() {
        count++;
    }

    public static void main(String[] args) {

        SynchronizedKeywordDemo syncKeyDemo = new SynchronizedKeywordDemo();
        syncKeyDemo.doWork();

    }

    public void doWork() {

        Thread threadOne = new Thread(()->{
            for (int i=0; i<10000; i++) {
                increment();
            }
        });

        Thread threadTwo = new Thread(()->{
            for (int i=0; i<10000; i++) {
                increment();
            }
        });

        threadOne.start();
        threadTwo.start();

        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count is: " + count);

    }
}
