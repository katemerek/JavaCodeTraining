package com.github.katemerek.javacodetraining.Concurrency.Task2;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class ComplexTask implements Runnable {
    private static final AtomicInteger taskCounter = new AtomicInteger(0);
    private final CyclicBarrier barrier;
    private volatile int taskNumber;

    public ComplexTask(CyclicBarrier barrier) {
        this.barrier = barrier;
        this.taskNumber = taskCounter.incrementAndGet();
    }

    @Override
    public void run() {
        try {
            System.out.println("Time: " + LocalDateTime.now() + " Thread: " + Thread.currentThread().getName() + "приступает к выполнению задачи");
            execute();
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    public void execute() {
        try {
            System.out.println(Thread.currentThread().getName() + " начинает выполнение задачи " + taskNumber);
            int num = new Random().nextInt(100);
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + " Очень долго работал и получил число: " + num);
            System.out.println("Выполнил задачу № " + taskNumber);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " Я был прерван!");
            Thread.currentThread().interrupt();
        }
    }
}
