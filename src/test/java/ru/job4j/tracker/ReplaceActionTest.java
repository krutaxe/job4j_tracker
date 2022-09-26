package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReplaceActionTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        ReplaceAction rep = new ReplaceAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askSTR(any(String.class))).thenReturn(replacedName);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Edit item ===" + ln
                + "Заявка изменена успешно." + ln);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo(replacedName);
    }
}