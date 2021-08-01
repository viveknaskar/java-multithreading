package com.viveknaskar.semaphores;

import java.util.concurrent.Semaphore;

/**
 * A semaphore uses a counter to regulate access to a shared resource.
 * Access is permitted if the counter is larger than zero.
 * If the value is 0, access is refused.
 * Permits that provide access to the shared resource are counted by the counter.
 * As a result, in order to access the resource, a thread must first obtain permission from the semaphore.
 */
public class SemaphoreDemo {

    public static void main(String[] args) throws Exception {

        /**
         * Semaphores are used to control access to resources
         */
        Semaphore sem = new Semaphore(1);

        sem.release();

        sem.acquire();

        System.out.println("Available permits: " + sem.availablePermits());


    }
}
