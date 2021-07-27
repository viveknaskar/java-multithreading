package com.viveknaskar.multiplelocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

    private Random random = new Random();

    /**
     * It is a good practice to separate lock objects for synchronized blocks
     * so that the threads can function separately without bothering about the
     * intrinsic lock
     */
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private List<Integer> listOne = new ArrayList<>();
    private List<Integer> listTwo = new ArrayList<>();

    /**
     * Assuming Worker class as a processor, it will have multiple stages
     * that would do certain tasks
     */

    public void stageOne() {
        synchronized (lock1){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            listOne.add(random.nextInt(100));
        }
    }

    public void stageTwo() {
        synchronized (lock2){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            listTwo.add(random.nextInt(100));
        }

    }

    public void process() {
        for(int i=0; i< 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void main() {
        System.out.println("Starting...");

        long start = System.currentTimeMillis();

        // creating a new thread
        Thread threadOne = new Thread(() -> process());
        // creating another new thread
        Thread threadTwo = new Thread(() -> process());

        // starting the two threads
        threadOne.start();
        threadTwo.start();

        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start));
        System.out.println("List 1: " + listOne.size() + "; List 2: " + listTwo.size());
    }
}
