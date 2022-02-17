package ru.job4j.tracker;

public class CreateTest implements UserAction {

    private final Output out;

    public CreateTest(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Test add";
    }

    @Override
    public boolean execute(Input input, Store store) {
        for (int i = 0; i < 1000; i++) {
//            out.println("=== Create a new Item ====");
            Item item = new Item(new String("User: " + i));
            store.add(item);
//            System.out.println("Добавленная заявка: " + item);
        }
        return true;
    }
}
