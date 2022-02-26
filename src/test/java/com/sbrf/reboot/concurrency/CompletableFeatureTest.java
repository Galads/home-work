package com.sbrf.reboot.concurrency;

import com.sbrf.reboot.service.CalculationService;
import com.sbrf.reboot.service.ReportService;
import com.sbrf.reboot.service.SomeService;
import com.sbrf.reboot.service.SomeServiceCount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.Mockito;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.mockito.Mockito.*;

public class CompletableFeatureTest {

    @Test
    public void successCompletableFeature() throws ExecutionException, InterruptedException, TimeoutException {
        ReportService reportService = Mockito.mock(ReportService.class);

        when(reportService.sendReport("Отправляю отчет")).then(e -> {

            Thread.sleep(Duration.ofSeconds(3).toMillis());
            return "SUCCESS";
        });

        SomeService someService = new SomeService(reportService);

        someService.doSomething();

        verify(reportService, times(1)).sendReport("Отправляю отчет");
    }

    @Test
    @Timeout(value = 3)
    public void successAsyncCalculationService() throws ExecutionException, InterruptedException {
        CalculationService calcServ = Mockito.mock(CalculationService.class);
        List<Long> list = Arrays.asList(100L, 200L, 300L, 400L);

        when(calcServ.getBalance()).then(e -> {
            Thread.sleep(Duration.ofSeconds(2).toMillis());
            return list;
        });

        when(calcServ.updatePercentFromEach()).then(e -> {
            Thread.sleep(Duration.ofSeconds(2).toMillis());
            return Arrays.asList(0.9, 0.8, 0.7, 0.6);
        });

        SomeServiceCount someService = new SomeServiceCount(calcServ);
        someService.doSomething();

        verify(calcServ, times(1)).getBalance();
        verify(calcServ, times(1)).updatePercentFromEach();
    }
}
