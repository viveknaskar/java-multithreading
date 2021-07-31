package com.viveknaskar.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Server programmes, such as database and web servers, execute requests from numerous
 * clients on a regular basis and are designed to handle a high number of brief tasks.
 * A server application might be built by creating a new thread each time a request arrives
 * and servicing the new request in the freshly formed thread. While this technique appears
 * to be straightforward to execute, it has major drawbacks. A server that starts a new thread
 * for each request would spend more time and use more system resources producing and deleting
 * threads than it would on processing real requests.
 *
 * A thread pool re-uses previously generated threads to complete current tasks,
 * therefore addressing the issue of thread cycle overhead and resource thrashing.
 */

class Processor implements Runnable {
    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Starting: " + id);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed: " + id);
    }
}

public class Application {

    public static void main(String[] args) {

        /**
         *  By using the executor, one only has to implement the Runnable objects
         *  and send them to the executor to execute.
         */
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for(int i=0; i<5; i++) {
            executorService.submit(new Processor(i));
        }

        executorService.shutdown();

        System.out.println("All tasks submitted.");

        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks completed.");

    }
}
