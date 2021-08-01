package com.viveknaskar.producerconsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerDemo {

    /**
     * ArrayBlockingQueue is a data queue that can hold items and it is first-in and first-out
     * These are thread-safe
     */
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            try {
                producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadTwo = new Thread(() -> {
            try {
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();

    }

    private static void producer() throws InterruptedException {
        Random random = new Random();
        while(true) {
            queue.put(random.nextInt(100));
        }
    }

    private static void consumer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            Thread.sleep(100);
            if(random.nextInt() == 0) {
                Integer value = queue.take();
                System.out.println("Taken value: " + value + "; Queue size: " + queue.size());
            }
        }
    }

}
