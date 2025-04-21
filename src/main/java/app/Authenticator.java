package app;

public class Authenticator {
    public boolean autenticar(String usuario, String contraseña) {
        // Simulación simple de login
        return "admin".equals(usuario) && "admin".equals(contraseña);
    }
}