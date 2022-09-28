package ru.job4j.lombok;

public class LombokUsage {
    public static void main(String[] args) {

        var bird = new BirdData();
        bird.setAge(1);
        bird.setColor("red");
        System.out.println(bird.getColor());
        System.out.println(bird);

        var role = Role.of()
                .id(1)
                .name("ADMIN")
                .accessBy("create")
                .accessBy("update")
                .accessBy("read")
                .accessBy("delete")
                .build();
        System.out.println(role);

        var permission = Permission.of()
                .id(2)
                .name("User")
                .rules("user")
                .rules("admin")
                .rules("public")
                .build();
        System.out.println(permission);
    }
}
