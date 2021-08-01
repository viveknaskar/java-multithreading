package com.viveknaskar.reentrantlocks;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private int count = 0;

    /**
     * Threads can enter the lock on a resource more than once with ReentrantLock.
     * A hold count of one is established when the thread initially enters the lock.
     * Before unlocking, the thread can enter lock again, and each time the hold count is increased by one.
     * The hold count is decremented by one for each unlocks request, and when the hold count reaches
     * zero, the resource is unlocked.
     */
    private Lock lock = new ReentrantLock();

    /**
     * A Condition interface allows a thread to pause execution until the provided condition is true.
     * A Condition object must be tied to a Lock and acquired through the newCondition() method.
     */
    private Condition condition = lock.newCondition();

    /**
     * Both the threads will be iterated 10000 times.
     * When one thread is incremented, the other thread will wait.
     */
    private void increment() {
        for(int i=0; i<10000; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();

        System.out.println("Waiting...");
        /**
         * This method makes the current thread to wait until it is signalled or interrupted.
         */
        condition.await();
        System.out.println("Woken up!!!");

        /**
         * Putting increment() in try in case when there are exceptions,
         * the lock needs to be release or unlocked, hence finally must be used.
         */
        try {
            increment();
        }
        finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {

        Thread.sleep(2000);
        lock.lock();

        System.out.println("Press the return key...");
        new Scanner(System.in).nextLine();
        System.out.println("Return key pressed!");

        /**
         * This method wakes up one waiting thread.
         */
        condition.signal();

        try {
            increment();
        }
        finally {
            lock.unlock();
        }
    }

    public void finished() {
        System.out.println("Count is: " + count);
    }

}
