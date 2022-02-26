package com.sbrf.reboot.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
public class SomeServiceCount {
    @NonNull
    private final CalculationService calcService;

    public void doSomething() throws ExecutionException, InterruptedException {

        CompletableFuture<List<Long>> userBalance = CompletableFuture
                .supplyAsync(calcService::getBalance);

        CompletableFuture<List<Double>> updatePercents = CompletableFuture
                .supplyAsync(calcService::updatePercentFromEach);

        CompletableFuture<List<Double>> resBalances = userBalance
                .thenCombine(updatePercents, (balances, percents) ->
                        IntStream
                                .range(0, balances.size())
                                .mapToDouble(i -> balances.get(i) * percents.get(i))
                                .boxed()
                                .collect(Collectors.toList()));
        if (resBalances.get().size() != 0) {
            System.out.println("Баланс на аккаунтах пользователя обновлен");
        }
    }
}
