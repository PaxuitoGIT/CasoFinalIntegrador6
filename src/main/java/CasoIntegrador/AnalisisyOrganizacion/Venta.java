package CasoIntegrador.AnalisisyOrganizacion;

class Venta {
    String producto;
    int cantidad;

    // Constructor de la clase Venta
    public Venta(String producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    // Getters de la clase Venta
    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }
}

