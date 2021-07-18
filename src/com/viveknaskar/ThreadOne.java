package com.viveknaskar;

/**
 * Creating thread by extending Thread class
 */
class Runner extends Thread {
    // Overriding the run method from thread class
    public void run() {

        for (int i=0; i<10; i++) {
            System.out.println("Hello there: " + i);

            /**
             * sleep() is a static method of the Thread class which pauses the program
             *  for specified number of milliseconds
             */
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

public class ThreadOne {
    public static void main(String[] args) {
        Runner runnerOne = new Runner();
        // start() will make the Thread class to execute the run method
        runnerOne.start();

        // Running another thread to run concurrently
        Runner runnerTwo = new Runner();
        runnerTwo.start();
    }
}
