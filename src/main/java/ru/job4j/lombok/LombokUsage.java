package ru.job4j.lombok;

public class LombokUsage {
    public static void main(String[] args) {
        var bird = new BirdData();
        bird.setAge(1);
        bird.setColor("red");
        System.out.println(bird.getColor());
        System.out.println(bird);
    }
}
