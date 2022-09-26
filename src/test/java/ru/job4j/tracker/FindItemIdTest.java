package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindItemIdTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("item1"));
        tracker.add(new Item("item2"));
        tracker.add(new Item("item3"));

        FindItemId findItemId = new FindItemId(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(4);

        findItemId.execute(input, tracker);
        String ln = System.lineSeparator();

        assertThat(tracker.findById(1).getName()).isEqualTo("item1");
        assertThat(tracker.findById(2).getName()).isEqualTo("item2");
        assertThat(tracker.findById(3).getName()).isEqualTo("item3");
        assertThat(out.toString()).isEqualTo("=== Find item by id ===" + ln
                + "Заявка с введенным id: 4 не найдена." + ln);
    }
}