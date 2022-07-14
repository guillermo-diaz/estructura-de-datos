package estructuras.conjuntistas.dinamicas;
import estructuras.lineales.dinamicas.*;
/**
 *
 * @author Guille
 */

public class ArbolBB {
    private NodoABB raiz;

    public ArbolBB(){
        //constructor
        this.raiz = null;
    }
    
    public boolean insertar(Comparable elem){
        //inserta un elem conservando el orden de el arbol 
        boolean verif = true;

        if (this.raiz == null){
            this.raiz = new NodoABB(elem, null, null);
        } else {
            verif = insertarAux(this.raiz, elem);
        }
        return verif;
    }

    public boolean insertarAux(NodoABB n, Comparable elem){
        //metodo aux que inserta un elem en un arbol no vacio, devuelve false si el elem ya existe
        boolean flag = true;


        if (elem.compareTo(n.getElem()) == 0){ 
            flag = false; //error el elem ya existe en el arbol
            
        } else if (elem.compareTo(n.getElem()) > 0) { //si el elem es mayor que la raiz voy al HD
            if (n.getDerecho() == null){ //si no tiene HD inserto
                n.setDerecho(new NodoABB(elem, null, null));
            } else { //paso recursivo con subarbol der
                flag = insertarAux(n.getDerecho(), elem);
            }
        } else { //si el elem es menor que la raiz voy al HI
            if (n.getIzquierdo() == null){ //si no tiene HI inserto
                n.setIzquierdo(new NodoABB(elem, null, null));
            } else { //Paso recursivo con subarbol izq
                flag = insertarAux(n.getIzquierdo(), elem);
            }
        }
        
        return flag;
    }

    public boolean eliminar(Comparable elem){
        //Recibe el elemento que se desea eliminar y se procede a removerlo del árbol
        return eliminarAux(this.raiz, elem, null);
    }

    /*private boolean eliminarAux(NodoABB n, Comparable elem, NodoABB padre){
        /* 
        elimina un elemento del arbol conservando el orden del mismo

        precondicion el arbol no debe estar vacio
        
        NodoABB izq, der;
        //hijos de nodo actual
        izq = n.getIzquierdo();
        der = n.getDerecho();
        Comparable aux = n.getElem(); //elem de nodo actual
        boolean flag = false;

        if (elem.compareTo(aux) == 0){ //elem encontrado
            flag = true; 
            
            //CASOS
            if (izq != null && der != null){ //si tiene 2 hijos
                Comparable candidato = menorEnSubarbolDer(der); //candidato a reemplazar a su padre
                eliminarAux(der, candidato, n); //elimino candidato 
                n.setElem(candidato); //reemplazo nodo actual con candidato
                
            } else if (izq == null && der == null){ //si es hoja
                if (padre == null){ //caso especial arbol de 1 elem
                    this.raiz = null;
                } else {
                    if (aux.compareTo(padre.getElem()) < 0){ //si el hijo es menor cambio el HI
                        padre.setIzquierdo(null);
                    } else { //si el hijo es mayor cambio el HD
                        padre.setDerecho(null);
                    }
                }
            } else { //si tiene solo 1 hijo
                if (padre != null){ 
                    if (der == null){ //si solo tiene HI
                        if (aux.compareTo(padre.getElem()) < 0){ // si aux es menor a su padre lo coloco como HI
                            padre.setIzquierdo(izq);
                        } else { //aux es mayor q su padre, lo coloco como HD
                            padre.setDerecho(izq);
                        }
                    } else { //si solo tiene HD
                        if (aux.compareTo(padre.getElem()) < 0){ 
                            padre.setIzquierdo(der);
                        } else {
                            padre.setDerecho(der);
                        }
                    }
                } else { //caso especial: si el elem es raiz lo reemplazo por su hijo
                    if (izq == null){
                        this.raiz = izq;
                    } else {
                        this.raiz = der;
                    }
                }
                
            }
        } else { //si no lo encontró, busco en sus hijos

            if(izq != null && elem.compareTo(aux) < 0) { //si es menor a la raiz voy al subarbol izq (si existe)
                flag = eliminarAux(n.getIzquierdo(), elem, n); 
            } else {
                flag = eliminarAux(n.getDerecho(), elem, n); //si es mayor a la raiz voy al subarbol der (si existe)
            }
        }

        return flag;
    }
    */

