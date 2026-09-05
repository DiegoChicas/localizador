package sv.edu.utec.datos;

import sv.edu.utec.modelo.Vehiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {

    public void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS vehiculo (" +
                "id INT PRIMARY KEY, marca VARCHAR(50), modelo VARCHAR(50), anio INT)";
        try (Connection cn = ConexionDB.obtenerConexion();
             Statement st = cn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla: " + e.getMessage());
        }
    }

    public void insertar(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculo (id, marca, modelo, anio) VALUES (?, ?, ?, ?)";
        try (Connection cn = ConexionDB.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, vehiculo.getId());
            ps.setString(2, vehiculo.getMarca());
            ps.setString(3, vehiculo.getModelo());
            ps.setInt(4, vehiculo.getAnio());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    public List<Vehiculo> listar() {
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        String sql = "SELECT id, marca, modelo, anio FROM vehiculo";
        try (Connection cn = ConexionDB.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vehiculo v = new Vehiculo(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("modelo"), // Corregido: lee la columna "modelo"
                        rs.getInt("anio")
                );
                listaVehiculos.add(v);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return listaVehiculos;
    }

    public boolean actualizar(Vehiculo vehiculo) {
        String sql = "UPDATE vehiculo SET marca = ?, modelo = ?, anio = ? WHERE id = ?";
        try (Connection cn = ConexionDB.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, vehiculo.getMarca());
            ps.setString(2, vehiculo.getModelo());
            ps.setInt(3, vehiculo.getAnio());
            ps.setInt(4, vehiculo.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM vehiculo WHERE id = ?";
        try (Connection cn = ConexionDB.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
}