package com.github.katemerek.javacodetraining.Concurrency.Task3;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * В виртуальном банке "ConcurrentBank" решено внедрить многопоточность для обработки операций по счетам клиентов.
 * Система должна поддерживать возможность одновременного пополнения (deposit), снятия (withdraw), а также переводов (transfer) между счетами.
 * Каждый счет имеет свой уникальный номер.
 * Реализуйте класс BankAccount с методами deposit, withdraw и getBalance, поддерживающими многопоточное взаимодействие.
 * Реализуйте класс ConcurrentBank для управления счетами и выполнения переводов между ними.
 * Класс должен предоставлять методы createAccount для создания нового счета и transfer для выполнения переводов между счетами.
 * Переводы между счетами должны быть атомарными, чтобы избежать ситуаций, когда одна часть транзакции выполняется успешно, а другая нет.
 * Реализуйте метод getTotalBalance, который возвращает общий баланс всех счетов в банке.
 */

@Data
public class ConcurrentBank {

    private final List<BankAccount> accounts = new ArrayList<>();
    private final Lock totalBalanceLock = new ReentrantLock(true);

    public BankAccount createAccount(BigDecimal newBalance) {
        BankAccount newAccount = new BankAccount(newBalance, UUID.randomUUID());
        accounts.add(newAccount);
        return newAccount;
    }

    public void transfer(BankAccount from, BankAccount to, BigDecimal amount) throws InsufficientMoneyException {
        //определяем порядок для перевода чтоб не было deadlock
        BankAccount first = from.getAccountNumber().compareTo(to.getAccountNumber()) < 0 ? from : to;
        BankAccount second = from.getAccountNumber().compareTo(to.getAccountNumber()) < 0 ? to : from;

        boolean firstLocked = false;
        boolean secondLocked = false;

        try {
            // Пытаемся захватить блокировки в произвольном порядке
            firstLocked = first.getLock().tryLock();
            secondLocked = second.getLock().tryLock();

            // Если обе блокировки получены
            if (firstLocked && secondLocked) {
                if (from.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientMoneyException();
                }
                from.withdraw(amount);
                to.deposit(amount);
            }
        } finally {
            // Всегда освобождаем блокировки в обратном порядке
            if (secondLocked) {
                second.getLock().unlock();
            }
            if (firstLocked) {
                first.getLock().unlock();
            }
        }
    }

    public BigDecimal getTotalBalance() {
        totalBalanceLock.lock();
        try {
            BigDecimal totalBalance = BigDecimal.ZERO;
            for (BankAccount account : accounts) {
                account.getLock().lock();
                try {
                    totalBalance = totalBalance.add(account.getBalance());
                } finally {
                    account.getLock().unlock();
                }
            }
            return totalBalance;
        } finally {
            totalBalanceLock.unlock();
        }
    }
}
