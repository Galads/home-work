package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerH2Repository implements CustomerRepository {

    private final String JDBC_DRIVER = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:~/my_db";

    private final String USER = "sa";
    private final String PASS = "";

    public CustomerH2Repository() {
        Connection conn = null;
        Statement stmt = null;

    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();

        return customers;
    }

    @Override
    public boolean createCustomer(String name, String eMail) {
        return false;
    }
}


