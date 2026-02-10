package com.mercadoficajes.util;

/**
 * Utilidad para validaciones de datos
 */
public class ValidationUtil {

    /**
     * Valida que una cadena no esté vacía
     */
    public static boolean esTextoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    /**
     * Valida que un número sea positivo
     */
    public static boolean esNumeroPositivo(double numero) {
        return numero > 0;
    }

    /**
     * Valida que un número esté en un rango
     */
    public static boolean estaEnRango(int numero, int min, int max) {
        return numero >= min && numero <= max;
    }

    /**
     * Valida un email básico
     */
    public static boolean esEmailValido(String email) {
        if (!esTextoValido(email)) {
            return false;
        }
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }

    /**
     * Valida que un texto tenga una longitud mínima
     */
    public static boolean longitudMinima(String texto, int minimo) {
        return esTextoValido(texto) && texto.length() >= minimo;
    }

    /**
     * Valida que un precio sea válido
     */
    public static boolean esPrecioValido(String precio) {
        try {
            double valor = Double.parseDouble(precio);
            return valor >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Valida que una edad sea válida para un jugador
     */
    public static boolean esEdadValida(int edad) {
        return edad >= 16 && edad <= 45;
    }
}
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.mercadoficajes</groupId>
    <artifactId>mercado-ficajes</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>Mercado de Ficajes</name>
    <description>Sistema de gestión de mercado de ficajes de fútbol</description>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <javafx.version>17.0.2</javafx.version>
    </properties>

    <dependencies>
        <!-- JavaFX -->
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>${javafx.version}</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>${javafx.version}</version>
        </dependency>

        <!-- MySQL Connector -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.33</version>
        </dependency>

        <!-- Opcional: Logging -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.7</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>2.0.7</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>11</source>
                    <target>11</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.openjfx</groupId>
                <artifactId>javafx-maven-plugin</artifactId>
                <version>0.0.8</version>
                <configuration>
                    <mainClass>com.mercadoficajes.Main</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

