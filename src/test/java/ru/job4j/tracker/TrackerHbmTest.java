package ru.job4j.tracker;

import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TrackerHbmTest {

    private final HbmTracker hbmTracker = new HbmTracker();

    @After
    public void cleanTable() {
        hbmTracker.deleteAll();
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        Item item = new Item();
        item.setName("test1");
        hbmTracker.add(item);
        Item result = hbmTracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenFindByName() throws Exception {
        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();
        item1.setName("test1");
        item2.setName("test2");
        item3.setName("test3");
        hbmTracker.add(item1);
        hbmTracker.add(item2);
        hbmTracker.add(item3);
        List<Item> result = hbmTracker.findByName("test2");
        assertThat(result.get(0).getName(), is(List.of(item2).get(0).getName()));
    }

    @Test
    public void whenDelete() throws Exception {
        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();
        hbmTracker.add(item1);
        hbmTracker.add(item2);
        hbmTracker.add(item3);
        hbmTracker.delete(item2.getId());
        List<Item> result = hbmTracker.findAll();
        assertThat(result.size(), is(2));
    }

    @Test
    public void whenDeleteAll() throws Exception {
        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();
        hbmTracker.add(item1);
        hbmTracker.add(item2);
        hbmTracker.add(item3);
        hbmTracker.deleteAll();
        List<Item> result = hbmTracker.findAll();
        assertThat(result.size(), is(0));
    }

    @Test
    public void whenReplace() throws Exception {
        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();
        item1.setName("test1");
        item2.setName("test2");
        item3.setName("test3");
        hbmTracker.add(item1);
        hbmTracker.add(item2);
        hbmTracker.add(item3);
        hbmTracker.replace(item1.getId(), item2);
        List<Item> result = hbmTracker.findAll();
        assertThat(result.get(0).getName(), is(item2.getName()));
    }
}