    private boolean eliminarAux(NodoABB n, Comparable elem, NodoABB padre){
        //elimina un elemento del arbol conservando el orden del mismo
        boolean flag = false;

        if (n != null){
            NodoABB izq, der;
            //hijos de nodo actual
            izq = n.getIzquierdo();
            der = n.getDerecho();
            Comparable aux = n.getElem(); //elem de nodo actual

            if (elem.compareTo(aux) == 0){ //elem encontrado
                flag = true; 
                
                //CASOS
                if (izq != null && der != null){ //Si el elem a eliminar tiene 2 hijos
                    Comparable candidato = menorEnSubarbolDer(der); //candidato a reemplazar a su padre
                    eliminarAux(der, candidato, n); //elimino candidato 
                    n.setElem(candidato); //reemplazo nodo actual con candidato
                    
                } else if (izq == null && der == null){ //Si el elem a eliminar es hoja
                    casoHoja(izq, der, padre, aux);

                } else { //Si el elem a eliminar tiene solo 1 hijo
                    caso1Hijo(izq, der, padre, aux);

                }

            } else if (elem.compareTo(aux) < 0) { //si es menor busco a la izq
                flag = eliminarAux(n.getIzquierdo(), elem, n); 
            } else { //si es mayor busco a la der
                flag = eliminarAux(n.getDerecho(), elem, n); 
            }
        }
        return flag;
    }

    private void casoHoja(NodoABB izq, NodoABB der, NodoABB padre, Comparable aux){
        //Caso del metodo eliminar: eliminar el nodo que es hoja. 
        if (padre == null){ //caso especial arbol de 1 elem
            this.raiz = null;
        } else {
            if (aux.compareTo(padre.getElem()) < 0){ //si el hijo es menor cambio el HI
                padre.setIzquierdo(null);
            } else { //si el hijo es mayor cambio el HD
                padre.setDerecho(null);
            }
        }
    }

    private void caso1Hijo(NodoABB izq, NodoABB der, NodoABB padre, Comparable aux){
        //Caso del metodo eliminar: eliminar el nodo que tiene solo 1 hijo
        if (padre != null){ 
            
            if (der == null){ //si solo tiene HI
                if (aux.compareTo(padre.getElem()) < 0){ // si aux es menor a su padre lo coloco como HI
                    padre.setIzquierdo(izq);
                } else { //aux es mayor q su padre, lo coloco como HD
                    padre.setDerecho(izq);
                }
            } else { //si solo tiene HD
                if (aux.compareTo(padre.getElem()) < 0){ 
                    padre.setIzquierdo(der);
                } else {
                    padre.setDerecho(der);
                }
            }
        } else { //caso especial: si el elem es raiz lo reemplazo por su hijo
            if (izq == null){
                this.raiz = izq;
            } else {
                this.raiz = der;
            }
        }
    }
    private Comparable menorEnSubarbolDer(NodoABB n){
        //metodo aux que busca el mayor elem de un subarbol no vacio
        Comparable ret; 
        if (n.getIzquierdo() == null){
            ret = n.getElem();
        } else {
            ret = menorEnSubarbolDer(n.getIzquierdo());
        }
        return ret;
    }

    private Comparable mayorEnSubarbolIzq(NodoABB n){
        //busca el menor el elem de un un subarbol no vacio
        Comparable ret;
        if (n.getDerecho() == null){
            ret = n.getElem();
        } else {
            ret = mayorEnSubarbolIzq(n.getDerecho());
        }
        return ret;
    }

    public boolean pertenece(Comparable elem){
        // Devuelve verdadero si el elem está en el árbol y falso en caso contrario
        return perteneceAux(this.raiz, elem);
    }

    private boolean perteneceAux(NodoABB n, Comparable elem){
        //metodo aux que verifica si un elemento esta en el arbol
        boolean flag; 

        if (n != null){
            if (elem.compareTo(n.getElem()) == 0){ //si es igual encontramos elem
                flag = true;
            } else if (elem.compareTo(n.getElem()) < 0) { //Si elem es menor a la raiz busco en subArbol izq
                flag = perteneceAux(n.getIzquierdo(), elem);
            } else { //Si elem es mayor a la raiz busco en subArbol der
                flag = perteneceAux(n.getDerecho(), elem);
                
            }
        } else {
            flag = false;
        }

        return flag;
    }

    public Comparable minimoElem(){
        //recorre la rama correspondiente y devuelve el elemento más pequeño almacenado en el árbol.
        return minimoAux(this.raiz);
    }

    private Comparable minimoAux(NodoABB n){
        //busca el menor elem en el arbol
        Comparable ret; 

        if (n != null){
            if (n.getIzquierdo() == null){ //si es hoja ret el elem 
                ret = n.getElem();
            } else {
                ret = minimoAux(n.getIzquierdo()); //busco en su HI 
            }
        } else {
            ret = null;
        }
        

        return ret;
    }

