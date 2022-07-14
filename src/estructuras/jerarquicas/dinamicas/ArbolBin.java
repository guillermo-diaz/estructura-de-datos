package estructuras.jerarquicas.dinamicas;

import estructuras.lineales.dinamicas.*;
/**
 *
 * @author Guille
 */

public class ArbolBin {
    private NodoArbol raiz;

    public ArbolBin() {
        raiz = null;
    }
    public boolean esVacio(){
        return  raiz == null;
    }
    public void vaciar(){
        this.raiz = null;
    }
    public int altura(){
        return alturaR(this.raiz);
    }
    private int alturaR(NodoArbol n){
        int ret;
        
        if (n != null){
            int izq = alturaR(n.getIzquierdo()); //cuento la altura del hijo izq
            int der = alturaR(n.getDerecho());  //cuento la altura del hijo der
            if (izq > der) { //si la altura del HI es mayor, le sumo la raiz y retorno 
                ret = izq +1;
            } else { //HD es mayor, le sumo la raiz en donde estoy parado y retorno
                ret = der +1;
            }
        } else {
            ret = -1; //error el arbol o el subarbol estan vacios
        }     
        
        
        return ret;
    }
    public int nivel(Object elem){
        return nivelAux(this.raiz, elem, 0);
    }
    private int nivelAux(NodoArbol n, Object elem, int lvl){
        int ret = -1; //error arbol o subarbol vacios

        if (n != null){ //existe al menos un nodo en el arbol o subarbol
            if (n.getElem().equals(elem)){ //elem encontrado, ret nivel
                ret = lvl;
            } else {
                ret = nivelAux(n.getIzquierdo(), elem, lvl + 1); //busco por la izq
                if (ret == -1){ //si no encontre el elem, busco a la der
                    ret = nivelAux(n.getDerecho(), elem, lvl + 1);  
                }   
            }
        } 
        return ret;
    }   
   

    public boolean insertar(Object elem, Object padre, char pos){
        boolean verif = true;

        if (this.esVacio()){
            this.raiz = new NodoArbol(elem, null, null);
        } else {
            NodoArbol aux = obtenerNodo(this.raiz, padre); //busco el padre del objeto

            if (aux == null){ //si el padre no está en el arbol reporta error
                verif = false;
            } else {
                if (pos == 'I' && aux.getIzquierdo() == null){ //si la pos es izq y el hijo izq del padre esta vacio lo insertamos
                    aux.setIzquierdo(new NodoArbol(elem, null, null));
                } else if (pos == 'D' && aux.getDerecho() == null){  ///si la pos es der y el hijo der del padre esta vacio lo insertamos
                    aux.setDerecho(new NodoArbol(elem, null, null));
                } else {
                    verif = false; //posicion ocupada, reporta error
                }
            }
        }
        return verif;
    }
    private NodoArbol obtenerNodo(NodoArbol nodo, Object elem){
        //Busca el nodo que contiene el elem ingresado, si no lo encuentra retorna null
        NodoArbol ret = null;
        
        if (nodo != null){
            if (nodo.getElem() == elem){ //elem encontrado
                ret = nodo;
            } else {
                ret = obtenerNodo(nodo.getIzquierdo(), elem); //evaluo hijo izq
                if (ret == null){
                    ret = obtenerNodo(nodo.getDerecho(), elem); //evaluo hijo der
                }
            }
        }

        return ret;
    }
    
    public Object padre(Object hijo){
        Object padre = null;
        if (this.raiz != null){
            padre = obtenerPadre(this.raiz, hijo, null); //envio al padre null en caso de que este hijo sea la raiz (no tiene padre)
        }
        return padre; 
    }

    private Object obtenerPadre(NodoArbol nodo, Object hijo, Object padreAux){
        //Dado un elem (hijo) devuelve el valor del nodo padre
        Object ret = null; 

        if (nodo != null){ 
            if (nodo.getElem().equals(hijo)){ //elemento encontrado, retorno el padre del parametro
                ret = padreAux;
            } else {
                ret = obtenerPadre(nodo.getIzquierdo(), hijo, nodo.getElem()); //evaluo lado izq y guardando en padreAux el elem actual
                if (ret == null){ //si no se encontro evaluo el der
                    ret = obtenerPadre(nodo.getDerecho(), hijo, nodo.getElem());
                }
            }
        }
        return ret;
    }

    public Lista listarPreorden() {
         //crea lista del arbol en recorrido preorden (raiz, izq, der)
        Lista listaElem = new Lista();
        preordenAux(this.raiz, listaElem);
        return listaElem;
    }

    private void preordenAux(NodoArbol nodo, Lista list) {
        if (nodo != null){
            list.insertar(nodo.getElem(),list.longitud()+1);
            preordenAux(nodo.getIzquierdo(), list);
            preordenAux(nodo.getDerecho(), list);
        }
    }

