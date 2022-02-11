package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerH2RepositoryTest {

    private static CustomerRepository customerRepository;

    @BeforeAll
    public static void before() throws SQLException {
        customerRepository = new CustomerH2Repository();
    }

    @Test
    void getAll() throws SQLException {
        boolean tomCreated = customerRepository.createCustomer("Tom", "tom@ya.ru");

        List<Customer> all = customerRepository.getAll();

        assertTrue(all.size() != 0);
    }

    @Test
    void createCustomer() throws SQLException {

        boolean mariaCreated = customerRepository.createCustomer("Maria", "maria98@ya.ru");

        assertTrue(mariaCreated);
    }

    @Test
    void existCustomer() throws SQLException {
        boolean userCreated = customerRepository.createCustomer("GALADS", "galads98@ya.ru");

        boolean exist1 = customerRepository.isExist("Galads");
        boolean exist2 = customerRepository.isExist("GALADS");

        assertFalse(exist1);
        assertTrue(exist2);
    }

    @Test
    void deleteCustomer() throws SQLException {
        boolean userCreated1 = customerRepository.createCustomer("Lin", "lin@ya.ru");
        boolean userCreated2 = customerRepository.createCustomer("Lin", "lin@ya.ru");

        customerRepository.deleteWhere("Lin", "lin@ya.ru");
    }
}