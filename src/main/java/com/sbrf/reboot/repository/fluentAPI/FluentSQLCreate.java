package com.sbrf.reboot.repository.fluentAPI;

public interface FluentSQLCreate<E> extends FluentSQL<E> {
    FluentSQLCreate<E> column(String... names);

    FluentSQLCreate<E> table(String name);

    FluentSQLCreate<E> execute();
}
