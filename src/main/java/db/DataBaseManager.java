package db;

import app.Pedido;
import app.validacion;

import java.io.InputStream;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class DataBaseManager {

    private String url ;
    private String user;
    private String password;

    public DataBaseManager() {
        cargarConfiguracion();
    }

    private void cargarConfiguracion() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            Properties props = new Properties();
            if (input == null) {
                throw new RuntimeException("No se pudo cargar el archivo application.properties");
            }
            props.load(input);
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // 🟩 Crear un pedido
    public void crearPedido(String cliente, String items, double total) {
        if (!validacion.nombreClienteValido(cliente)) {
            System.out.println("❌ Nombre inválido: solo letras sin espacios ni caracteres especiales.");
            return;
        }
        String sql = "INSERT INTO pedidos (cliente, items, total) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente);
            stmt.setString(2, items);
            stmt.setDouble(3, total);
            stmt.executeUpdate();
            System.out.println("✅ Pedido creado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🟦 Leer un pedido por ID
    public void obtenerPedido(int id) {
        String sql = "SELECT * FROM pedidos WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int idPedido = rs.getInt("id");
                String cliente = rs.getString("cliente");
                String itemsTexto = rs.getString("items");
                double total = rs.getDouble("total");

                Map<String, Integer> contador = new LinkedHashMap<>();
                for (String item : itemsTexto.split(", ")) {
                    String nombre = item.split(" \\(\\$")[0]; // Solo el nombre, sin precio
                    contador.put(nombre, contador.getOrDefault(nombre, 0) + 1);
                }

                System.out.println("🧾 Pedido #" + idPedido + " - Cliente: " + cliente);
                for (Map.Entry<String, Integer> entry : contador.entrySet()) {
                    String nombre = entry.getKey();
                    int cantidad = entry.getValue();
                    String multiplicador = (cantidad > 1) ? " \u001B[33m(x" + cantidad + ")\u001B[0m" : ""; // amarillo
                    System.out.println("   🍕 " + nombre + multiplicador);
                }
                System.out.printf("💰 Total: \u001B[32m$%.2f\u001B[0m%n", total); // total en verde

            } else {
                System.out.println("⚠️ Pedido no encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🟨 Actualizar pedido
    public void actualizarPedido(int id, String items, double total) {
        String sql = "UPDATE pedidos SET items = ?, total = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, items);
            stmt.setDouble(2, total);
            stmt.setInt(3, id);
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("✅ Pedido actualizado.");
            } else {
                System.out.println("⚠️ No se encontró el pedido a actualizar.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🟥 Eliminar pedido
    public void eliminarPedido(int id) {
        String sql = "DELETE FROM pedidos WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("🗑️ Pedido eliminado.");
            } else {
                System.out.println("⚠️ No se encontró el pedido a eliminar.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inicializarBaseDeDatos() {
        String sql = """
        CREATE TABLE IF NOT EXISTS pedidos (
            id INT AUTO_INCREMENT PRIMARY KEY,
            cliente VARCHAR(100) NOT NULL,
            items TEXT NOT NULL,
            total DOUBLE NOT NULL
        )
        """;

        String sqlPagados = """
        CREATE TABLE IF NOT EXISTS pedidos_pagados (
            id INT AUTO_INCREMENT PRIMARY KEY,
            cliente VARCHAR(100) NOT NULL,
            items TEXT NOT NULL,
            total DOUBLE NOT NULL,
            fecha_pago TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        )
        """;

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            stmt.execute(sqlPagados);
            System.out.println("✅ Tablas de pedidos incializadas.");
        } catch (SQLException e) {
            System.out.println("❌ Error al inicializar la base de datos:");
            e.printStackTrace();
        }
    }

    public Pedido obtenerPedidoPorId(int id) {
        String sql = "SELECT * FROM pedidos WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String cliente = rs.getString("cliente");
                String items = rs.getString("items");
                double total = rs.getDouble("total");
                return new Pedido(id, cliente, items, total);
            } else {
                System.out.println("⚠️ Pedido no encontrado.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void registrarPago(Pedido pedido) {
        String sql = "INSERT INTO pedidos_pagados (cliente, items, total) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pedido.getCliente());
            stmt.setString(2, pedido.getItems());
            stmt.setDouble(3, pedido.getTotal());
            stmt.executeUpdate();
            System.out.println("🧾 Pedido archivado en 'pedidos_pagados'.");
        } catch (SQLException e) {
            System.out.println("❌ Error al registrar el pago:");
            e.printStackTrace();
        }
    }

}

