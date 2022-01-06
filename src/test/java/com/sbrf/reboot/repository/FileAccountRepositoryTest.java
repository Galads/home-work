package com.sbrf.reboot.repository;

import com.sbrf.reboot.repository.impl.FileAccountRepository;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FileAccountRepositoryTest {

    AccountRepository accountRepository;

    @Test
    void onlyPersonalAccounts() throws IOException {
        String filePath = "src/main/resources/Accounts.txt";
        accountRepository = new FileAccountRepository(filePath);

        long clientId = 1L;
        Set<Long> actualAccounts = accountRepository.getAllAccountsByClientId(clientId);

        Set<Long> expected = new HashSet<Long>() {{
            add(111L);
            add(222L);
            add(333L);
        }};

        actualAccounts.forEach(e -> assertTrue(expected.contains(e)));
    }

    @Test
    void failGetAllAccountsByClientId() {
        long clientId = 1L;

        String filePath = "somePath";

        accountRepository = new FileAccountRepository(filePath);

        assertThrows(FileNotFoundException.class, () -> accountRepository.getAllAccountsByClientId(clientId));
    }

    @Test
    void successUpdateNumberByClientId() throws IOException {
        long clientId = 1L;

        String filePath = "src/main/resources/Accounts.txt";
        accountRepository = new FileAccountRepository(filePath);

        assertTrue(accountRepository.updateClientNumber(clientId, 111, 222));
    }

    @Test
    void failUpdateNumberByClientId() throws IOException {
        long clientId = 3L;

        String filePath = "src/main/resources/Accounts.txt";
        accountRepository = new FileAccountRepository(filePath);

        assertFalse(accountRepository.updateClientNumber(clientId, 111, 888));
    }
}