package com.sbrf.reboot.repository.fluentAPI;

public interface FluentSQL<E> {
    FluentSQL<E> table(String name);

    FluentSQL<E> execute();

    boolean isResult();
}
