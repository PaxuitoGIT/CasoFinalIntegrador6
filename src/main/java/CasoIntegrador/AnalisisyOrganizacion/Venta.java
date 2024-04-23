package CasoIntegrador.AnalisisyOrganizacion;

class Venta {
    private String producto;
    private int cantidad;

    public Venta(String producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }
}

