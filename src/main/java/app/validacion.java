package app;

public class validacion {

    public static boolean nombreClienteValido(String nombre) {
        return nombre.matches("^[A-Za-z]+$");
    }
}