    public Comparable maximoElem(){ 
        // recorre la rama correspondiente y devuelve el elemento más pequeño almacenado en el árbol
        return maximoAux(this.raiz);
    }

    private Comparable maximoAux(NodoABB n){
        //busca el mayor elem en el arbol buscando en los subarboles der recursivamente
        Comparable ret; 

        if (n != null){ //si no es vacio
            if (n.getDerecho() == null){ //si es hoja ret el elem 
                ret = n.getElem();
            } else {
                ret = minimoAux(n.getDerecho()); //busco en su HI 
            }
        } else {
            ret = null;
        }
        
        return ret;
    }

    public Lista listar(){ 
        //devuelve una lista ordenada con los elementos que se encuentran almacenados en él.
        Lista ls = new Lista();
        listarAux(this.raiz, ls);
        return ls;
    }

    private void listarAux(NodoABB n, Lista ls){
        //inserta los elem del arbol de menor a mayor, recorre HD primero para mejorar einsertar en 1|
        if (n != null){ 
            listarAux(n.getDerecho(), ls);
            ls.insertar(n.getElem(), 1);
            listarAux(n.getIzquierdo(), ls);
        }
    }

    public Lista listarRango(Comparable ext1, Comparable ext2){
        //devuelve una lista ordenada con los elementos que se encuentran en el intervalo [elemMinimo, elemMaximo].
        Lista ls = new Lista();
        listarRangoAux(this.raiz, ext1, ext2, ls);
        return ls;
    }

    private void listarRangoAux(NodoABB n, Comparable ext1, Comparable ext2, Lista ls){
        //inserta los elem > ext1 y < ext2. Hago recorrido en inorden inverso (der, raiz, izq) para inserta en 1
        if (n != null){
            Comparable aux = n.getElem(); //elem de la raiz

            if (aux.compareTo(ext2) < 0){ //si aux < ext2 me muevo al subarbol der
                listarRangoAux(n.getDerecho(), ext1, ext2, ls); 
            } 
            if (aux.compareTo(ext1) >= 0 && aux.compareTo(ext2) <= 0) {
                ls.insertar(aux, 1);
            }
            if (aux.compareTo(ext1) > 0){ //si aux > ext2 me muevo al subarbol izq
                listarRangoAux(n.getIzquierdo(), ext1, ext2, ls);
            }   
        }
    }

    public boolean esVacio(){
        //verifica si el arbol actual esta vacio
        return this.raiz == null;
    }

    public void vaciar(){
        //vacia el arbol actual
        this.raiz = null;
    }

    public String toString(){
        //devuelve un String con la informacion del arbol actual
        String cad; 

        if (this.raiz == null){
            cad = "Arbol Vacio";
        } else {
            cad = toStringAux(this.raiz);
        }
        return cad;
    }

