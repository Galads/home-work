package com.sbrf.reboot.collections;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionsTest {


    /*
     * Задача.
     * Имеется список лучших студентов вуза.
     *
     * 1. Иванов
     * 2. Петров
     * 3. Сидоров
     *
     * В новом семестре по результатам подсчетов оценок в рейтинг на 1 место добавился новый студент - Козлов.
     * Теперь в рейтинге участвуют 4 студента.
     * (Предполагаем что в рейтинг можно попасть только получив достаточное количество балов, что бы занять 1 место).
     *
     * Вопрос.
     * Какую коллекцию из Collections framework вы предпочтете для текущего хранения и использования списка студентов?
     *
     * Проинициализируйте students, добавьте в нее 4 фамилии студентов что бы тест завершился успешно.
     */
    @Test
    public void addStudentToRating() {

        List<String> students = new LinkedList<String>() {{
            add("Иванов");
            add("Петров");
            add("Сидоров");
        }};
        students.add(0, "Козлов");

        assertEquals(4, students.size());
    }

    /*
     * Задача.
     * Вы коллекционируете уникальные монеты.
     * У вас имеется специальный бокс с секциями, куда вы складываете монеты в хаотичном порядке.
     *
     * Вопрос.
     * Какую коллекцию из Collections framework вы предпочтете использовать для хранения монет в боксе.
     *
     * Проинициализируйте moneyBox, добавьте в нее 10 монет что бы тест завершился успешно.
     */
    @Test
    public void addMoneyToBox() {
        Set<Integer> moneyBox;

        moneyBox = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        assertEquals(10, moneyBox.size());
    }

    /*
     * Задача.
     * Имеется книжная полка.
     * Периодически вы берете книгу для чтения, затем кладете ее на свое место.
     *
     * Вопрос.
     * Какую коллекцию из Collections framework вы предпочтете использовать для хранения книг.
     *
     * Проинициализируйте bookshelf, добавьте в нее 3 книги что бы тест завершился успешно.
     */
    @Test
    public void addBookToShelf() {
        @RequiredArgsConstructor
        class Book {
            @Setter
            @Getter
            @NonNull
            private String name;
        }

        List<Book> bookshelf = new ArrayList<Book>() {{
            add(new Book("Математика"));
            add(new Book("Преступление и наказание"));
            add(new Book("Физика"));
        }};

        assertEquals(3, bookshelf.size());
    }

    /*
     * Задача.
     * Имеется список уникальных директорий в виде строк.
     * Для уменьшения времени получения каждой конкретной директории при уходе в глубину
     * Вы хотите записывать ее в структуру данных (кэш), чтобы в следующий раз получать директории из него.
     *
     * Вопрос.
     * Какую коллекцию из Collections framework вы предпочтете использовать для хранения директорий в виде строк.
     *
     * Проинициализируйте cacheDirectory, добавьте в нее 3 директории что бы тест завершился успешно.
     */
    @Test
    public void addPathToCache() {
        LinkedHashSet<String> cacheDirectory = new LinkedHashSet<>(Arrays.asList("/path/1", "/path/2", "/path/3"));

        assertEquals(3, cacheDirectory.size());
    }
}
