package com.sbrf.reboot.repository.fluentAPI;

import com.sbrf.reboot.repository.impl.CustomerH2Repository;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Drop<E> extends CustomerH2Repository implements FluentSQLDrop<E> {
    private String sqlString;
    @Getter
    private boolean result = false;

    @Override
    public FluentSQLDrop<E> table(String name) {
        sqlString += " TABLE ";
        sqlString += " " + name + " ;";
        return this;
    }

    @Override
    public FluentSQLDrop<E> execute() {
        try (Connection conn = DriverManager.getConnection(getDB_URL(), getUSER(), getPASS())) {
            try (PreparedStatement pstm = conn.prepareStatement(sqlString)) {
                int rows = pstm.executeUpdate();
                System.out.println(rows + " rows added");
                result = true;
            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error get connection: " + e);
        }
        return this;
    }

    public static Drop<String> drop() {
        Drop<String> base = new Drop<>();
        base.sqlString = "DROP";

        return base;
    }
}
