import app.*;
import db.DataBaseManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Authenticator auth = new Authenticator();
        DataBaseManager db = new DataBaseManager();
        db.inicializarBaseDeDatos();
        PaymentProcessor payment = new PaymentProcessor();
        OrderManager orderManager = new OrderManager(db, payment);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Usuario: ");
        String user = scanner.nextLine();
        System.out.print("Contraseña: ");
        String pass = scanner.nextLine();

        if (!auth.autenticar(user, pass)) {
            System.out.println("❌ Acceso denegado.");
            return;
        }

        System.out.println("✅ Bienvenido al sistema de pedidos de pizza");

        while (true) {
            System.out.println("\n1. Crear pedido\n2. Ver pedido\n3. Actualizar pedido\n4. Eliminar pedido\n5. Pagar pedido\n0. Salir");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> {
                    System.out.print("Cliente: ");
                    String cliente = scanner.nextLine();
                    Pedido pedido = MenuPizza.armarPedidoDesdeMenu(scanner, cliente);
                    orderManager.crearPedido(pedido);
                }
                case 2 -> {
                    System.out.print("ID del pedido: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    orderManager.mostrarPedido(id);
                }
                case 3 -> {
                    System.out.print("ID del pedido: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    Pedido pedidoOriginal = orderManager.obtenerPedido(id);
                    if (pedidoOriginal == null) return;

                    Pedido pedidoEditado = MenuPizza.editarPedidoExistente(
                            scanner,
                            pedidoOriginal.getId(),
                            pedidoOriginal.getCliente(),
                            pedidoOriginal.getItems(),
                            pedidoOriginal.getTotal()
                    );

                    orderManager.actualizarPedido(
                            pedidoEditado.getId(),
                            pedidoEditado.getItems(),
                            pedidoEditado.getTotal()
                    );
                }

                case 4 -> {
                    System.out.print("ID a eliminar: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    orderManager.eliminarPedido(id);
                }
                case 5 -> {
                    System.out.print("Cliente: ");
                    String cliente = scanner.nextLine();
                    System.out.print("Items: ");
                    String items = scanner.nextLine();
                    System.out.print("Total: ");
                    double total = Double.parseDouble(scanner.nextLine());
                    Pedido pedido = new Pedido(cliente, items, total);
                    orderManager.pagar(pedido);
                }
                case 0 -> {
                    System.out.println("👋 Cerrando sistema...");
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }
}