    public Lista listarInorden() {
         //crea lista del arbol en recorrido inorden (izq, raiz, der)
        Lista listaElem = new Lista();
        inordenAux(this.raiz, listaElem);
        return listaElem;
    }
    
    private void inordenAux(NodoArbol nodo, Lista list) {
        if (nodo != null){
            inordenAux(nodo.getIzquierdo(), list);
            list.insertar(nodo.getElem(),list.longitud()+1);
            inordenAux(nodo.getDerecho(), list);
        }
    }
    
    public Lista listarPosorden() {
         //crea lista del arbol en recorrido posorden (izq, der, raiz)
        Lista listaElem = new Lista();
        posordenAux(this.raiz, listaElem);
        return listaElem;
    }

    private void posordenAux(NodoArbol nodo, Lista list) {
        if (nodo != null){
            posordenAux(nodo.getIzquierdo(), list);
            posordenAux(nodo.getDerecho(), list);
            list.insertar(nodo.getElem(),list.longitud()+1);
        }
    }

    public Lista listarPorNiveles(){
        //crea lista del arbol en recorrido por niveles
        Lista lista = new Lista();
        if (this.raiz != null){
            Cola colaAux = new Cola();
            colaAux.poner(this.raiz); //pongo el primer elem
            NodoArbol nodoActual;
            
            while (!colaAux.esVacia()){ 
                nodoActual = (NodoArbol) colaAux.obtenerFrente();
                colaAux.sacar();
                lista.insertar(nodoActual.getElem(), lista.longitud()+1);
                
                if (nodoActual.getIzquierdo() != null){
                    colaAux.poner(nodoActual.getIzquierdo());
                }
                if (nodoActual.getDerecho() != null){
                    colaAux.poner(nodoActual.getDerecho());
                }
            }
        }
    
        return lista;
    }

    public String toString(){
        //devuelve un string con el arbol actual
        String cad = "Arbol Vacio";
        if (!this.esVacio()){
            cad = toStringAux(this.raiz);
        }
        return cad;
    }
    private String toStringAux(NodoArbol n){
        String cad = "";
        if (n != null){
            cad = cad +"("+ n.getElem() + ") ->  ";

            NodoArbol izq, der;
            izq = n.getIzquierdo(); 
            der = n.getDerecho();
            
            if (izq != null){ //si no es nulo imprimo el elem izq
                cad = cad + "HI: " + izq.getElem() + "    ";
            } else {
                cad = cad + "HI: -    ";
            }
            if (der != null){ //si no es nulo imprimo el elem der
                cad = cad + "HD: " + der.getElem() + "\n";
            } else {
                cad = cad + "HD: -\n";
            }
            cad = cad + toStringAux(izq); //voy al hijo izq para seguir imprimiendo
            cad = cad + toStringAux(der); //voy al hijo der para seguir imprimiendo
        }
        return cad;
    }

    public ArbolBin clone(){
        //crea un clon de el arbol actual
        ArbolBin clon = new ArbolBin();       
        clon.raiz = cloneAux(this.raiz);       
        return clon;
    }
    
    public NodoArbol cloneAux(NodoArbol n){
        NodoArbol nuevo;

        if (n != null){ 
            NodoArbol izq = cloneAux(n.getIzquierdo()); //consigo el subarbol izq
            NodoArbol der = cloneAux(n.getDerecho()); //consigo el subarbol der
            nuevo = new NodoArbol(n.getElem(), izq, der); //creo el nuevo nodo con el elem y con sus hijos
        } else {
            nuevo = null;
        }
        return nuevo;
    }


    //Ejercicios tp/simulacro

    public Lista obtenerAncestros(Object elem){
        //retorna una lista de los ancestros de un elem del arbol
        Lista ret = new Lista();
        obtenerAncestrosAux(this.raiz, ret, elem);
        return ret;
    }

    private boolean obtenerAncestrosAux(NodoArbol n1, Lista l1, Object elem){
        boolean flag = false; // determina si el elem esta en el arbol

        if (n1 != null){
            if (n1.getElem().equals(elem)){
                flag = true; //elem encontrado, ret true
            } else {
                flag = obtenerAncestrosAux(n1.getIzquierdo(), l1, elem); //busco a la izq
                if (!flag){ //si el elemento no esta en izq busco a la der
                    flag = obtenerAncestrosAux(n1.getDerecho(), l1, elem);
                }

                if (flag) { //si se encontro en alguno de los 2, inserto el ancestro en el q estoy parado
                    l1.insertar(n1.getElem(), 1);
                }
            }
        }

        return flag;
    }

    public Lista obtenerDescendientes(Object elem){
        //retorna una lista de los descendientes de un elem del arbol
        Lista ret = new Lista();
        obtenerDescendientesAux(this.raiz, ret, elem);
        return ret;
    }

