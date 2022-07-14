package estructuras.jerarquicas.dinamicas;
import estructuras.lineales.dinamicas.*;
/**
 *
 * @author Guille
 */
public class ArbolGen {
    private NodoGen raiz;

    public ArbolGen(){
        //constructor
        this.raiz = null;
    }

    public boolean esVacio(){
        //verifica que el árbol esté vacio
        return this.raiz == null;
    }
    
    public boolean insertar(Object elem, Object padre){
        //inserta un nuevo elemento en el arbol
        boolean verif = true;

        if (this.raiz == null){ //caso especial insertar en arbol vacio
            this.raiz = new NodoGen(elem, null, null);
        } else {
            NodoGen aux = obtenerNodo(this.raiz, padre); //busco al padre
            if (aux == null){
                verif = false; //error, no encontro al padre
            } else {
                NodoGen nuevo = new NodoGen(elem, null, aux.getHijoIzquierdo()); //creo el nuevo nodo con el elem
                aux.setHijoIzquierdo(nuevo); //lo pongo como primer hijo 
            }
        }
        return verif;
    }

    private NodoGen obtenerNodo(NodoGen n, Object elem){
        //Metodo que busca la primera aparicion del nodo que contiene a elem
        NodoGen ret = null; 

        if (n != null){
            if (n.getElem() == elem){
                ret = n;
            } else {
                NodoGen hijo = n.getHijoIzquierdo(); //guardo el primer hijo
                boolean flag = false;

                while (!flag && hijo != null){ //si no ha sido encontrado y no se terminaron sus hijos
                    ret = obtenerNodo(hijo, elem);
                    if (ret != null){ //Nodo encontrado, salgo de la repetitiva
                        flag = true;
                    } else {
                        hijo = hijo.getHermanoDerecho(); //me muevo a su hermano
                    }
                } 
                
            }
        }
        return ret;
    } 

    public String toString(){
        //crea un string del arbol actual
        String ret; 
        if (this.raiz == null){
            ret = "Arbol Vacio";
        } else {
            ret = toStringAux(this.raiz);
        }
        return ret;
    }

