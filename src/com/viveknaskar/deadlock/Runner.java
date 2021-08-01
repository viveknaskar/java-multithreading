package com.viveknaskar.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private Account accountOne = new Account();
    private Account accountTwo = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public void firstThread() throws InterruptedException {

        /**
         * Making random transfer from account 1 to account 2
         */
        Random random = new Random();
        for (int i=0; i<10000; i++) {

            lock1.lock();
            lock2.lock();
            try {
                Account.transfer(accountOne, accountTwo, random.nextInt(100));
            }
            finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() throws InterruptedException {
        /**
         * Making random transfer from account 2 to account 1
         */
        Random random = new Random();
        for (int i=0; i<10000; i++) {

            lock1.lock();
            lock2.lock();
            try {
                Account.transfer(accountTwo, accountOne, random.nextInt(100));
            }
            finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void finished() {

        System.out.println("First account balance: " + accountOne.retrieveBalance());
        System.out.println("Second account balance: " + accountTwo.retrieveBalance());
        System.out.println("Total balance: " + (accountOne.retrieveBalance() + accountTwo.retrieveBalance()));

    }
}
