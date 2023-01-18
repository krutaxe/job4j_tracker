package ru.job4j.queue;

import java.util.Comparator;

public class TaskByPositionAsc implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        return o1.position().compareTo(o2.position());
    }

    public static void main(String[] args) {
        TaskByPositionAsc taskByPositionAsc = new TaskByPositionAsc();
        Task task1 = new Task(Position.MANAGER, "hx", 1);
        Task task2 = new Task(Position.DEPUTY_DIRECTOR, "hx", 2);
        System.out.println(taskByPositionAsc.compare(task1, task2));
    }
}
