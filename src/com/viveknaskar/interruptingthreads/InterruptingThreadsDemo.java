package com.viveknaskar.interruptingthreads;

import java.util.Random;

public class InterruptingThreadsDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting...");
        Thread thread1 = new Thread(() -> {
            Random random = new Random();

            for(int i=0; i<1E8; i++) {

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted!!!");
                    break;
                }
                Math.sin(random.nextDouble());
            }
        });
        thread1.start();

        Thread.sleep(500);

        thread1.interrupt();
        thread1.join();

        System.out.println("Finished!");

    }
}
