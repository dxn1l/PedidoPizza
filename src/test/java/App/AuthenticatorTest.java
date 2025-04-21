package App;

import app.Authenticator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticatorTest {
    @Test
    void autenticarCorrecto() {
        Authenticator auth = new Authenticator();
        assertTrue(auth.autenticar("admin", "admin"));
    }

    @Test
    void autenticarIncorrecto() {
        Authenticator auth = new Authenticator();
        assertFalse(auth.autenticar("usuario", "1234"));
    }
}
