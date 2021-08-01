package com.viveknaskar.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Process implements Runnable {

    /**
     * CountDownLatch is a Thread-safe class that allows one or more threads to
     * wait for a given set of operations to complete.
     */
    private CountDownLatch latch;

    public Process(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {

        System.out.println("Started.");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * The count is decremented by calls to the countDown() method.
         */
        latch.countDown();

    }
}

public class CountdownLatchDemo {

    public static void main(String[] args) {

        /**
         * A CountDownLatch is initialized with a given count.
         */
        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for(int i=0; i<3; i++) {
            executorService.submit(new Process(latch));
        }

        /**
         * Threads waiting for the count to reach zero can call one of the await() methods.
         * Calling await() blocks the thread until the count reaches zero.
         */
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed.");

    }
}
