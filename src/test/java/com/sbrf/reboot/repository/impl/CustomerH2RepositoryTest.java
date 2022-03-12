package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;
import com.sbrf.reboot.repository.fluentAPI.Create;
import com.sbrf.reboot.repository.fluentAPI.Drop;
import com.sbrf.reboot.repository.fluentAPI.FluentSQLCreate;
import com.sbrf.reboot.repository.fluentAPI.FluentSQLDrop;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerH2RepositoryTest {

    private static CustomerRepository customerRepository;

    @BeforeAll
    public static void before() {
        customerRepository = new CustomerH2Repository();
    }

    @AfterEach
    public void afterEachDropTable() {
        FluentSQLDrop<String> fsd = Drop
                .drop()
                .table("my_db")
                .execute();
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

        boolean res = customerRepository.deleteWhere("Lin", "lin@ya.ru");

        assertTrue(res);
    }

    @BeforeEach
    void createTable() {
        FluentSQLCreate<String> table = Create
                .create()
                .table("my_db")
                .column("id", "LONG", "NOT NULL", "AUTO_INCREMENT")
                .column("name", "VARCHAR(50)", "NOT NULL")
                .column("eMAIL", "VARCHAR(50)", "NOT NULL")
                .execute();
    }

    @Test
    void createTableSuccess() {
        String name = "new_db";
        FluentSQLCreate<String> table = Create
                .create()
                .table(name)
                .column("id", "LONG", "NOT NULL", "AUTO_INCREMENT")
                .column("name", "VARCHAR(50)", "NOT NULL")
                .column("eMAIL", "VARCHAR(50)", "NOT NULL")
                .execute();

        assertTrue(table.isResult());

        FluentSQLDrop<String> fsd = Drop
                .drop()
                .table(name)
                .execute();

        assertTrue(fsd.isResult());
    }
}