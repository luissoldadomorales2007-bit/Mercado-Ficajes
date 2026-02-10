package com.mercadoficajes.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para gestionar la conexión a la base de datos
 * Implementa el patrón Singleton
 */
public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/mercado_ficajes";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión a la base de datos establecida correctamente");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver de MySQL no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null || instance.getConnection() == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null || instance.getConnection() == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la conexión: " + e.getMessage());
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}

