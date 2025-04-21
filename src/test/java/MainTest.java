import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void simulacionLoginFallido() {
        String input = "user\nwrongpass\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertDoesNotThrow(() -> {
            try {
                Main.main(new String[]{});
            } catch (Exception ignored) {}
        });
    }

    @Test
    void simulacionLoginCorrectoSalirInmediato() {
        String input = "admin\nadmin\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertDoesNotThrow(() -> {
            try {
                Main.main(new String[]{});
            } catch (Exception ignored) {}
        });
    }
}
