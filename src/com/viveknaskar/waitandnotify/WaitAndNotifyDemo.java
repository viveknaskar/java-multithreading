package com.viveknaskar.waitandnotify;

public class WaitAndNotifyDemo {

    public static void main(String[] args) throws InterruptedException {

        final Processor processor = new Processor();

        Thread threadOne = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadTwo = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();


    }

}
