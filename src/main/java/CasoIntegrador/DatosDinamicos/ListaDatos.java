package CasoIntegrador.DatosDinamicos;

import java.util.ArrayList;
import java.util.List;

public class ListaDatos {
    private List<Double> datosReales;
    private List<Pareja<Integer, String>> datosPares;

    public ListaDatos() {
        this.datosReales = new ArrayList<>();
        this.datosPares = new ArrayList<>();
    }

    public void agregarDatoReal(Double dato) {
        datosReales.add(dato);
    }

    public void eliminarDatoReal(Double dato) {
        datosReales.remove(dato);
    }

    public void modificarDatoReal(int index, Double dato) {
        datosReales.set(index, dato);
    }

    public void agregarDatoPar(Pareja<Integer, String> dato) {
        datosPares.add(dato);
    }

    public void eliminarDatoPar(Pareja<Integer, String> dato) {
        datosPares.remove(dato);
    }

    public void modificarDatoPar(int index, Pareja<Integer, String> dato) {
        datosPares.set(index, dato);
    }

    public List<Double> getDatosReales() {
        return datosReales;
    }

    public void setDatosReales(List<Double> datosReales) {
        this.datosReales = datosReales;
    }

    public List<Pareja<Integer, String>> getDatosPares() {
        return datosPares;
    }

    public void setDatosPares(List<Pareja<Integer, String>> datosPares) {
        this.datosPares = datosPares;
    }
}