package estructuras.grafos;
import estructuras.lineales.dinamicas.*;

public class GrafoEtiquetado {
    //Grafo normal etiquetado 
    private NodoVert inicio;

    public GrafoEtiquetado(){
        this.inicio = null;
    }

    public boolean insertarVertice(Object elem){
        boolean flag = false;
        NodoVert aux = this.ubicarVertice(elem);

        if (aux == null){
            flag = true;
            this.inicio = new NodoVert(elem, this.inicio, null);
        } 
        
        /*NodoVert aux = this.inicio; //para moverme por los vertices

        while (flag && aux != null){
            if (aux.getElem().equals(elem)){ //esta repetido
                flag = false;
            } else {
                aux = aux.getSigVertice();
            }
        }
        if (flag){ //si no esta repetido
            NodoVert nuevo = new NodoVert(elem, this.inicio, null);
            this.inicio = nuevo;
        }
        */
        
        return flag;
    }

    public boolean eliminarVertice(Object elem){
        boolean flag = false;
        NodoVert aux = this.inicio, anterior = null;

        while (aux != null && !aux.getElem().equals(elem)){
            anterior = aux;
            aux = aux.getSigVertice();
        }

        if (aux != null){
            NodoAdy ady = aux.getPrimerAdy();
            while (ady != null){
                eliminarArcoAux(ady.getVertice(), elem);
                ady = ady.getSigAdyacente();
            }
            if (anterior == null){ //caso especial el nodo a eliminar esta en el inicio de la lista de nodos
                this.inicio = this.inicio.getSigVertice();
            } else {
                anterior.setSigVertice(aux.getSigVertice()); 
            }
        }
        return flag;
    }

    private boolean eliminarArcoAux(NodoVert n, Object elem){
        //elimina un arco hacia el nodo elem, retorna un boolean para verificar si fue encontrado
        NodoAdy anterior = null, aux = n.getPrimerAdy();
        boolean flag = false;

        while(!flag && aux != null){
            if (elem.equals(aux.getVertice().getElem())){
                flag = true;
            } else {
                anterior = aux;
                aux = aux.getSigAdyacente();
            }
        } 
        if (flag){
            if (anterior == null){
                n.setPrimerAdy(aux.getSigAdyacente());
            } else {
                anterior.setSigAdyacente(aux.getSigAdyacente());
            }
        }
        return flag;
    }

    private NodoVert ubicarVertice(Object buscado){
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getElem().equals(buscado)){
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public boolean insertarArco(Object origen, Object destino, int etiqueta){
        boolean flag = false;

        NodoVert aux, auxOrig, auxDest;
        aux = this.inicio; 
        auxOrig = null;
        auxDest = null;

        while (aux != null && (auxOrig == null || auxDest == null)){ //busco a los 2 nodos
            if (aux.getElem().equals(origen)){
                auxOrig = aux;
            } 
            if (aux.getElem().equals(destino)){
                auxDest = aux;
            }
            aux = aux.getSigVertice();
        }

        if (auxOrig != null && auxDest != null){
            flag = true;
            conectarAdy(auxOrig, auxDest, etiqueta);
            conectarAdy(auxDest, auxOrig, etiqueta);
        }

        return flag;
    }

    public boolean eliminarArco(Object origen, Object destino){
        boolean flag = false; 
        NodoVert auxOrig = ubicarVertice(origen);
        
        if (auxOrig != null){ //si el origen fue encontrado, busco en sus adyacentes a el destino
            NodoVert auxDest = null;
            NodoAdy anterior = null;
            NodoAdy dest = auxOrig.getPrimerAdy();

            while (!flag && dest != null){
                if (dest.getVertice().getElem().equals(destino)){
                    flag = true;
                    auxDest = dest.getVertice();
                } else {
                    anterior = dest;
                    dest = dest.getSigAdyacente();
                }
            }

            if (flag){ //si estan conectados
                //desconecto desde el origen primero
                if (anterior == null){ //caso especial es el primer ady
                    auxOrig.setPrimerAdy(dest.getSigAdyacente());
                } else {
                    anterior.setSigAdyacente(dest.getSigAdyacente());
                }

                //desconecto desde el destino
                eliminarArcoAux(auxDest, origen);
            }
        }
        return flag;
    }

    private void conectarAdy(NodoVert n, NodoVert adyacente, int etiqueta){
        //precondicion de este metodo: no deben ser nulos
        NodoAdy nuevo = new NodoAdy(adyacente, n.getPrimerAdy(), etiqueta);
        n.setPrimerAdy(nuevo);
    }

    public boolean existeVertice(Object elem){
        boolean flag;
        if (this.ubicarVertice(elem) == null){
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    public boolean existeArco(Object origen, Object destino){
        boolean flag = false;
        NodoVert auxO = this.ubicarVertice(origen);

        if (auxO != null){
            NodoAdy ady = auxO.getPrimerAdy();
            while (ady != null){
                if (destino.equals(ady.getVertice().getElem())){
                    flag = true;
                } else {
                    ady = ady.getSigAdyacente();
                }
            }
        }
        return flag;
    }

    public Lista listarEnProfundidad(){
        Lista ls = new Lista();
        return ls;
    }

    public boolean esVacio(){
        return this.inicio == null;
    }

    public void vaciar(){
        this.inicio = null;
    }
    
    public String toString(){
        String cad;

        if (this.inicio == null){
            cad = "Grafo vacio";
        } else {
            cad = toStringAux(this.inicio);
        }
        return cad;
    }

    private String toStringAux(NodoVert n){
        /*retorna un string con el grafo
        precondicion: n no debe ser vacio
        */
        String ret = "";

        
        ret = "+ ("+n.getElem()+"): \n       ";
        NodoAdy ady = n.getPrimerAdy();

        if (ady != null){
            while (ady != null){
                ret = ret + "->  "+ady.getVertice().getElem()+": "+ady.getEtiqueta()+"\n       ";
                ady = ady.getSigAdyacente();
                    
            }
        } else {
            ret = ret + "------ \n";
        }
        
        if (n.getSigVertice() != null){
            ret = ret + "\n "+toStringAux(n.getSigVertice());
        }

        return ret;
    }
    
}

