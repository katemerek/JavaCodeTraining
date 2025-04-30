package com.github.katemerek.javacodetraining.Collection;

/*
Напишите метод filter, который принимает на вход массив любого типа, вторым аргументом метод должен принимать класс,
реализующий интерфейс Filter, в котором один метод - T apply(T o) (параметризованный).
Метод должен быть реализован так чтобы возвращать новый массив, к каждому элементу которого была применена функция apply
 */

import java.util.Arrays;

public class FilterClass {
    public <T extends Filter<Object>> void filter(Object[] array, T filter) {
        Object[] result = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = filter.apply(array[i]);
        }
        System.out.println(Arrays.toString(result));
    }

    public static void main(String[] args) {
        String[] strings = {"bla", "koko", "kuku"};
        FilterClass filterClass = new FilterClass();
        filterClass.filter(strings, new Example());
    }
}
