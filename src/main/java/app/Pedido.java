package app;

public class Pedido {
    private int id;
    private String cliente;
    private String items;
    private double total;

    public Pedido(String cliente, String items, double total) {
        this.cliente = cliente;
        this.items = items;
        this.total = total;
    }

    public Pedido(int id, String cliente, String items, double total) {
        this(cliente, items, total);
        this.id = id;
    }

    public int getId() { return id; }
    public String getCliente() { return cliente; }
    public String getItems() { return items; }
    public double getTotal() { return total; }

    public void setItems(String items) { this.items = items; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return "Pedido #" + id + " - Cliente: " + cliente + ", Items: " + items + ", Total: $" + total;
    }
}

