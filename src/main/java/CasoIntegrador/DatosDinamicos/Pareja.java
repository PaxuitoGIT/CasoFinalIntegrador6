package CasoIntegrador.DatosDinamicos;

public class Pareja<A,B> {
    private A primerElemento;
    private B segundoElemento;

    public Pareja(A primerElemento, B segundoElemento) {
        this.primerElemento = primerElemento;
        this.segundoElemento = segundoElemento;
    }

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
