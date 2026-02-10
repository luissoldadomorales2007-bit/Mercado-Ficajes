package com.mercadoficajes.controller;

import com.mercadoficajes.model.entities.Equipo;
import com.mercadoficajes.model.entities.Jugador;
import com.mercadoficajes.model.services.EquipoService;
import com.mercadoficajes.model.services.JugadorService;
import com.mercadoficajes.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Controlador para la lista de jugadores
 */
public class JugadorListController {

    @FXML private TableView<Jugador> tablaJugadores;
    @FXML private TableColumn<Jugador, Integer> colId;
    @FXML private TableColumn<Jugador, String> colNombre;
    @FXML private TableColumn<Jugador, String> colPosicion;
    @FXML private TableColumn<Jugador, Integer> colEdad;
    @FXML private TableColumn<Jugador, String> colNacionalidad;
    @FXML private TableColumn<Jugador, Double> colPrecio;
    @FXML private TableColumn<Jugador, String> colEquipo;

    @FXML private TextField txtNombre;
    @FXML private ComboBox<String> cbPosicion;
    @FXML private TextField txtEdad;
    @FXML private TextField txtNacionalidad;
    @FXML private TextField txtPrecio;
    @FXML private ComboBox<Equipo> cbEquipo;
    @FXML private TextField txtBuscar;
    @FXML private Button btnGuardar;
    @FXML private Button btnActualizar;
    @FXML private Button btnNuevo;

    private JugadorService jugadorService;
    private EquipoService equipoService;
    private ObservableList<Jugador> jugadoresData;
    private Jugador jugadorSeleccionado;

