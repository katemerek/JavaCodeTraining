package com.github.katemerek.javacodetraining.Stream.task1;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Предположим, у нас есть список заказов, и каждый заказ представляет собой продукт и его стоимость.
 * Задача состоит в использовании Stream API и коллекторов для решения следующих задач:
 * Создайте список заказов с разными продуктами и их стоимостями.
 * Группируйте заказы по продуктам.
 * Для каждого продукта найдите общую стоимость всех заказов.
 * Отсортируйте продукты по убыванию общей стоимости.
 * Выберите три самых дорогих продукта.
 * Выведите результат: список трех самых дорогих продуктов и их общая стоимость.
 */

public class StreamCollectorsExample {

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0),
                new Order("Headphones", 100.0)
        );

        orders.stream().collect(Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getCost)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
    }
}
