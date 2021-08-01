package com.viveknaskar.reentrantlocks;

/**
 * The ReentrantLock class implements the Lock interface and allows methods to be synchronised
 * while accessing shared resources. Calls to the lock and unlock methods surround the code that
 * manipulates the shared resource. This provides the current working thread a lock on the
 * shared resource and prevents all other threads from attempting to take a lock on it.
 */
public class ReentrantLockDemo {

    public static void main(String[] args) throws InterruptedException {

        final Runner runner = new Runner();

        Thread threadOne = new Thread(() -> {
            try {
                runner.firstThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadTwo = new Thread(() -> {
            try {
                runner.secondThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();

        runner.finished();
    }
}
