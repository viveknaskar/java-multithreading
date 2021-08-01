package com.viveknaskar.deadlock;

public class DeadlockDemo {

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
