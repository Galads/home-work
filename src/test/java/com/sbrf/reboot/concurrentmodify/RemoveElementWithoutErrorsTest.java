package com.sbrf.reboot.concurrentmodify;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveElementWithoutErrorsTest {

    @Test
    public void successConcurrentModificationException() {

        List<Integer> list = new ArrayList() {{
            add(1);
            add(2);
            add(3);
        }};

        assertThrows(ConcurrentModificationException.class, () -> {

            for (Integer integer : list) {
                list.remove(1);
            }
        });
    }

    @Test
    public void successRemoveElementWithoutErrors() {
        List<Integer> list = new ArrayList() {{
            add(1);
            add(2);
            add(3);
        }};

        assertDoesNotThrow(() -> {
            list.removeIf(e -> e == 1);
        });
    }

    @Test
    public void successRemoveElementWithoutErrorSecond() {
        List<Integer> list = new ArrayList() {{
            add(1);
            add(2);
            add(3);
        }};


        assertDoesNotThrow(() -> {
                    for (Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
                        Integer value = it.next();
                        if (value == 1)
                            it.remove();
                    }
                }
        );
    }

    @Test
    public void successRemoveElementWithoutErrorBadVersion() {
        List<Integer> list = new ArrayList() {{
            add(1);
            add(2);
            add(3);
        }};
        List<Integer> rmList = new ArrayList<>();

        assertDoesNotThrow(() -> {
            for (Integer e : list) {
                if (e == 1) {
                    rmList.add(e);
                }
            }
            list.removeAll(rmList);
        });
    }
}
