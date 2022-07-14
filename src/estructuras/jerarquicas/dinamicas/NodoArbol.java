package estructuras.jerarquicas.dinamicas;
/**
 *
 * @author Guille
 */
public class NodoArbol {
    private Object elem;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    public NodoArbol(Object e, NodoArbol izq, NodoArbol der){
        elem = e;
        izquierdo = izq;
        derecho = der; 
    }

    public Object getElem(){
        return elem;
    }
    public NodoArbol getIzquierdo(){
        return izquierdo;
    }
    public NodoArbol getDerecho(){
        return derecho;
    }

    public void setElem(Object elem){
        this.elem = elem;
    }
    public void setIzquierdo(NodoArbol izquierdo){
        this.izquierdo = izquierdo;
    }
    public void setDerecho(NodoArbol derecho){
        this.derecho = derecho;
    }

}
