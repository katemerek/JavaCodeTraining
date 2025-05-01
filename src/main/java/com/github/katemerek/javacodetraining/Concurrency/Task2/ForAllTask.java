package com.github.katemerek.javacodetraining.Concurrency.Task2;

import lombok.Data;

@Data
class ForAllTask implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(800);
            System.out.println(Thread.currentThread().getName() + " Мы все хорошо постарались и в награду нам эта строка!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

