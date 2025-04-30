package com.github.katemerek.javacodetraining.Concurrency;

import lombok.Data;

import java.util.PriorityQueue;

/**
 * Предположим, у вас есть пул потоков, и вы хотите реализовать блокирующую очередь для передачи задач между потоками.
 * Создайте класс BlockingQueue, который будет обеспечивать безопасное добавление и извлечение элементов между
 * производителями и потребителями в контексте пула потоков.
 * Класс BlockingQueue должен содержать методы enqueue() для добавления элемента в очередь и dequeue() для извлечения элемента.
 * Если очередь пуста, dequeue() должен блокировать вызывающий поток до появления нового элемента.
 * Очередь должна иметь фиксированный размер.
 * Используйте механизмы wait() и notify() для координации между производителями и потребителями.
 * Реализуйте метод size(), который возвращает текущий размер очереди.
 **/

@Data
public class BlockingQueueClass {
    private final PriorityQueue<Object> queue;
    private final int capacity = 5;

    public synchronized void enqueue(Object o) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.offer(o);

    }

    public synchronized void dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        queue.poll();
    }

    public synchronized int size() {
        return queue.size();
    }
}
