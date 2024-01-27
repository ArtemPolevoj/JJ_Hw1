package ru.geekbrains.junior.lesson1.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        double average = 0;
        int count = 0;
        for (int i = 0; i < 20; i++) {
            numbers.add(random.nextInt(1, 101));
        }
        for (Integer i : numbers) {
            if (i % 2 == 0) {
                average = average + i;
                count++;
            }
        }
        average = average / count;

        double averageStream = numbers.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);

        System.out.printf("Среднее значение четных чисел(stream): %.2f.\n", averageStream);
        System.out.printf("Среднее значение четных чисел(for): %.2f.\n", average);
        if (average == averageStream) {
            System.out.println("Значения равны.");
        } else {
            System.out.println("Значения не равны.");
        }
    }
}
