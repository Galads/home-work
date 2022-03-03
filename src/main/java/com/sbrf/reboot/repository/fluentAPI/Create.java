package com.sbrf.reboot.repository.fluentAPI;

import com.sbrf.reboot.repository.impl.CustomerH2Repository;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Create<E> extends CustomerH2Repository implements FluentSQLCreate<E> {
    private String sqlString;
    @Getter
    private boolean result = false;

    @Override
    public FluentSQLCreate<E> table(String name) {
        sqlString += " " + name + " (\n";
        return this;
    }

    @Override
    public FluentSQLCreate<E> column(String... names) {
        for (String name : names) {
            sqlString += " " + name + " ";
        }
        sqlString += ",\n";
        return this;
    }

    @Override
    public Create<E> execute() {
        sqlString = sqlString.replaceAll(",$", "");
        sqlString += ");\n";

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

    public static Create<String> create() {
        Create<String> base = new Create<>();
        base.sqlString = "CREATE TABLE";
        return base;
    }
}
