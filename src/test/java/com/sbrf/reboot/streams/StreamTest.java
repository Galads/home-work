package com.sbrf.reboot.streams;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamTest {

    /*
     * Отсортируйте коллекцию integers по возрастанию. Используйте Stream.
     */
    @Test
    public void sortedListStream() {
        List<Integer> integers = Arrays.asList(6, 9, 8, 3);

        List<Integer> expectedIntegers = Arrays.asList(3, 6, 8, 9);

        List<Integer> actualIntegers = integers.stream().sorted().collect(Collectors.toList());

        assertEquals(expectedIntegers, actualIntegers);
    }

    /*
     * Отфильтруйте коллекцию integers.
     * В коллекции должны остаться только те числа, которые делятся без остатка на 2.  Используйте Stream.
     */
    @Test
    public void filteredListStream() {
        List<Integer> integers = Arrays.asList(6, 9, 8, 3);

        List<Integer> expectedIntegers = Arrays.asList(6, 8);

        List<Integer> actualIntegers = integers.stream().filter(e -> e % 2 == 0).collect(Collectors.toList());

        assertEquals(expectedIntegers, actualIntegers);

    }

    /*
     * Отфильтруйте и отсортируйте коллекцию books.
     * Получите коллекцию, в которой будут только книги от автора "Maria", отсортированные по цене.
     * Используйте Stream.
     */
    @AllArgsConstructor
    @EqualsAndHashCode
    class Book {
        private String name;
        private String author;
        private BigDecimal price;
    }

    @Test
    public void sortedAndFilteredBooks() {
        List<Book> books = Arrays.asList(
                new Book("Trees", "Maria", new BigDecimal(900)),
                new Book("Animals", "Tom", new BigDecimal(500)),
                new Book("Cars", "John", new BigDecimal(200)),
                new Book("Birds", "Maria", new BigDecimal(100)),
                new Book("Flowers", "Tom", new BigDecimal(700))

        );
        List<Book> expectedBooks = Arrays.asList(
                new Book("Birds", "Maria", new BigDecimal(100)),
                new Book("Trees", "Maria", new BigDecimal(900))

        );

        List<Book> actualBooks = books.stream()
                .filter(e -> "Maria".equals(e.author))
                .sorted(Comparator.comparing(e -> e.price))
                .collect(Collectors.toList());

        assertEquals(expectedBooks, actualBooks);
    }

    /*
     * Получите измененную коллекцию contracts.
     * Получите коллекцию, в которой будет тот же набор строк, только у каждой строки появится префикс "M-".
     * Используйте Stream.
     */
    @Test
    public void modifiedList() {
        List<String> contracts = Arrays.asList("NCC-1-CH", "NCC-2-US", "NCC-3-NH");

        List<String> expectedContracts = Arrays.asList("M-NCC-1-CH", "M-NCC-2-US", "M-NCC-3-NH");

        List<String> actualContracts = contracts.stream()
                .map(e -> "M-" + e)
                .collect(Collectors.toList());

        assertEquals(expectedContracts, actualContracts);
    }

    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    class Card {
        private String number;
        private List<String> operations;
    }

    @Test
    void getAllOperations() {
        List<Card> cardholder = Arrays.asList(
                new Card("0408565612120001", Arrays.asList("send cash: 8$", "withdrawing cash: 200$")),
                new Card("1111111111110000", Arrays.asList("depositing cash: 500$")),
                new Card("1234567890000000", Arrays.asList("send cash: 2$"))
        );

        List<String> expectedOperations = Arrays.asList(
                "send cash: 8$", "withdrawing cash: 200$",
                "depositing cash: 500$", "send cash: 2$"
        );
        List<String> history = cardholder.stream()
                .map(Card::getOperations)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        assertArrayEquals(expectedOperations.toArray(), history.toArray());
    }
}
