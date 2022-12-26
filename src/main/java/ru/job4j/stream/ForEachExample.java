package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class ForEachExample {
    public static void main(String[] args) {
        List<StringBuilder> names = Arrays.asList(
                new StringBuilder("Михаиdfgsgsg"), new StringBuilder("Аван"),
                new StringBuilder("Е"));
       OptionalDouble rsl = names
                .stream()
                .map((el) -> el.append(" (Ученик"))
                .mapToInt(e -> e.length())
                        .average();
    }
}
