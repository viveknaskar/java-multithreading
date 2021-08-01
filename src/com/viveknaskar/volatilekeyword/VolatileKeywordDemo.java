package com.viveknaskar.volatilekeyword;
import java.util.Scanner;

class Processor extends Thread {

    /**
     * Volatile is used for preventing Threads caching variable when they are not changed
     * within that thread.
     *
     */
    private volatile boolean running = true;

    public void run() {

        while (running) {
            System.out.println("hello there");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }

}

public class VolatileKeywordDemo {

    public static void main(String[] args) {

        Processor processorOne = new Processor();
        processorOne.start();

        System.out.println("Press return to shutdown...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        processorOne.shutdown();

    }
}
