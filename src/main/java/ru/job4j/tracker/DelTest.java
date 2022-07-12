package ru.job4j.tracker;

public class DelTest implements UserAction {
    private final Output out;

    public DelTest(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete Item";
    }

    @Override
    public boolean execute(Input input, Store store) {
        for (int i = 0; i < 500000; i++) {
            out.println("=== Delete item ====");
            if (store.delete(i)) {
                out.println("Заявка удалена успешно - " + i);
            } else {
                out.println("Ошибка удаления заявки - " + i);
            }
        }

        return true;
    }
}
