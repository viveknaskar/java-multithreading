package com.viveknaskar.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private Account accountOne = new Account();
    private Account accountTwo = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException, IllegalMonitorStateException {
        while(true) {
            /** Acquire locks **/
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;

            try {
                /**  tryLock() will return true if it acquires the lock.  **/
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = firstLock.tryLock();
            }
            finally {
                if(gotFirstLock && gotSecondLock) {
                    return;
                }
                if(gotFirstLock) {
                    firstLock.unlock();
                }
                if(gotSecondLock) {
                    secondLock.unlock();
                }
            }

            /** Locks not acquired **/
            Thread.sleep(1);
        }
    }

    public void firstThread() throws InterruptedException {

        /**
         * Making random transfer from account 1 to account 2
         */
        Random random = new Random();
        for (int i=0; i<10000; i++) {

            acquireLocks(lock1, lock2);

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

            acquireLocks(lock2, lock1);

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
