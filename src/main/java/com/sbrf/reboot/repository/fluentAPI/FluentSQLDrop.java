package com.sbrf.reboot.repository.fluentAPI;

public interface FluentSQLDrop<E> extends FluentSQL<E> {
    FluentSQLDrop<E> execute();
}
