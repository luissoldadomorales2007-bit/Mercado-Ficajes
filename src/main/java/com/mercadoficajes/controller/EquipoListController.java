    private Equipo obtenerEquipoDesdeFormulario() {
        Equipo equipo = new Equipo();
        equipo.setNombre(txtNombre.getText());
        equipo.setPais(txtPais.getText());
        equipo.setLiga(txtLiga.getText());
        equipo.setPresupuesto(Double.parseDouble(txtPresupuesto.getText()));
        return equipo;
    }
}
package com.mercadoficajes.controller;

import com.mercadoficajes.model.entities.Equipo;
import com.mercadoficajes.model.services.EquipoService;
import com.mercadoficajes.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Controlador para la lista de equipos
 */
public class EquipoListController {

    @FXML private TableView<Equipo> tablaEquipos;
    @FXML private TableColumn<Equipo, Integer> colId;
    @FXML private TableColumn<Equipo, String> colNombre;
    @FXML private TableColumn<Equipo, String> colPais;
    @FXML private TableColumn<Equipo, String> colLiga;
    @FXML private TableColumn<Equipo, Double> colPresupuesto;

    @FXML private TextField txtNombre;
    @FXML private TextField txtPais;
    @FXML private TextField txtLiga;
    @FXML private TextField txtPresupuesto;
    @FXML private Button btnGuardar;
    @FXML private Button btnActualizar;

    private EquipoService equipoService;
    private ObservableList<Equipo> equiposData;
    private Equipo equipoSeleccionado;

    @FXML
    public void initialize() {
        equipoService = new EquipoService();

        configurarTabla();
        cargarEquipos();

        // Listener para selección
        tablaEquipos.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    mostrarDetallesEquipo(newValue);
                }
            }
        );

        btnActualizar.setDisable(true);
    }

    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        colLiga.setCellValueFactory(new PropertyValueFactory<>("liga"));
        colPresupuesto.setCellValueFactory(new PropertyValueFactory<>("presupuesto"));

        colPresupuesto.setCellFactory(column -> new TableCell<Equipo, Double>() {
            @Override
            protected void updateItem(Double presupuesto, boolean empty) {
                super.updateItem(presupuesto, empty);
                if (empty || presupuesto == null) {
                    setText(null);
                } else {
                    setText(String.format("€%.2f", presupuesto));
                }
            }
        });
    }

    private void cargarEquipos() {
        List<Equipo> equipos = equipoService.obtenerTodosLosEquipos();
        equiposData = FXCollections.observableArrayList(equipos);
        tablaEquipos.setItems(equiposData);
    }

    private void mostrarDetallesEquipo(Equipo equipo) {
        equipoSeleccionado = equipo;
        txtNombre.setText(equipo.getNombre());
        txtPais.setText(equipo.getPais());
        txtLiga.setText(equipo.getLiga());
        txtPresupuesto.setText(String.valueOf(equipo.getPresupuesto()));

        btnGuardar.setDisable(true);
        btnActualizar.setDisable(false);
    }

    @FXML
    private void guardarEquipo() {
        try {
            Equipo equipo = obtenerEquipoDesdeFormulario();

            if (equipoService.crearEquipo(equipo)) {
                AlertUtil.mostrarExito("Éxito", "Equipo creado correctamente");
                cargarEquipos();
                limpiarFormulario();
            } else {
                AlertUtil.mostrarError("Error", "No se pudo crear el equipo");
            }
        } catch (Exception e) {
            AlertUtil.mostrarError("Error", e.getMessage());
        }
    }

    @FXML
    private void actualizarEquipo() {
        if (equipoSeleccionado == null) {
            AlertUtil.mostrarAdvertencia("Advertencia", "Selecciona un equipo primero");
            return;
        }

        try {
            Equipo equipo = obtenerEquipoDesdeFormulario();
            equipo.setId(equipoSeleccionado.getId());

            if (equipoService.actualizarEquipo(equipo)) {
                AlertUtil.mostrarExito("Éxito", "Equipo actualizado correctamente");
                cargarEquipos();
                limpiarFormulario();
            } else {
                AlertUtil.mostrarError("Error", "No se pudo actualizar el equipo");
            }
        } catch (Exception e) {
            AlertUtil.mostrarError("Error", e.getMessage());
        }
    }

    @FXML
    private void eliminarEquipo() {
        Equipo equipo = tablaEquipos.getSelectionModel().getSelectedItem();

        if (equipo == null) {
            AlertUtil.mostrarAdvertencia("Advertencia", "Selecciona un equipo para eliminar");
            return;
        }

        boolean confirmar = AlertUtil.mostrarConfirmacion(
            "Confirmar eliminación",
            "¿Estás seguro de eliminar a " + equipo.getNombre() + "?"
        );

        if (confirmar) {
            if (equipoService.eliminarEquipo(equipo.getId())) {
                AlertUtil.mostrarExito("Éxito", "Equipo eliminado correctamente");
                cargarEquipos();
                limpiarFormulario();
            } else {
                AlertUtil.mostrarError("Error", "No se pudo eliminar el equipo");
            }
        }
    }

    @FXML
    private void nuevoEquipo() {
        limpiarFormulario();
    }

    private void limpiarFormulario() {
        txtNombre.clear();
        txtPais.clear();
        txtLiga.clear();
        txtPresupuesto.clear();
        equipoSeleccionado = null;
        btnGuardar.setDisable(false);
        btnActualizar.setDisable(true);
    }

package com.mercadoficajes.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Controlador principal de la aplicación
 * Gestiona la navegación entre diferentes vistas
 */
public class MainController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Label lblTitulo;

    @FXML
    public void initialize() {
        // Cargar la vista de inicio por defecto
        cargarVistaInicio();
    }

    /**
     * Carga la vista de inicio
     */
    @FXML
    private void cargarVistaInicio() {
        lblTitulo.setText("Bienvenido al Mercado de Ficajes");
        // Aquí puedes cargar una vista de dashboard o inicio
    }

    /**
     * Carga la lista de jugadores
     */
    @FXML
    private void cargarListaJugadores() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mercadoficajes/view/JugadorListView.fxml"));
            Parent jugadorListView = loader.load();
            mainBorderPane.setCenter(jugadorListView);
            lblTitulo.setText("Gestión de Jugadores");
        } catch (IOException e) {
            System.err.println("Error al cargar la vista de jugadores: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Carga la lista de equipos
     */
    @FXML
    private void cargarListaEquipos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mercadoficajes/view/EquipoListView.fxml"));
            Parent equipoListView = loader.load();
            mainBorderPane.setCenter(equipoListView);
            lblTitulo.setText("Gestión de Equipos");
        } catch (IOException e) {
            System.err.println("Error al cargar la vista de equipos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Carga la vista de transferencias
     */
    @FXML
    private void cargarTransferencias() {
        lblTitulo.setText("Transferencias");
        // Implementar carga de vista de transferencias
    }

    /**
     * Muestra información sobre la aplicación
     */
    @FXML
    private void mostrarAcercaDe() {
        com.mercadoficajes.util.AlertUtil.mostrarInfo(
            "Acerca de",
            "Mercado de Ficajes v1.0\n\n" +
            "Sistema de gestión de mercado de ficajes de fútbol\n" +
            "Desarrollado con Java y JavaFX siguiendo arquitectura MVC"
        );
    }

    /**
     * Cierra la aplicación
     */
    @FXML
    private void salir() {
        System.exit(0);
    }
}

