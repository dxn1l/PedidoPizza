package App;

import app.MenuPizza;
import app.Pedido;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;

class MenuPizzaTest {
    @Test
    void armarPedidoDesdeMenuSimulado() {
        String input = "1\ns\n2\nn\n"; // Margarita + Pepperoni
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Pedido pedido = MenuPizza.armarPedidoDesdeMenu(scanner, "Luis");
        assertEquals("Luis", pedido.getCliente());
        assertTrue(pedido.getItems().contains("Margarita"));
        assertTrue(pedido.getItems().contains("Pepperoni"));
        assertEquals(6.99 + 7.49, pedido.getTotal(), 0.01);
    }
}
