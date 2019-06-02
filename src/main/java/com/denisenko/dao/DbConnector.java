package com.denisenko.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

public class DbConnector {

    private static final String userName = "sa";
    private static final String password = "";
    private static final String connectionUrl = "jdbc:h2:tcp://localhost/~/test";

    public static Optional<Connection> connect() {
        Connection connection;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            return Optional.ofNullable(connection);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
