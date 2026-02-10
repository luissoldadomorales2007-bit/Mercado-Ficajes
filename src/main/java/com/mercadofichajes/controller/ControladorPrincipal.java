package com.mercadofichajes.controller;

import com.mercadofichajes.model.entities.Jugador;
import com.mercadofichajes.view.VistaPrincipal;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador único principal
 * SOLO muestra jugadores por defecto - SIN base de datos
 */
public class ControladorPrincipal {

    private Stage stage;
    private VistaPrincipal vista;
    private List<Jugador> todosLosJugadores; // Lista completa para búsqueda

    public ControladorPrincipal(Stage stage) {
        this.stage = stage;
        this.vista = new VistaPrincipal();
        this.todosLosJugadores = new ArrayList<>();
        inicializar();
    }

    private void inicializar() {
        // SIEMPRE añade jugadores por defecto
        añadirJugadoresPorDefecto();

        // Búsqueda en tiempo real
        vista.getTxtBuscar().textProperty().addListener((obs, old, nuevo) -> {
            filtrarJugadores(nuevo);
        });

        System.out.println("✅ Aplicación iniciada con " + todosLosJugadores.size() + " jugadores");
    }

    private void añadirJugadoresPorDefecto() {
        // Limpiar lista
        todosLosJugadores.clear();
        vista.getListaJugadores().clear();

        // Crear 20 jugadores de muestra
        todosLosJugadores.add(crearJugador(1, "Lamine Yamal", 17, "Extremo", 90000000, "FC Barcelona"));
        todosLosJugadores.add(crearJugador(2, "Chidera Ejuke", 26, "Extremo", 15000000, "Sevilla FC"));
        todosLosJugadores.add(crearJugador(3, "Pedri González", 21, "Centrocampista", 80000000, "FC Barcelona"));
        todosLosJugadores.add(crearJugador(4, "Gavi", 19, "Centrocampista", 90000000, "FC Barcelona"));
        todosLosJugadores.add(crearJugador(5, "Robert Lewandowski", 35, "Delantero", 15000000, "FC Barcelona"));
        todosLosJugadores.add(crearJugador(6, "Ronald Araújo", 25, "Defensa central", 70000000, "FC Barcelona"));
        todosLosJugadores.add(crearJugador(7, "Ter Stegen", 31, "Portero", 25000000, "FC Barcelona"));
        todosLosJugadores.add(crearJugador(8, "Vinicius Jr", 23, "Extremo", 120000000, "Real Madrid"));
        todosLosJugadores.add(crearJugador(9, "Jude Bellingham", 20, "Centrocampista", 150000000, "Real Madrid"));
        todosLosJugadores.add(crearJugador(10, "Thibaut Courtois", 31, "Portero", 45000000, "Real Madrid"));
        todosLosJugadores.add(crearJugador(11, "Federico Valverde", 25, "Centrocampista", 100000000, "Real Madrid"));
        todosLosJugadores.add(crearJugador(12, "Rodrygo", 23, "Delantero", 80000000, "Real Madrid"));
        todosLosJugadores.add(crearJugador(13, "Youssef En-Nesyri", 26, "Delantero", 25000000, "Sevilla FC"));
        todosLosJugadores.add(crearJugador(14, "Jesús Navas", 38, "Lateral derecho", 2000000, "Sevilla FC"));
        todosLosJugadores.add(crearJugador(15, "Lucas Ocampos", 29, "Extremo", 12000000, "Sevilla FC"));
        todosLosJugadores.add(crearJugador(16, "Antoine Griezmann", 32, "Delantero", 30000000, "Atlético Madrid"));
        todosLosJugadores.add(crearJugador(17, "Jan Oblak", 30, "Portero", 40000000, "Atlético Madrid"));
        todosLosJugadores.add(crearJugador(18, "Marcos Llorente", 28, "Centrocampista", 50000000, "Atlético Madrid"));
        todosLosJugadores.add(crearJugador(19, "José Gayà", 28, "Lateral izquierdo", 20000000, "Valencia CF"));
        todosLosJugadores.add(crearJugador(20, "Hugo Duro", 24, "Delantero", 15000000, "Valencia CF"));

        // Añadir TODOS a la tabla
        vista.getListaJugadores().addAll(todosLosJugadores);

        System.out.println("✅ Añadidos " + todosLosJugadores.size() + " jugadores por defecto");
    }

    private Jugador crearJugador(int id, String nombre, int edad, String posicion, double valor, String equipo) {
        Jugador jugador = new Jugador();
        jugador.setId(id);
        jugador.setNombre(nombre);
        jugador.setEdad(edad);
        jugador.setPosicion(posicion);
        jugador.setValorMercado(valor);
        jugador.setNombreEquipo(equipo);
        return jugador;
    }

    private void filtrarJugadores(String busqueda) {
        // Limpiar tabla
        vista.getListaJugadores().clear();

        // Si no hay búsqueda, mostrar todos
        if (busqueda == null || busqueda.trim().isEmpty()) {
            vista.getListaJugadores().addAll(todosLosJugadores);
            return;
        }

        // Filtrar por texto
        String busquedaLower = busqueda.toLowerCase();
        for (Jugador j : todosLosJugadores) {
            if (j.getNombre().toLowerCase().contains(busquedaLower) ||
                j.getNombreEquipo().toLowerCase().contains(busquedaLower) ||
                j.getPosicion().toLowerCase().contains(busquedaLower)) {
                vista.getListaJugadores().add(j);
            }
        }
    }

    public void mostrar() {
        stage.setScene(vista.getEscena());
        stage.setTitle("⚽ Mercado de Fichajes - Transfermarkt Style");
        stage.show();
    }
}

