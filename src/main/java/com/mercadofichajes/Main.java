package com.mercadofichajes;

import com.mercadofichajes.controller.ControladorPrincipal;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación
 * Vista única - Todo en una sola pantalla
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // Crear el controlador principal único
        ControladorPrincipal controller = new ControladorPrincipal(stage);

        // Mostrar la pantalla única
        controller.mostrar();
    }

    public static void main(String[] args) {
        launch(args);
    }
}