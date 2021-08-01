package com.viveknaskar.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * A semaphore uses a counter to regulate access to a shared resource.
 * Access is permitted if the counter is larger than zero.
 * If the value is 0, access is refused.
 * Permits that provide access to the shared resource are counted by the counter.
 * As a result, in order to access the resource, a thread must first obtain permission from the semaphore.
 */
public class SemaphoreDemo {

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i=0; i<200; i++) {
            executor.submit(() -> {
                Connection.getInstance().connect();
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
