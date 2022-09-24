package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindItemNameTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("item1"));
        tracker.add(new Item("item2"));
        tracker.add(new Item("item3"));

        FindItemName findItemName = new FindItemName(out);

        Input input = mock(Input.class);

        when(input.askSTR(any(String.class))).thenReturn("item4");

        findItemName.execute(input, tracker);
        String ln = System.lineSeparator();

        assertThat(tracker.findByName("item3").get(0).getName()).isEqualTo("item3");
        assertThat(out.toString()).isEqualTo("=== Find items by name ===" + ln
                + "Заявки с именем: item4 не найдены." + ln);
    }
}