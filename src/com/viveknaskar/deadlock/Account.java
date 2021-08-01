package com.viveknaskar.deadlock;

public class Account {

    private int balance = 10000;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int retrieveBalance() {
        return balance;
    }

    public static void transfer(Account accountOne, Account accountTwo, int amount) {
        accountOne.withdraw(amount);
        accountTwo.deposit(amount);
    }
}
