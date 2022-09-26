package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DelActionTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("item1"));
        tracker.add(new Item("item2"));
        tracker.add(new Item("item3"));

        DelAction del = new DelAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(2);

        del.execute(input, tracker);

        String ln = System.lineSeparator();

        assertThat(out.toString()).isEqualTo("=== Delete item ====" + ln
                + "Заявка удалена успешно." + ln);
        assertThat(tracker.findById(2)).isNull();
    }
}