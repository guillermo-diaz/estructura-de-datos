package estructuras.jerarquicas.dinamicas;
/**
 *
 * @author Guille
 */
public class NodoGen {
    private Object elem;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho; 

    public NodoGen(Object elto, NodoGen hIzq, NodoGen hDer) {
        elem = elto;
        hijoIzquierdo = hIzq;
        hermanoDerecho = hDer;
    }
    public Object getElem(){
        return elem;
    }
    public NodoGen getHijoIzquierdo(){
        return hijoIzquierdo;
    }
    public NodoGen getHermanoDerecho(){
        return hermanoDerecho;
    }

    public void setElem(Object elto){
        elem = elto;
    }
    public void setHijoIzquierdo(NodoGen hIzq){
        hijoIzquierdo = hIzq;
    }
    public void setHermanoDerecho(NodoGen hDer){
        hermanoDerecho = hDer;
    }

}
