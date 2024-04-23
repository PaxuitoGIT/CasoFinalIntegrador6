package CasoIntegrador.DatosDinamicos;

import java.util.ArrayList;
import java.util.List;

public class ListaDatos {
    private List<Double> datosReales;
    private List<Pareja<Integer, Integer>> datosPares;

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

    public void agregarDatoPar(Pareja<Integer, Integer> dato) {
        datosPares.add(dato);
    }

    public void eliminarDatoPar(Pareja<Integer, Integer> dato) {
        datosPares.remove(dato);
    }

    public void modificarDatoPar(int index, Pareja<Integer, Integer> dato) {
        datosPares.set(index, dato);
    }

    public List<Double> getDatosReales() {
        return datosReales;
    }

    public void setDatosReales(List<Double> datosReales) {
        this.datosReales = datosReales;
    }

    public List<Pareja<Integer, Integer>> getDatosPares() {
        return datosPares;
    }

    public void setDatosPares(List<Pareja<Integer, Integer>> datosPares) {
        this.datosPares = datosPares;
    }
}