    @FXML
    public void initialize() {
        jugadorService = new JugadorService();
        equipoService = new EquipoService();

        configurarTabla();
        configurarComboBoxes();
        cargarJugadores();
        cargarEquipos();

        // Listener para selección en la tabla
        tablaJugadores.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    mostrarDetallesJugador(newValue);
                }
            }
        );

        btnActualizar.setDisable(true);
    }

    /**
     * Configura las columnas de la tabla
     */
    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPosicion.setCellValueFactory(new PropertyValueFactory<>("posicion"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colNacionalidad.setCellValueFactory(new PropertyValueFactory<>("nacionalidad"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colEquipo.setCellValueFactory(new PropertyValueFactory<>("nombreEquipo"));

        // Formato para el precio
        colPrecio.setCellFactory(column -> new TableCell<Jugador, Double>() {
            @Override
            protected void updateItem(Double precio, boolean empty) {
                super.updateItem(precio, empty);
                if (empty || precio == null) {
                    setText(null);
                } else {
                    setText(String.format("€%.2f", precio));
                }
            }
        });
    }

    /**
     * Configura los ComboBoxes
     */
    private void configurarComboBoxes() {
        ObservableList<String> posiciones = FXCollections.observableArrayList(
            "Portero", "Defensa", "Centrocampista", "Delantero"
        );
        cbPosicion.setItems(posiciones);
    }

    /**
     * Carga todos los jugadores en la tabla
     */
    private void cargarJugadores() {
        List<Jugador> jugadores = jugadorService.obtenerTodosLosJugadores();
        jugadoresData = FXCollections.observableArrayList(jugadores);
        tablaJugadores.setItems(jugadoresData);
    }

    /**
     * Carga los equipos en el ComboBox
     */
    private void cargarEquipos() {
        List<Equipo> equipos = equipoService.obtenerTodosLosEquipos();
        ObservableList<Equipo> equiposData = FXCollections.observableArrayList(equipos);
        cbEquipo.setItems(equiposData);
    }

    /**
     * Muestra los detalles del jugador seleccionado
     */
    private void mostrarDetallesJugador(Jugador jugador) {
        jugadorSeleccionado = jugador;
        txtNombre.setText(jugador.getNombre());
        cbPosicion.setValue(jugador.getPosicion());
        txtEdad.setText(String.valueOf(jugador.getEdad()));
        txtNacionalidad.setText(jugador.getNacionalidad());
        txtPrecio.setText(String.valueOf(jugador.getPrecio()));

        // Seleccionar el equipo en el ComboBox
        for (Equipo e : cbEquipo.getItems()) {
            if (e.getId() == jugador.getEquipoId()) {
                cbEquipo.setValue(e);
                break;
            }
        }

        btnGuardar.setDisable(true);
        btnActualizar.setDisable(false);
    }

    /**
     * Guarda un nuevo jugador
     */
    @FXML
    private void guardarJugador() {
        try {
            Jugador jugador = obtenerJugadorDesdeFormulario();

            if (jugadorService.crearJugador(jugador)) {
                AlertUtil.mostrarExito("Éxito", "Jugador creado correctamente");
                cargarJugadores();
                limpiarFormulario();
            } else {
                AlertUtil.mostrarError("Error", "No se pudo crear el jugador");
            }
        } catch (IllegalArgumentException e) {
            AlertUtil.mostrarError("Error de validación", e.getMessage());
        } catch (Exception e) {
            AlertUtil.mostrarError("Error", "Error al guardar: " + e.getMessage());
        }
    }

    /**
     * Actualiza el jugador seleccionado
     */
    @FXML
    private void actualizarJugador() {
        if (jugadorSeleccionado == null) {
            AlertUtil.mostrarAdvertencia("Advertencia", "Selecciona un jugador primero");
            return;
        }

        try {
            Jugador jugador = obtenerJugadorDesdeFormulario();
            jugador.setId(jugadorSeleccionado.getId());

            if (jugadorService.actualizarJugador(jugador)) {
                AlertUtil.mostrarExito("Éxito", "Jugador actualizado correctamente");
                cargarJugadores();
                limpiarFormulario();
            } else {
                AlertUtil.mostrarError("Error", "No se pudo actualizar el jugador");
            }
        } catch (IllegalArgumentException e) {
            AlertUtil.mostrarError("Error de validación", e.getMessage());
        } catch (Exception e) {
            AlertUtil.mostrarError("Error", "Error al actualizar: " + e.getMessage());
        }
    }

    /**
     * Elimina el jugador seleccionado
     */
    @FXML
    private void eliminarJugador() {
        Jugador jugador = tablaJugadores.getSelectionModel().getSelectedItem();

        if (jugador == null) {
            AlertUtil.mostrarAdvertencia("Advertencia", "Selecciona un jugador para eliminar");
            return;
        }

        boolean confirmar = AlertUtil.mostrarConfirmacion(
            "Confirmar eliminación",
            "¿Estás seguro de eliminar a " + jugador.getNombre() + "?"
        );

        if (confirmar) {
            if (jugadorService.eliminarJugador(jugador.getId())) {
                AlertUtil.mostrarExito("Éxito", "Jugador eliminado correctamente");
                cargarJugadores();
                limpiarFormulario();
            } else {
                AlertUtil.mostrarError("Error", "No se pudo eliminar el jugador");
            }
        }
    }

    /**
     * Busca jugadores por palabra clave
     */
    @FXML
    private void buscarJugador() {
        String keyword = txtBuscar.getText();
        List<Jugador> jugadores = jugadorService.buscarJugadores(keyword);
        jugadoresData = FXCollections.observableArrayList(jugadores);
        tablaJugadores.setItems(jugadoresData);
    }

    /**
     * Prepara el formulario para un nuevo jugador
     */
    @FXML
    private void nuevoJugador() {
        limpiarFormulario();
    }

    /**
     * Limpia el formulario
     */
    private void limpiarFormulario() {
        txtNombre.clear();
        cbPosicion.setValue(null);
        txtEdad.clear();
        txtNacionalidad.clear();
        txtPrecio.clear();
        cbEquipo.setValue(null);
        jugadorSeleccionado = null;
        btnGuardar.setDisable(false);
        btnActualizar.setDisable(true);
    }

    /**
     * Obtiene un objeto Jugador desde el formulario
     */
    private Jugador obtenerJugadorDesdeFormulario() {
        Jugador jugador = new Jugador();
        jugador.setNombre(txtNombre.getText());
        jugador.setPosicion(cbPosicion.getValue());
        jugador.setEdad(Integer.parseInt(txtEdad.getText()));
        jugador.setNacionalidad(txtNacionalidad.getText());
        jugador.setPrecio(Double.parseDouble(txtPrecio.getText()));

        if (cbEquipo.getValue() != null) {
            jugador.setEquipoId(cbEquipo.getValue().getId());
        }

        return jugador;
    }
}