    private String toStringAux(NodoGen n){ 
        //metodo aux toString, concatena el nodo actual y sus hijos, luego recorre recursivamente a sus hijos
        String ret = "";

        if (n != null){
            ret = ret + n.getElem()+ ")  ->  ";
            NodoGen hijo = n.getHijoIzquierdo(); //guardo hijo ext izq

            if (hijo == null){ //si no tiene primer hijo entonces es hoja
                ret = ret + "[Hoja]";
            } else {
                while (hijo != null){  //si el hijo existe
                    ret = ret + hijo.getElem(); //concateno a su hijo
                    hijo = hijo.getHermanoDerecho(); //me muevo a su otro hijo
                    if (hijo != null){
                        ret = ret + ", ";
                    }
                }
            }
            hijo = n.getHijoIzquierdo(); //vuelvo al primer hijo
             //comienza recorrido de los hijos de n recursivamente
            while (hijo != null){
                ret = ret + "\n"+toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return ret;
    }

    public Object padre(Object elem){ 
        //metodo que busca al padre de elem (si no existe retorna null)
        return padreAux(this.raiz, elem, null);
    }

    private Object padreAux(NodoGen n, Object hijo, Object padre){
        //metodo aux busca al elem (hijo) en preorden, y si lo encuentro retorna al padre
        Object ret = null;

        if (n != null){
            if (n.getElem().equals(hijo)){
                ret = padre;
            } else {
                NodoGen hijoAux = n.getHijoIzquierdo();
                boolean flag = false;

                while (!flag && hijoAux != null){
                    ret = padreAux(hijoAux, hijo, n.getElem());
                    if (ret != null){
                        flag = true;
                    } else {
                        hijoAux = hijoAux.getHermanoDerecho();
                    }
                }
            }
        }

        return ret;
    }

    public Lista listarPreorden(){
        //crea lista del arbol en recorrido preorden
        Lista list = new Lista();
        listarPreordenAux(list, this.raiz);
        return list;
    }

    private void listarPreordenAux(Lista ls, NodoGen n){
        if (n != null){
            ls.insertar(n.getElem(), ls.longitud()+1); //visito elem de n
            NodoGen hijo = n.getHijoIzquierdo(); //voy al primer hijo
            
            while (hijo != null){ //recorrido de los hijos de n recursivamente
                listarPreordenAux(ls, hijo);
                hijo = hijo.getHermanoDerecho();
            }
        } 
        
    }

    public Lista listarInorden(){
        //crea lista del arbol en recorrido inorden
        Lista list = new Lista();
        listarInordenAux(list, this.raiz);
        return list;
    }

    private void listarInordenAux(Lista ls, NodoGen n){
        if (n != null){
            //llamado recursivo con primer hijo de n
            NodoGen hijo = n.getHijoIzquierdo();
            if (hijo != null){
                listarInordenAux(ls, n.getHijoIzquierdo());
            }
            //visita al nodo de n
            ls.insertar(n.getElem(), ls.longitud()+1);

            //llamados recursivos con hijos de n
            if (hijo != null){
                hijo = hijo.getHermanoDerecho();
                while (hijo != null){
                    listarInordenAux(ls, hijo);
                    hijo = hijo.getHermanoDerecho();
                }
            }
            
        }
    }

    public Lista listarPosorden(){
        //crea lista del arbol en recorrido posorden
        Lista list = new Lista();
        listarPosordenAux(list, this.raiz);
        return list;
    }

    private void listarPosordenAux(Lista ls, NodoGen n){
        if (n != null){           
            NodoGen hijo = n.getHijoIzquierdo(); //voy al primer hijo
            
            if (hijo != null){
                while (hijo != null){ //recorrido de los hijos de n recursivamente
                    listarPosordenAux(ls, hijo);
                    hijo = hijo.getHermanoDerecho();
                }
                
            }
            ls.insertar(n.getElem(), ls.longitud()+1); //visito elem de n
            
        }
    }

    public Lista listarPorNiveles(){
        //crea lista del arbol en recorrido por niveles
        Lista list = new Lista();
        
        if (this.raiz != null){
            Cola q = new Cola(); //cola aux para guardar nodos
            q.poner(this.raiz); //pongo la raiz
            NodoGen nodoActual, hijo;
            int index = 1;

            while (!q.esVacia()){
                nodoActual = (NodoGen) q.obtenerFrente(); //obtengo el nodo del frente
                q.sacar();
                list.insertar(nodoActual.getElem(), index); //inserto

                hijo = nodoActual.getHijoIzquierdo(); 
                index++;


                while(hijo != null){ //pongo los hijos en la cola
                    q.poner(hijo);
                    hijo = hijo.getHermanoDerecho();
                }
            }
            
        }

        return list;
    }

    public int nivel(Object elem){
        // Devuelve el nivel de un elemento en el árbol. Si elem no existe en el árbol devuelve -1
        return nivelAux(this.raiz, elem, 0);
    }

    private int nivelAux(NodoGen n, Object elem, int lvl){
        //metodo que retorna el nivel de elem 
        int ret = -1;

        if (n != null){ 
            if (n.getElem().equals(elem)){
                ret = lvl; //elem encontrado, ret lvl
            } else {
                NodoGen hijo = n.getHijoIzquierdo(); //primer hijo
                boolean flag = false;
                
                while (hijo != null && !flag){ //recorro hijos hasta encontrar el elem
                    ret = nivelAux(hijo, elem, lvl + 1);
                    if (ret != -1){  //si es distinto entonces ya se encontro y dejo de recorrer
                        flag = true;
                    } else {
                        hijo = hijo.getHermanoDerecho(); //me muevo a su hermano
                    }
                }
            }
        }
        return ret;
    }

    public Lista ancestros(Object elem){
        //Devuelve una lista con los ancestros del elemento ingresado

        Lista list = new Lista();
        ancestrosAux(this.raiz, elem, list);
        return list;
    } 

    private boolean ancestrosAux(NodoGen n, Object padre, Lista ls){
        //inserta los ancestros de 'padre' en la lista, primero verifica si existe
        boolean ret = false;

        if (n != null){
            if (padre.equals(n.getElem())){ //si encuentro el elem ret true
                ret = true;
            } else { //si no es igual busco en sus hijos
                NodoGen hijo = n.getHijoIzquierdo();
                
                while (hijo != null && !ret){ //mientras hijo exista y elem no sea encontrado entonces
                    ret = ancestrosAux(hijo, padre, ls); 
                    if (ret){ //si fue encontrado inserto
                        ls.insertar(n.getElem(), 1);
                    } else {
                        hijo = hijo.getHermanoDerecho(); //si no es encontrado me muevo a su hermano
                    }
                }
            }
        }
        return ret;
    }  

    public int altura(){
        //devuelve la altura del arbol
        return alturaAux(this.raiz);
    }

    private int alturaAux(NodoGen n){
        int ret = -1; 
 
        if (n != null){ //si no es vacio
            NodoGen hijo = n.getHijoIzquierdo();
            int aux; 

            while (hijo != null){ //mientras tenga hijos, calculo sus alturas
                aux = alturaAux(hijo);
                if (ret < aux){ //busco el mas grande
                    ret = aux;
                }
                hijo = hijo.getHermanoDerecho(); //me muevo a su hermano
            }
            ret++; //sumo la altura del nodo     
        }

        return ret;
    }

    public boolean pertenece(Object elem){
        // Devuelve verdadero si el elem pasado por parámetro está en el árbol, y falso en caso contrario
        return perteneceAux(this.raiz, elem);
    }

    private boolean perteneceAux(NodoGen n, Object elem){
        boolean verif = false;

        if (n != null){
            if (n.getElem().equals(elem)){
                verif = true; //elem encontrado
            } else {
                NodoGen hijo = n.getHijoIzquierdo(); //primer hijo

                while (!verif && hijo != null){ //paso recursivo con sus hijos hasta encontrarlo
                    verif = perteneceAux(hijo, elem);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return verif;
    }
   
    public void vaciar(){
        this.raiz = null;
    }
    
    public ArbolGen clone(){
        //crea un clon del arbol actual
        ArbolGen clon = new ArbolGen();
        clon.raiz = cloneAux(this.raiz);  //actualizo raiz
        return clon;
    }

    private NodoGen cloneAux(NodoGen n){
        NodoGen ret;

        if (n != null){
            NodoGen hijoExt, bro;
            
            hijoExt = cloneAux(n.getHijoIzquierdo()); //obtengo hijo extremo izquierdo
            bro = cloneAux(n.getHermanoDerecho()); //obtengo el hermano derecho

            ret = new NodoGen(n.getElem(), hijoExt, bro); //creo el nuevo nodo
        } else {
            ret = null;
        }

        return ret;
    }

    //Ejercicio TPO OBLIGATORIO
    public boolean sonFrontera(Lista unaLista) {
        /*Metodo que verifica si los elementos de la lista ingresada son hojas en el arbol actual
        Precondición: unaLista no puede contener elementos repetidos*/
        boolean ret;

        Lista lis = unaLista.clone(); //lo clono para no afectar lista original
    
        if (lis.esVacia()){
            if (this.raiz == null){ 
                ret = true; /* tomamos como verdadero un caso donde la lista que entra esta vacia
                 y el arbol esta vacio*/
            } else {
                ret = false; 
                /* tomamos como falso un caso donde la lista que entra esta vacia
                 y el arbol tiene al menos un elemento*/
            }
        } else {
            sonFronteraAux(lis, this.raiz);
            ret = lis.esVacia(); //si la lista se vacio es que todas son hojas
        }

        return ret;
    }
    
        private void sonFronteraAux(Lista lis, NodoGen n) {
            //Método que recorre cada hoja, lo busca en la lista y lo elimina en caso de que exista
    
            if (n != null) {
                NodoGen hijo = n.getHijoIzquierdo();
                if (hijo == null) {//Si n es hoja (no tiene HI)
                    int pos = lis.localizar(n.getElem());//Buscar posición en la lista
                    if (pos > 0) {
                        lis.eliminar(pos); //Eliminarlo de la lista
                    }
                } else {
                    /*Paso recursivo son sus hijos, si son hojas, verificar en la lista y eliminarlos*/
                    while (hijo != null && !lis.esVacia()) { 
                        sonFronteraAux(lis, hijo);
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }

    //ejercicios adicionales

    public boolean verificarCamino(Lista ls){
        boolean flag; 

        if (this.raiz != null){
            flag = verificarCaminoAux(this.raiz, ls, 1);
        } else {
            flag = ls.esVacia();
        }

        return flag;
    }

    private boolean verificarCaminoAux(NodoGen n, Lista ls, int pos){
        boolean flag = true;
        NodoGen hijo = n.getHijoIzquierdo();

        if (pos <= ls.longitud()){
            if (!n.getElem().equals(ls.recuperar(pos))){
                flag = false;
            } else {
                pos++;
                if (pos <= ls.longitud() && hijo == null){
                    flag = false;
                } else {
                    while (hijo != null){
                        flag = verificarCaminoAux(n, ls, pos);
                        if (!flag){
                            hijo = hijo.getHermanoDerecho();
                        }  
                    }
                }  
            }   
        }

        return flag;
    }

public Lista listarEntreNiveles(int n1, int n2){
    Lista ls = new Lista();

    if (this.raiz != null){
        listarEntreNivelesAux(this.raiz, ls, 0, n1, n2);
    }
    return ls;
}

    private void listarEntreNivelesAux(NodoGen n, Lista ls, int nivActual, int n1, int n2){
        //precondicion: el arbol no debe estar vacio
        NodoGen hijo = n.getHijoIzquierdo();

        if (nivActual >= n1 && nivActual <= n2){
            ls.insertar(n.getElem(), ls.longitud()+1);
        }
        nivActual++;
        if (nivActual <= n2){
            while (hijo != null){
                listarEntreNivelesAux(hijo, ls, nivActual, n1, n2);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }


    public Lista frontera(){
        //devuelve la lista de hojas del arbol actual
        Lista ls = new Lista();
        fronteraAux(this.raiz, ls);
        return ls;
    }

    private void fronteraAux(NodoGen n, Lista ls){
        if (n != null){
            NodoGen hijo = n.getHijoIzquierdo();
            if (hijo == null){ //si es hoja lo inserto
                ls.insertar(n.getElem(), 1);
            } else { //si no es hoja busco en sus hijos
                while (hijo != null){
                    fronteraAux(hijo, ls);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    //arreglar verificarPatron
    public boolean verificarPatron(Lista ls){
        boolean ret = false; 

        if (this.raiz == null){
            if (ls.esVacia()){
                ret = true;
            }
        } else {
            ret = verificarPatronAux(this.raiz, ls, 1);
        }
        return ret;
    }

    private boolean verificarPatronAux(NodoGen n, Lista ls, int pos){
        boolean verif = true; 

        if (n != null){
            if (n.getElem().equals(ls.recuperar(pos))){
                NodoGen hijo = n.getHijoIzquierdo();
                int posAux = pos + 1;
                boolean flag = false;

                while (!flag && hijo != null){
                    verif = verificarPatronAux(hijo, ls, posAux);
                    if (!verif){
                        hijo = hijo.getHermanoDerecho();
                    } else {
                        flag = true;
                    }
                    
                }
            } else {
                verif = false;
            }
        }
        return verif;
    }




}