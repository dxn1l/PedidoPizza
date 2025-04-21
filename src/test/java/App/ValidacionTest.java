package App;

import app.validacion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidacionTest {
    @Test
    void nombreValido() {
        assertTrue(validacion.nombreClienteValido("Carlos"));
    }

    @Test
    void nombreInvalidoConNumeros() {
        assertFalse(validacion.nombreClienteValido("Carlos123"));
    }

    @Test
    void nombreInvalidoConEspacios() {
        assertFalse(validacion.nombreClienteValido("Carlos Perez"));
    }

    @Test
    void nombreInvalidoConCaracteresEspeciales() {
        assertFalse(validacion.nombreClienteValido("Car@los!"));
    }
}
