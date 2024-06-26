package CasoIntegrador.AnalisisyOrganizacion;

class TransaccionVenta {
    String producto, cliente, fecha;
    int cantidad;

    // Constructor de la clase TransaccionVenta
    public TransaccionVenta(String producto, int cantidad, String cliente, String fecha) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.cliente = cliente;
        this.fecha = fecha;
    }
// Getters de la clase TransaccionVenta
    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getCliente() {
        return cliente;
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "TransaccionVenta{" +
                "producto='" + producto + '\'' +
                ", cantidad=" + cantidad +
                ", cliente='" + cliente + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}