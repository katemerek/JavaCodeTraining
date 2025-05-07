package com.github.katemerek.javacodetraining.Concurrency.Task3;

public class InsufficientMoneyException extends RuntimeException {
    public InsufficientMoneyException() {
        super("Недостаточно денег для совершения операции, бро");
    }
}
