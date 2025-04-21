package App;

import app.PaymentProcessor;
import app.Pedido;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

class PaymentProcessorTest {
    @Test
    void pagoConTarjetaExitoso() {
        String input = "1\n1234567812345678\n123\ns\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        PaymentProcessor processor = new PaymentProcessor();
        Pedido pedido = new Pedido("Juan", "Pepperoni ($7.49)", 7.49);
        assertTrue(processor.procesarPago(pedido));
    }

    @Test
    void pagoEnEfectivoCancelado() {
        String input = "2\nn\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        PaymentProcessor processor = new PaymentProcessor();
        Pedido pedido = new Pedido("Lucia", "Hawaiana ($7.99)", 7.99);
        assertFalse(processor.procesarPago(pedido));
    }
}

