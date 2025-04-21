package App;

import app.Pedido;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {
    @Test
    void crearPedidoYAccederAtributos() {
        Pedido p = new Pedido(1, "Ana", "Margarita ($6.99), Barbacoa ($8.50)", 15.49);
        assertEquals(1, p.getId());
        assertEquals("Ana", p.getCliente());
        assertEquals("Margarita ($6.99), Barbacoa ($8.50)", p.getItems());
        assertEquals(15.49, p.getTotal());
    }
}

