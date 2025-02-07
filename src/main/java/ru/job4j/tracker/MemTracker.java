package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemTracker implements Store {
    private final List<Item> items = new ArrayList<>();
    private int ids = 1;

    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        return item;
    }

    public List<Item> findAll() {
        return List.copyOf(items);
    }

    public List<Item> findByName(String key) {
        List<Item> name = new ArrayList<>();
        for (Item item : items) {
            if (key.equals(item.getName())) {
                name.add(item);
            }

        }
        return name;
    }

    public Item findById(Integer id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    @Override
    public void deleteAll() {

    }

    private int indexOf(Integer id) {
        int rsl = -1;
            for (int index = 0; index < items.size(); index++) {
                if (items.get(index).getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public boolean replace(Integer id, Item item) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (index != -1) {
            item.setId(id);
            items.set(index, item);

        }
        return rsl;
    }

    public boolean delete(Integer id) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            items.remove(index);
        }
        return rsl;
    }
}
