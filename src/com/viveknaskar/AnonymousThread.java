package com.viveknaskar;

public class AnonymousThread {
    public static void main(String[] args) {
        // Used lambda function to create thread and passing the Thread class constructor
        Thread thread = new Thread(() -> {
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
        });

        thread.start();
    }
}