    private String toStringAux(NodoABB n){
        String cad = "";

        if (n != null){
            cad = cad +"("+ n.getElem() + ") ->  ";

            NodoABB izq, der;
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

    public ArbolBB clone(){
        //crea un clon del arbol actual
        ArbolBB clon = new ArbolBB();
        clon.raiz = cloneAux(this.raiz);
        return clon;
    }

    private NodoABB cloneAux(NodoABB n){
        //metodo aux que copia los nodos y enlaces del arbol
        NodoABB ret = null;

        if (n != null){
            ret = new NodoABB(n.getElem(), cloneAux(n.getIzquierdo()), cloneAux(n.getDerecho()));
        }

        return ret;
    }

    //parcial
    public void eliminarHojasEnRango(Comparable min, Comparable max){
        NodoABB n = this.raiz;

        if (n != null){
            if (esHoja(n)){ //caso especial: arbol con 1 elemento y esta en [min, max]
                if (n.getElem().compareTo(min) >= 0 && n.getElem().compareTo(max) <= 0){
                    this.raiz = null;
                }
            } else {
                eliminarEnRango(n, min, max);
            }
        }
        
        
    }

    private boolean esHoja(NodoABB n){
        return n.getIzquierdo() == null && n.getDerecho() == null;
    }

    private void eliminarEnRango(NodoABB n, Comparable min, Comparable max){
        NodoABB izq, der;
        izq = n.getIzquierdo();
        der = n.getDerecho();
        
        if (n.getElem().compareTo(min) > 0){
            if (izq != null){
                if (esHoja(izq) && izq.getElem().compareTo(min) >= 0 && izq.getElem().compareTo(max) <= 0){
                    n.setIzquierdo(null);
                } else {
                    eliminarEnRango(izq, min, max);
                }
            }
        }
        if (n.getElem().compareTo(max) < 0){
            if (der != null){
                if (esHoja(der) && der.getElem().compareTo(min) >= 0 && der.getElem().compareTo(max) <= 0){
                    n.setDerecho(null);
                } else {
                    eliminarEnRango(der, min, max);
                }
            }
        }
    }

    //ej de parcial
    public Comparable mejorCandidato(Comparable elem){
        Comparable ret;
        NodoABB n = obtenerNodo(this.raiz, elem);

        if (n != null){
            NodoABB izq, der;
            izq = n.getIzquierdo();
            der = n.getDerecho();
            if (izq != null && der != null){
                int elemNodo, d1, d2;
                Comparable menorDer, mayorIzq;
                elemNodo = (int) n.getElem();
                menorDer = menorEnSubarbolDer(der);
                mayorIzq = mayorEnSubarbolIzq(izq);
                d1 = Math.abs(elemNodo - (int) menorDer);
                d2 = Math.abs(elemNodo - (int) mayorIzq);
                if (d1 < d2){
                    ret = menorDer; 
                } else {
                    ret = mayorIzq;
                }
            } else {
                if (izq == null && der != null){
                    ret = menorEnSubarbolDer(der);
                } else if (izq != null && der == null){
                    ret = mayorEnSubarbolIzq(izq);
                } else {
                    ret = -1;
                }
            }
        } else {
            ret = 0;
        }
        return ret;
    }

    //ej de parcial
    public int diferenciaCandidatos(Comparable elem){
        int ret;
        NodoABB n = obtenerNodo(this.raiz, elem);

        if (n != null){
            if (n.getIzquierdo() != null && n.getDerecho() != null){
                int menorDer, mayorIzq;
                menorDer = (int) menorEnSubarbolDer(n.getDerecho());
                mayorIzq = (int) mayorEnSubarbolIzq(n.getIzquierdo());
                ret = menorDer - mayorIzq;
            } else {
                ret = -2;
            }
        } else {
            ret = -1;
        }
        return ret;
    }

    private NodoABB obtenerNodo(NodoABB n, Comparable elem){
        //busca el nodo que contiene a elem
        NodoABB ret = null;

        if (n != null){
            int comp = elem.compareTo(n.getElem());
            if (comp == 0){
                ret = n;
            } else if (comp < 0){
                ret = obtenerNodo(n.getIzquierdo(), elem);
            } else {
                ret = obtenerNodo(n.getDerecho(), elem);
            }
        }
        return ret;
    }
    
    //adicionales 
    public ArbolBB clonarParteInvertida(Comparable elem){
        //encuentra el subarbol de elem, lo clona y invierte a sus hijos
        ArbolBB clon = new ArbolBB();
        clon.raiz = clonarInvertidoAux(this.raiz, elem);
        return clon;
    }

    private NodoABB clonarInvertidoAux(NodoABB n, Comparable elem){
        NodoABB ret;

        if (n != null){
            Comparable aux = n.getElem();
            if (elem.compareTo(aux) == 0){
                ret = invertirSubarbol(n);
            } else if (elem.compareTo(aux) < 0){
                ret = clonarInvertidoAux(n.getIzquierdo(), elem);
            } else {
                ret = clonarInvertidoAux(n.getDerecho(), elem);
            }
        } else {
            ret = null;
        }
        return ret;
    }

    private NodoABB invertirSubarbol(NodoABB n){
        //invierte a los hijos de n 
        NodoABB ret;

        if (n != null){
            NodoABB izq = n.getIzquierdo(), der = n.getDerecho();
            ret = new NodoABB(n.getElem(), invertirSubarbol(der), invertirSubarbol(izq));
        } else {
            ret = null;
        }

        return ret;
    }

    public Lista listarMayorIgual(Comparable elem){
        Lista ls = new Lista();
        if (this.raiz != null){
            listarMayorIgualAux(this.raiz, elem, ls);
        }
        return ls;
    }
    private void listarMayorIgualAux(NodoABB n, Comparable elem, Lista ls){
        //precondicion: debe ser un arbol no vacio
            NodoABB izq, der;
            int comp = n.getElem().compareTo(elem);
            izq = n.getIzquierdo();
            der = n.getDerecho();

            if (comp > 0 && izq != null){ //solo voy a ir a la izq si elem < n 
                listarMayorIgualAux(izq, elem, ls);
            }
            if (comp >= 0){
                ls.insertar(elem, 1);
            }
            //siempre a la der
            if (der != null){
                listarMayorIgualAux(n, elem, ls);
            }
            
            
        }
    
}
