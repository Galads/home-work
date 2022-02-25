package com.sbrf.reboot.service.concurrency;

import lombok.Getter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutorService {

    @Getter
    private final int numberOfThreads;

    @Getter
    private final int MAX_THREAD = Runtime.getRuntime().availableProcessors();

    private final ExecutorService service;

    /**
     * Executors - методы упрощают создание исполнителей
     * Number of threads = (Number of Available Cores >> 1) * (1 + Wait time / Service time) - 1
     * (Number of Available Cores >> 1) - получение кол-ва физических ядер; 12 >> 1 == 6
     */
    public TaskExecutorService(int numberOfThreads) {
        numberOfThreads = (numberOfThreads >= MAX_THREAD) ? (MAX_THREAD >> 1) * (1 + 1 / 1) - 1 : numberOfThreads;
        this.service = Executors.newFixedThreadPool(numberOfThreads);
        this.numberOfThreads = numberOfThreads;
    }

    public void execute(Task task) {
        for (int i = 0; i < numberOfThreads; i++)
            service.execute(task);
    }

    public void shutdown() {
        service.shutdown();
    }
}
