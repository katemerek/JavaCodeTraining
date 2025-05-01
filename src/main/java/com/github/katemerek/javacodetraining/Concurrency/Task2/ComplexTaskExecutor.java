package com.github.katemerek.javacodetraining.Concurrency.Task2;


import lombok.Data;

import java.util.concurrent.*;

/**
 * Синхронизация потоков с использованием CyclicBarrier и ExecutorService
 * В этой задаче мы будем использовать CyclicBarrier и ExecutorService для синхронизации нескольких потоков,
 * выполняющих сложную задачу, и затем ожидающих, пока все потоки завершат выполнение, чтобы объединить результаты.
 * <p>
 * Создайте класс ComplexTask, представляющий сложную задачу, которую несколько потоков будут выполнять.
 * В каждой задаче реализуйте метод execute(), который выполняет часть сложной задачи.
 * <p>
 * Создайте класс ComplexTaskExecutor, в котором будет использоваться CyclicBarrier и ExecutorService для синхронизации выполнения задач.
 * Реализуйте метод executeTasks(int numberOfTasks), который создает пул потоков и назначает каждому потоку экземпляр сложной задачи для выполнения.
 * Затем используйте CyclicBarrier для ожидания завершения всех потоков и объединения результатов их работы.
 * В методе main создайте экземпляр ComplexTaskExecutor и вызовите метод executeTasks с несколькими задачами для выполнения.
 **/

@Data
public class ComplexTaskExecutor {
    private final int threadPoolSize;
    private CyclicBarrier cyclicBarrier;

    public ComplexTaskExecutor(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
        this.cyclicBarrier = new CyclicBarrier(threadPoolSize, new ForAllTask());
    }

    public void executeTasks(int numberOfTasks) {
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        try {
            for (int i = 0; i < numberOfTasks; i++) {
                ComplexTask task = new ComplexTask(cyclicBarrier);
                executor.execute(task);
            }
        } finally {
            shutdownAndAwaitTermination(executor);
        }
    }

    static void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ex) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
