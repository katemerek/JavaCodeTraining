package com.github.katemerek.javacodetraining.Collection;
/*
Напишите метод, который получает на вход массив элементов и возвращает Map, ключи в котором - элементы,
а значения - сколько раз встретился этот элемент
 */

import java.util.HashMap;
import java.util.Map;

public class Elements {

    public Map<Object, Integer> CountOfElements(Object[] array) {
        Map<Object, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            map.put(array[i], map.getOrDefault(array[i], 0) + 1);
        }
        return map;
    }

    public static void main(String[] args) {
        Elements elements = new Elements();
        String[] strings = {"bla", "koko", "kuku", "koko", "koko", "kuku"};
        System.out.println(elements.CountOfElements(strings));
    }
}
