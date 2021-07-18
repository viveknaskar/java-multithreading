package com.viveknaskar;

/**
 * Creating thread by implementing Runnable interface
 */
class ThreadRunner implements Runnable {

    // Overriding the run method from the Runnable interface
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

public class ThreadTwo {

    public static void main(String[] args) {

        Thread threadOne = new Thread(new ThreadRunner());
        Thread threadTwo = new Thread(new ThreadRunner());

        threadOne.start();
        threadTwo.start();


    }
}
