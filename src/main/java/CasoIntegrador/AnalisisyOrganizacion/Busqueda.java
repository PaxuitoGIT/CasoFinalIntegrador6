package CasoIntegrador.AnalisisyOrganizacion;

import CasoIntegrador.DatosDinamicos.ListaDatos;
import CasoIntegrador.DatosDinamicos.Pareja;

public class Busqueda {
    public static int buscarIdPorNombreEnLista(ListaDatos listaDatos, String nombre) {
        for (Pareja<Integer, String> pareja : listaDatos.getDatosPares()) {
            if (pareja.getSegundoElemento().equals(nombre)) {
                return pareja.getPrimerElemento();
            }
        }
        return -1; // Si no se encuentra el nombre, devolvemos -1
    }
}