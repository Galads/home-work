package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;
import lombok.Getter;
import lombok.NonNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CustomerH2Repository implements CustomerRepository {
    private final String JDBC_DRIVER = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:~/my_db";

    private final String USER = "sa";
    private final String PASS = "";

    public CustomerH2Repository() {
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();

        String sqlSel = "Select * from my_db;";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            try (PreparedStatement pstmt = conn.prepareStatement(sqlSel)) {
                ResultSet set = pstmt.executeQuery();
                while (set.next()) {
                    Customer customer = new Customer();

                    customer.setId(set.getLong("id"));
                    customer.setName(set.getString("name"));
                    customer.setEMail(set.getString("eMail"));

                    customers.add(customer);
                }
            }
        }
        return customers;
    }

    @Override
    public boolean createCustomer(String name, String eMail) {
/*
        <=EXAMPLES=>
        INSERT INTO my_db (id, name, email) VALUES (123,'1_1', '1_0')

        CREATE TABLE my_db (
            id LONG NOT NULL AUTO_INCREMENT,
            name VARCHAR(50) NOT NULL,
            eMAIL VARCHAR(50) NOT NULL
        );
*/
        boolean res = false;
        String sqlInsert = "INSERT INTO my_db (name, eMail) VALUES(?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {

                pstmt.setString(1, name);
                pstmt.setString(2, eMail);

                int rows = pstmt.executeUpdate();

                System.out.println(rows + " rows added");
                res = true;
            } catch (SQLException e) {
                System.out.println("Error insert in database: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error get connection: " + e);
        }
        return res;
    }

    @Override
    public boolean isExist(@NonNull String name) {
        // <-example-> SELECT id, name, email FROM my_db where name = 'Maria';
        boolean res = false;
        String sqlInsert = "SELECT name FROM my_db WHERE NAME=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {

                pstmt.setString(1, name);
                ResultSet set = pstmt.executeQuery();

                if (set.next()) res = true;
            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error get connection: " + e);
        }
        return res;
    }

    @Override
    public boolean deleteWhere(@NonNull String name, @NonNull String eMail) {
        // <-example-> DELETE FROM my_db WHERE name = 'Maria';

        boolean res = false;
        String sqlDelete = "DELETE FROM my_db WHERE name=? AND email=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            try (PreparedStatement pstmt = conn.prepareStatement(sqlDelete)) {

                pstmt.setString(1, name);
                pstmt.setString(2, eMail);
                int rows = pstmt.executeUpdate();

                System.out.println(rows + " rows removed");

                if (rows > 0) res = true;
            } catch (SQLException e) {
                System.out.println("Error : " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error get connection: " + e);
        }
        return res;
    }
}
