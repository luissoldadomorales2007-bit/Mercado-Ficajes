package com.mercadoficajes.model.services;

import com.mercadoficajes.model.dao.JugadorDAO;
import com.mercadoficajes.model.entities.Jugador;

import java.util.List;

/**
 * Servicio para la lógica de negocio de Jugador
 */
public class JugadorService {

    private final JugadorDAO jugadorDAO;

    public JugadorService() {
        this.jugadorDAO = new JugadorDAO();
    }

    /**
     * Obtiene todos los jugadores
     */
    public List<Jugador> obtenerTodosLosJugadores() {
        return jugadorDAO.findAll();
    }

    /**
     * Obtiene un jugador por ID
     */
    public Jugador obtenerJugadorPorId(int id) {
        return jugadorDAO.findById(id);
    }

    /**
     * Crea un nuevo jugador
     */
    public boolean crearJugador(Jugador jugador) {
        // Aquí puedes agregar validaciones de negocio
        if (jugador.getNombre() == null || jugador.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del jugador no puede estar vacío");
        }
        if (jugador.getEdad() < 16 || jugador.getEdad() > 45) {
            throw new IllegalArgumentException("La edad debe estar entre 16 y 45 años");
        }
        if (jugador.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }

        return jugadorDAO.save(jugador);
    }

    /**
     * Actualiza un jugador existente
     */
    public boolean actualizarJugador(Jugador jugador) {
        if (jugador.getId() <= 0) {
            throw new IllegalArgumentException("ID de jugador inválido");
        }
        if (jugador.getNombre() == null || jugador.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del jugador no puede estar vacío");
        }

        return jugadorDAO.update(jugador);
    }

    /**
     * Elimina un jugador
     */
    public boolean eliminarJugador(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID de jugador inválido");
        }
        return jugadorDAO.delete(id);
    }

    /**
     * Busca jugadores por palabra clave
     */
    public List<Jugador> buscarJugadores(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return obtenerTodosLosJugadores();
        }
        return jugadorDAO.search(keyword);
    }

    /**
     * Calcula el precio promedio de los jugadores
     */
    public double calcularPrecioPromedio() {
        List<Jugador> jugadores = obtenerTodosLosJugadores();
        if (jugadores.isEmpty()) {
            return 0.0;
        }

        double total = jugadores.stream()
                .mapToDouble(Jugador::getPrecio)
                .sum();

        return total / jugadores.size();
    }
}

