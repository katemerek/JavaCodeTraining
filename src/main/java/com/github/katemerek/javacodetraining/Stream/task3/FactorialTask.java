package com.github.katemerek.javacodetraining.Stream.task3;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Integer> {
    private final int n;

    public FactorialTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n < 1) {
            throw new IllegalArgumentException("n must be greater than zero");
        }
        if (n == 1) {
            return 1;
        }
        FactorialTask task = new FactorialTask(n - 1);
        task.fork();
        return n * task.join();
    }
}
