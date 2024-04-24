package CasoIntegrador.DatosDinamicos;

public class Pareja<A,B> { // Se define una clase genérica con dos parámetros de tipo
    private A primerElemento;
    private B segundoElemento;

    // Constructor de la clase
    public Pareja(A primerElemento, B segundoElemento) {
        this.primerElemento = primerElemento;
        this.segundoElemento = segundoElemento;
    }

    // Getters y Setters de la clase
    public A getPrimerElemento() {
        return primerElemento;
    }

    public void setPrimerElemento(A primerElemento) {
        this.primerElemento = primerElemento;
    }

    public B getSegundoElemento() {
        return segundoElemento;
    }

    public void setSegundoElemento(B segundoElemento) {
        this.segundoElemento = segundoElemento;
    }
}
