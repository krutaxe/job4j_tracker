package ru.job4j.tracker;

public class CreateAction implements UserAction {

    private final Output out;

    public CreateAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add new Item";
    }

    @Override
    public boolean execute(Input input, Store store) {
        out.println("=== Create a new Item ====");
        String name = input.askSTR("Enter name: ");
        Item item = new Item(name);
        store.add(item);
        System.out.println("Добавленная заявка: " + item);
        return true;
    }
}
