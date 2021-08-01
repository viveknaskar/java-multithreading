package com.viveknaskar.semaphores;

import java.util.concurrent.Semaphore;

/**
 * Connection is a Singleton class
 */
public class Connection {

    private static Connection instance = new Connection();

    /**
     * When the fairness parameter is set,
     * the permit is prioritised to whichever thread calls acquire() first.
     */
    private Semaphore sem = new Semaphore(10, true);

    private int connections = 0;

    private Connection() {}

    public static Connection getInstance() {
        return instance;
    }

    public void connect() {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try{
            doConnect();
        }
        finally {
            sem.release();
        }
    }

    public void doConnect() {

        synchronized (this) {
            connections++;
            System.out.println("Current connections: " + connections);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            connections--;
        }

    }
}
