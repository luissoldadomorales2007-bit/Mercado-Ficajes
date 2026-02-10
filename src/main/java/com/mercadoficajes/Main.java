package com.mercadoficajes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación Mercado de Ficajes
 * Punto de entrada del programa
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar la vista principal desde el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mercadoficajes/view/MainView.fxml"));
        Parent root = loader.load();

        // Configurar la escena
        Scene scene = new Scene(root, 1200, 800);

        // Cargar estilos CSS
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        // Configurar el stage principal
        primaryStage.setTitle("Mercado de Ficajes");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

