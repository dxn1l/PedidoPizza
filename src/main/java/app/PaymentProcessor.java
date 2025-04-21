package app;

public class PaymentProcessor {
    public boolean procesarPago(Pedido pedido) {
        // Simulación de pago
        System.out.println("💳 Procesando pago de $" + pedido.getTotal() + " para " + pedido.getCliente());
        return true; // Simulamos éxito
    }
}
