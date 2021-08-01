package com.viveknaskar.waitandnotify;

import java.util.Scanner;

public class Processor {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread running...");

            /**
             * This method waits in such a way that it doesn't consume much of
             * the system resources and it can be called inside the synchronized block.
             */
            wait();

            System.out.println("Resumed.");

        }
    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);

        synchronized (this) {
            System.out.println("Waiting for the return key...");
            scanner.nextLine();
            System.out.println("Return key pressed.");
            /**
             * The Object.notify() wakes up a single thread that is waiting on the monitor of this object.
             * If there are many threads waiting on this item, one of them is picked to be woken.
             * The decision is arbitrary and is made at the implementation's discretion.
             * By invoking one of the wait methods on an object's monitor, a thread can wait on it.
             * This method is also used inside the synchronized method.
             */
            notify();

            /**
             * The intrinsic lock will be released only after 5 seconds and then
             * "Resumed." would be printed.
             */
            Thread.sleep(5000);
        }
    }
}
