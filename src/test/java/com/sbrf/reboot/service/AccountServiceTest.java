package com.sbrf.reboot.service;

import com.sbrf.reboot.AccountService;
import com.sbrf.reboot.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    AccountService accountService;

    @BeforeEach
    void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);

        accountService = new AccountService(accountRepository);
    }

    @Test
    void contractExist() {
        Set<Long> accounts = new HashSet();
        accounts.add(111L);

        long clientId = 1L;
        long contractNumber = 111L;

        when(accountRepository.getAllAccountsByClientId(clientId)).thenReturn(accounts);

        assertTrue(accountService.isClientHasContract(clientId, contractNumber));
    }

    @Test
    void contractNotExist() {
        Set<Long> accounts = new HashSet();
        accounts.add(222L);

        long clientId = 1L;
        long contractNumber = 111L;

        when(accountRepository.getAllAccountsByClientId(clientId)).thenReturn(accounts);

        assertFalse(accountService.isClientHasContract(clientId, contractNumber));
    }

    @Test
    void repositoryHasTreeMethods() {
        assertEquals(2, AccountRepository.class.getMethods().length);
    }

    @Test
    void serviceHasTreeMethods() {
        assertEquals(2, AccountService.class.getMethods().length - Object.class.getMethods().length);
    }

    @Test
    void historyExist() {
        Set<String> paymentHistory = new HashSet<>();
        long clientId = 2L;
        long contractNumber = 435L;
        String pay = "250.45";

        Calendar calendar = new GregorianCalendar();
        calendar.set(2021, Calendar.DECEMBER, 21, 12, 45);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strDate = df.format(calendar.getTime());

        paymentHistory.add("Client ID: " + clientId + "\nContract ID: " + contractNumber +
                "\nFirst name: Alex\nLast name: Hilton\nDate: " + strDate + "\nSum: " + pay);


        when(accountRepository.getAllPaymentDetails(clientId)).thenReturn(paymentHistory);

        assertTrue(accountService.isClientHasPaymentHistory(clientId));
    }

    @Test
    void historyNotExist() {
        Set<String> paymentHistory = new HashSet<>();
        long clientId = 2L;

        when(accountRepository.getAllPaymentDetails(clientId)).thenReturn(paymentHistory);

        assertFalse(accountService.isClientHasPaymentHistory(clientId));
    }
}