package com.mercadoficajes.model.dao;

import com.mercadoficajes.model.entities.Equipo;
import com.mercadoficajes.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para gestionar operaciones de base de datos de Equipo
 */
public class EquipoDAO {

    /**
     * Obtiene todos los equipos
     */
    public List<Equipo> findAll() {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipos ORDER BY nombre";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                equipos.add(extractEquipoFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener equipos: " + e.getMessage());
        }
        return equipos;
    }

    /**
     * Busca un equipo por ID
     */
    public Equipo findById(int id) {
        String sql = "SELECT * FROM equipos WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractEquipoFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar equipo: " + e.getMessage());
        }
        return null;
    }

    /**
     * Guarda un nuevo equipo
     */
    public boolean save(Equipo equipo) {
        String sql = "INSERT INTO equipos (nombre, pais, liga, presupuesto) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, equipo.getNombre());
            pstmt.setString(2, equipo.getPais());
            pstmt.setString(3, equipo.getLiga());
            pstmt.setDouble(4, equipo.getPresupuesto());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    equipo.setId(generatedKeys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar equipo: " + e.getMessage());
        }
        return false;
    }

    /**
     * Actualiza un equipo existente
     */
    public boolean update(Equipo equipo) {
        String sql = "UPDATE equipos SET nombre = ?, pais = ?, liga = ?, presupuesto = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, equipo.getNombre());
            pstmt.setString(2, equipo.getPais());
            pstmt.setString(3, equipo.getLiga());
            pstmt.setDouble(4, equipo.getPresupuesto());
            pstmt.setInt(5, equipo.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar equipo: " + e.getMessage());
        }
        return false;
    }

    /**
     * Elimina un equipo
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM equipos WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar equipo: " + e.getMessage());
        }
        return false;
    }

    /**
     * Extrae un objeto Equipo de un ResultSet
     */
    private Equipo extractEquipoFromResultSet(ResultSet rs) throws SQLException {
        Equipo equipo = new Equipo();
        equipo.setId(rs.getInt("id"));
        equipo.setNombre(rs.getString("nombre"));
        equipo.setPais(rs.getString("pais"));
        equipo.setLiga(rs.getString("liga"));
        equipo.setPresupuesto(rs.getDouble("presupuesto"));
        return equipo;
    }
}

