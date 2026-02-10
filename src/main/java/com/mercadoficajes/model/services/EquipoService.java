package com.mercadoficajes.model.services;

import com.mercadoficajes.model.dao.EquipoDAO;
import com.mercadoficajes.model.entities.Equipo;

import java.util.List;

/**
 * Servicio para la lógica de negocio de Equipo
 */
public class EquipoService {

    private final EquipoDAO equipoDAO;

    public EquipoService() {
        this.equipoDAO = new EquipoDAO();
    }

    /**
     * Obtiene todos los equipos
     */
    public List<Equipo> obtenerTodosLosEquipos() {
        return equipoDAO.findAll();
    }

    /**
     * Obtiene un equipo por ID
     */
    public Equipo obtenerEquipoPorId(int id) {
        return equipoDAO.findById(id);
    }

    /**
     * Crea un nuevo equipo
     */
    public boolean crearEquipo(Equipo equipo) {
        if (equipo.getNombre() == null || equipo.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del equipo no puede estar vacío");
        }
        if (equipo.getPresupuesto() < 0) {
            throw new IllegalArgumentException("El presupuesto no puede ser negativo");
        }

        return equipoDAO.save(equipo);
    }

    /**
     * Actualiza un equipo existente
     */
    public boolean actualizarEquipo(Equipo equipo) {
        if (equipo.getId() <= 0) {
            throw new IllegalArgumentException("ID de equipo inválido");
        }
        return equipoDAO.update(equipo);
    }

    /**
     * Elimina un equipo
     */
    public boolean eliminarEquipo(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID de equipo inválido");
        }
        return equipoDAO.delete(id);
    }
}
package com.mercadoficajes.model.dao;

import com.mercadoficajes.model.entities.Jugador;
import com.mercadoficajes.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para gestionar operaciones de base de datos de Jugador
 */
public class JugadorDAO {

    /**
     * Obtiene todos los jugadores
     */
    public List<Jugador> findAll() {
        List<Jugador> jugadores = new ArrayList<>();
        String sql = "SELECT j.*, e.nombre as nombre_equipo FROM jugadores j " +
                    "LEFT JOIN equipos e ON j.equipo_id = e.id ORDER BY j.nombre";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                jugadores.add(extractJugadorFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener jugadores: " + e.getMessage());
        }
        return jugadores;
    }

    /**
     * Busca un jugador por ID
     */
    public Jugador findById(int id) {
        String sql = "SELECT j.*, e.nombre as nombre_equipo FROM jugadores j " +
                    "LEFT JOIN equipos e ON j.equipo_id = e.id WHERE j.id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractJugadorFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar jugador: " + e.getMessage());
        }
        return null;
    }

    /**
     * Guarda un nuevo jugador
     */
    public boolean save(Jugador jugador) {
        String sql = "INSERT INTO jugadores (nombre, posicion, edad, nacionalidad, precio, equipo_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, jugador.getNombre());
            pstmt.setString(2, jugador.getPosicion());
            pstmt.setInt(3, jugador.getEdad());
            pstmt.setString(4, jugador.getNacionalidad());
            pstmt.setDouble(5, jugador.getPrecio());
            pstmt.setInt(6, jugador.getEquipoId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    jugador.setId(generatedKeys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar jugador: " + e.getMessage());
        }
        return false;
    }

    /**
     * Actualiza un jugador existente
     */
    public boolean update(Jugador jugador) {
        String sql = "UPDATE jugadores SET nombre = ?, posicion = ?, edad = ?, " +
                    "nacionalidad = ?, precio = ?, equipo_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, jugador.getNombre());
            pstmt.setString(2, jugador.getPosicion());
            pstmt.setInt(3, jugador.getEdad());
            pstmt.setString(4, jugador.getNacionalidad());
            pstmt.setDouble(5, jugador.getPrecio());
            pstmt.setInt(6, jugador.getEquipoId());
            pstmt.setInt(7, jugador.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar jugador: " + e.getMessage());
        }
        return false;
    }

    /**
     * Elimina un jugador
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM jugadores WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar jugador: " + e.getMessage());
        }
        return false;
    }

    /**
     * Busca jugadores por nombre o equipo
     */
    public List<Jugador> search(String keyword) {
        List<Jugador> jugadores = new ArrayList<>();
        String sql = "SELECT j.*, e.nombre as nombre_equipo FROM jugadores j " +
                    "LEFT JOIN equipos e ON j.equipo_id = e.id " +
                    "WHERE j.nombre LIKE ? OR e.nombre LIKE ? OR j.nacionalidad LIKE ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                jugadores.add(extractJugadorFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar jugadores: " + e.getMessage());
        }
        return jugadores;
    }

    /**
     * Extrae un objeto Jugador de un ResultSet
     */
    private Jugador extractJugadorFromResultSet(ResultSet rs) throws SQLException {
        Jugador jugador = new Jugador();
        jugador.setId(rs.getInt("id"));
        jugador.setNombre(rs.getString("nombre"));
        jugador.setPosicion(rs.getString("posicion"));
        jugador.setEdad(rs.getInt("edad"));
        jugador.setNacionalidad(rs.getString("nacionalidad"));
        jugador.setPrecio(rs.getDouble("precio"));
        jugador.setEquipoId(rs.getInt("equipo_id"));
        jugador.setNombreEquipo(rs.getString("nombre_equipo"));

        Date fecha = rs.getDate("fecha_registro");
        if (fecha != null) {
            jugador.setFechaRegistro(fecha.toLocalDate());
        }

        return jugador;
    }
}