    private boolean obtenerDescendientesAux(NodoArbol n, Lista l1, Object padre){
        boolean verif = false; //determina si el padre existe en el arbol

        if (n != null){  
            if (n.getElem().equals(padre)){ //caso base: encontro al padre
                verif = true; 
                preordenAux(n, l1); //mando el nodo padre
                l1.eliminar(1); //elimino raiz (padre)
            } else {
                verif = obtenerDescendientesAux(n.getIzquierdo(), l1, padre);
                if (!verif){
                    verif = obtenerDescendientesAux(n.getDerecho(), l1, padre);
                }
            }
        }

        return verif;
    }

    public Lista frontera(){
        Lista l1 = new Lista();
        obtenerHojas(l1, this.raiz);
        return l1;      
    }

    private void obtenerHojas(Lista list, NodoArbol n){
        if (n != null){
            if (n.getIzquierdo() == null && n.getDerecho() == null){ //si es hoja entonces inserto
                list.insertar(n.getElem(), list.longitud()+1);
            } 
            obtenerHojas(list, n.getIzquierdo()); 
            obtenerHojas(list, n.getDerecho());
            
        }
    }

    public boolean verificarPatron(Lista list){
        boolean verif = false;
        if (raiz == null){
            if (list.esVacia()){
                verif = true;
            }
        } else{
            verif = verificarPatronAux(this.raiz, list, 1);
        }
        return verif;
    }

    private boolean verificarPatronAux(NodoArbol n, Lista l1, int pos){
        boolean verif = true;
        
        if (n != null){
            if (n.getElem().equals(l1.recuperar(pos))){ //si es igual entonces verifico en sus hijos
                verif = verificarPatronAux(n.getIzquierdo(), l1, pos+1); //busco el camino en izq
                if (!verif){ //sino existe en izq busco en der
                    verif = verificarPatronAux(n.getDerecho(), l1, pos+1);
                }
            } else { //no es igual, retorna false
                verif = false;
            }   
        }
        return verif;
    }
    
    public ArbolBin clonarInvertido(){
        ArbolBin ret = new ArbolBin();
        ret.raiz = clonarInvertidoAux(this.raiz);
        return ret;
    }

    private NodoArbol clonarInvertidoAux(NodoArbol n){
        NodoArbol ret; 
        if (n == null){
            ret = null;
        } else {
            NodoArbol izq, der;
            izq = clonarInvertidoAux(n.getDerecho()); //mando invertido el hijo der a hijo izq de clone
            der = clonarInvertidoAux(n.getIzquierdo()); //mando invertido al hijo izq a hijo der de clone
            ret = new NodoArbol(n.getElem(), izq, der);
        }
        return ret;
    }

    public boolean equals(ArbolBin otro){
        //verifica si el arbol actual y el del parametro son iguales
        return equalsAux(this.raiz, otro.raiz);
    }

    private boolean equalsAux(NodoArbol n1, NodoArbol n2){
        boolean flag = true; 

        if (n1 != null && n2 != null){
            if (!n1.getElem().equals(n2.getElem())){ //si no son iguales
                flag = false;
            } else {
                flag = equalsAux(n1.getIzquierdo(), n2.getIzquierdo()); //veo si son iguales en subarbol izq
                if (flag){ //si son iguales verifico el subarbol der
                    flag = equalsAux(n1.getDerecho(), n2.getDerecho());
                }
            }
        } else {
            if (unoEsNull(n1, n2)){ 
                flag = false;
            }
        }
        return flag;
    }
    /*public boolean equals(ArbolBin otro){
        boolean verif = false;
        if (!unoEsNull(this.raiz, otro.raiz)){
            verif = equalsAux(this.raiz, otro.raiz);
        } 
          
        return verif;
    }

    private boolean equalsAux(NodoArbol n1, NodoArbol n2){
        boolean verif = true;

        if(n1 != null && n2 != null){
            if (!n1.getElem().equals(n2.getElem())){
                verif = false;
            } else {
                if (!unoEsNull(n1.getIzquierdo(), n2.getIzquierdo())){ //si uno se termino es false
                    verif = equalsAux(n1.getIzquierdo(), n2.getIzquierdo()); //verifico lado izq
                } else {
                    verif = false;
                }
                
                if (verif && !unoEsNull(n1.getDerecho(), n2.getDerecho())){ //si el lado izq es igual, verifico el der
                    verif = equalsAux(n1.getDerecho(), n2.getDerecho());
                } else {
                    verif = false;
                }             
            }
        }
        return verif;
    }*/

    private boolean unoEsNull(NodoArbol n1, NodoArbol n2){
        //verifica si un nodo es null y el otro no
        return (n1 == null && n2 != null) || (n1 != null && n2 == null);
    }

}
