package com.github.katemerek.javacodetraining.Concurrency.Task3;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class BankAccount {

    private BigDecimal balance;
    private final UUID accountNumber;
    private final Lock lock = new ReentrantLock(true);

    public BankAccount(BigDecimal balance, UUID accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public void deposit(BigDecimal amount) {
        lock.lock();
        try {
            balance = balance.add(amount);
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(BigDecimal amount) {
        lock.lock();
        try {
            balance = balance.subtract(amount);
        } finally {
            lock.unlock();
        }
    }

    public BigDecimal getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public UUID getAccountNumber() {
        return accountNumber;
    }

    public Lock getLock() {
        return lock;
    }
}

