package app;

import db.DataBaseManager;

public class OrderManager {
    private final DataBaseManager db;
    private final PaymentProcessor payment;

    public OrderManager(DataBaseManager db, PaymentProcessor payment) {
        this.db = db;
        this.payment = payment;
    }

    public void crearPedido(Pedido pedido) {
        db.crearPedido(pedido.getCliente(), pedido.getItems(), pedido.getTotal());
    }

    public void actualizarPedido(int id, String items, double total) {
        db.actualizarPedido(id, items, total);
    }

    public void eliminarPedido(int id) {
        db.eliminarPedido(id);
    }

    public void mostrarPedido(int id) {
        db.obtenerPedido(id);
    }

    public void pagar(Pedido pedido) {
        if (payment.procesarPago(pedido)) {
            System.out.println("✅ Pago exitoso.");
        } else {
            System.out.println("❌ Error al procesar el pago.");
        }
    }

    public Pedido obtenerPedido(int id) {
        return db.obtenerPedidoPorId(id);
    }

}
