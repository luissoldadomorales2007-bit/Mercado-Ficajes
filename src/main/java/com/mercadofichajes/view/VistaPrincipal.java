package com.mercadofichajes.view;

import com.mercadofichajes.model.entities.Jugador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Vista única principal - Solo tabla de jugadores estilo Transfermarkt
 */
public class VistaPrincipal {

    private BorderPane contenedor;
    private TableView<Jugador> tablaJugadores;
    private ObservableList<Jugador> listaJugadores;
    private TextField txtBuscar;

    public VistaPrincipal() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        contenedor = new BorderPane();
        contenedor.setStyle("-fx-background-color: #f8f9fa;");

        // === BARRA SUPERIOR ===
        VBox topBox = new VBox(15);
        topBox.setPadding(new Insets(20, 30, 20, 30));
        topBox.setStyle("-fx-background-color: white; -fx-border-color: #dee2e6; -fx-border-width: 0 0 1 0;");

        Label titulo = new Label("⚽ Mercado de Fichajes");
        titulo.setStyle(
            "-fx-font-size: 28px; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: #212529;"
        );

        // Campo de búsqueda
        txtBuscar = new TextField();
        txtBuscar.setPromptText("🔍 Buscar jugador por nombre, equipo o posición...");
        txtBuscar.setPrefWidth(400);
        txtBuscar.setStyle(
            "-fx-font-size: 14px; " +
            "-fx-padding: 10; " +
            "-fx-border-color: #ced4da; " +
            "-fx-border-radius: 5; " +
            "-fx-background-radius: 5;"
        );

        topBox.getChildren().addAll(titulo, txtBuscar);

        // === TABLA DE JUGADORES ===
        tablaJugadores = new TableView<>();
        listaJugadores = FXCollections.observableArrayList();
        tablaJugadores.setItems(listaJugadores);
        tablaJugadores.setStyle(
            "-fx-background-color: white; " +
            "-fx-font-size: 13px;"
        );

        // Columna: # (Número)
        TableColumn<Jugador, Integer> colNumero = new TableColumn<>("#");
        colNumero.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNumero.setPrefWidth(50);
        colNumero.setStyle("-fx-alignment: CENTER;");

        // Columna: Jugador (con nombre destacado)
        TableColumn<Jugador, String> colJugador = new TableColumn<>("Jugador");
        colJugador.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colJugador.setPrefWidth(250);
        colJugador.setCellFactory(column -> new TableCell<Jugador, String>() {
            @Override
            protected void updateItem(String nombre, boolean empty) {
                super.updateItem(nombre, empty);
                if (empty || nombre == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(nombre);
                    setStyle("-fx-font-weight: bold; -fx-text-fill: #0066cc;");
                }
            }
        });

        // Columna: Posición
        TableColumn<Jugador, String> colPosicion = new TableColumn<>("Posición");
        colPosicion.setCellValueFactory(new PropertyValueFactory<>("posicion"));
        colPosicion.setPrefWidth(150);

        // Columna: Fecha de Nacimiento/Edad
        TableColumn<Jugador, Integer> colEdad = new TableColumn<>("F. Nacim./Edad");
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colEdad.setPrefWidth(150);
        colEdad.setCellFactory(column -> new TableCell<Jugador, Integer>() {
            @Override
            protected void updateItem(Integer edad, boolean empty) {
                super.updateItem(edad, empty);
                if (empty || edad == null) {
                    setText(null);
                } else {
                    // Formato: (edad)
                    setText("(" + edad + ")");
                }
            }
        });

        // Columna: Equipo
        TableColumn<Jugador, String> colEquipo = new TableColumn<>("Equipo");
        colEquipo.setCellValueFactory(new PropertyValueFactory<>("nombreEquipo"));
        colEquipo.setPrefWidth(200);

        // Columna: Valor de mercado
        TableColumn<Jugador, Double> colValor = new TableColumn<>("Valor de mercado");
        colValor.setCellValueFactory(new PropertyValueFactory<>("valorMercado"));
        colValor.setPrefWidth(180);
        colValor.setStyle("-fx-alignment: CENTER-RIGHT;");
        colValor.setCellFactory(column -> new TableCell<Jugador, Double>() {
            @Override
            protected void updateItem(Double valor, boolean empty) {
                super.updateItem(valor, empty);
                if (empty || valor == null) {
                    setText(null);
                    setStyle("");
                } else {
                    // Formato: X,XX mill. €
                    double millones = valor / 1000000;
                    setText(String.format("%.2f mill. €", millones));
                    setStyle("-fx-font-weight: bold; -fx-alignment: CENTER-RIGHT;");
                }
            }
        });

        // Añadir todas las columnas
        tablaJugadores.getColumns().addAll(
            colNumero,
            colJugador,
            colPosicion,
            colEdad,
            colEquipo,
            colValor
        );

        // Estilo de las filas (efecto hover)
        tablaJugadores.setRowFactory(tv -> {
            TableRow<Jugador> row = new TableRow<>();
            row.setOnMouseEntered(event -> {
                if (!row.isEmpty()) {
                    row.setStyle("-fx-background-color: #f0f8ff;");
                }
            });
            row.setOnMouseExited(event -> {
                row.setStyle("");
            });
            return row;
        });

        // Placeholder cuando no hay datos
        tablaJugadores.setPlaceholder(new Label("No hay jugadores para mostrar"));

        // === CONTENEDOR CON PADDING ===
        VBox centerBox = new VBox();
        centerBox.setPadding(new Insets(20, 30, 20, 30));
        centerBox.getChildren().add(tablaJugadores);
        centerBox.setStyle("-fx-background-color: #f8f9fa;");

        // === LAYOUT PRINCIPAL ===
        contenedor.setTop(topBox);
        contenedor.setCenter(centerBox);
    }

    public Scene getEscena() {
        return new Scene(contenedor, 1100, 700);
    }

    // Getters
    public TableView<Jugador> getTablaJugadores() {
        return tablaJugadores;
    }

    public ObservableList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public TextField getTxtBuscar() {
        return txtBuscar;
    }
}

