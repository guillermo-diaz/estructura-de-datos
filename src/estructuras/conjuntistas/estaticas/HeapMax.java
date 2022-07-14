package estructuras.conjuntistas.estaticas;
/**
 *
 * @author Guille
 */

public class HeapMax {
    private static final int TAM = 20;
    private Comparable[] heap;
    private int ultimo;

    public HeapMax (){
        //constructor
        heap = new Comparable[TAM];
        ultimo = 0;
    }

    public boolean insertar(Comparable elem){
        // recibe un elemento y lo inserta en el árbol
        boolean flag; 

        if (ultimo + 1 < TAM){
            flag = true;
            this.ultimo++;
            heap[ultimo] = elem;
            hacerSubir(ultimo);
        } else {
            flag = false; 
        }

        return flag;
    }

    public void hacerSubir(int posHijo){
        //Metodo aux que hace subir el elem de la posHijo a donde corresponde
        int posPadre;
        boolean flag = false;
        Comparable aux = this.heap[posHijo];

        while (!flag){
            posPadre = posHijo / 2;
            if (posPadre > 0){ //si existe el padre
                if (aux.compareTo(this.heap[posPadre]) > 0){ //si el hijo es mayor al padre los intercambio
                    this.heap[posHijo] = this.heap[posPadre];
                    this.heap[posPadre] = aux;
                    posHijo = posPadre;
                } else {
                    flag = true;
                }
            } else { //si no existe el padre, ya llegamos a la raiz
                flag = true;
            }
        }


    }

    public boolean eliminarCima(){
        //elimina el elemento de la raiz
        boolean exito;
        
        if (this.ultimo != 0){ //si no es vacio
            exito = true;
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;
            hacerBajar(1);

        } else {
            exito = false; //error arbol vacio
        }

        return exito;
    }

    private void hacerBajar(int posPadre) {
        //Metodo aux que hace bajar el elem de la posPadre a donde corresponde
        int posHijo = posPadre * 2;
        Comparable temp = this.heap[posPadre];
        boolean flag = false;

        while (!flag && posHijo <= this.ultimo) { //si tiene al menos un hijo y mientras no este bien ubicado
            if (posHijo < this.ultimo) { //si es menor tiene un HD
                if (this.heap[posHijo+ 1].compareTo(this.heap[posHijo]) > 0) { //si el HD < HI
                    posHijo++;  //me muevo al HD
                }
            }
            
            if (this.heap[posHijo].compareTo(temp) > 0) { 
                // el hijo es menor que el padre, los intercambia
                this.heap[posPadre] = this.heap[posHijo];
                this.heap[posHijo] = temp;
                posPadre = posHijo;
                posHijo = posPadre * 2;
            } else {
                flag = true;
            }
            
        }
    }

    public boolean esVacio(){ 
        //verifica si el heap esta vacio
        return this.ultimo == 0;
    }
    
    public Comparable recuperarCima(){
        //devuelve el elemento que está en la raíz
        Comparable ret; 

        if (ultimo != 0){
            ret = this.heap[1];
        } else {
            ret = null;
        }

        return ret;
    }

    public HeapMax clone(){
        //crea un clon del heap actual
        HeapMax clon = new HeapMax();
        int pos;
        clon.ultimo = this.ultimo; //clono ref a ult pos

        for (pos = 1; pos <= this.ultimo;pos++){
            clon.heap[pos] = this.heap[pos];
        }

        return clon;
    }

    public String toString(){
        String cad = "";

        if (ultimo == 0){
            cad = "Arbol vacio";
        } else {
            int pos, izq, der;

            for (pos = 1; pos <= this.ultimo; pos++) { 
                cad = cad + "("+this.heap[pos].toString() + ") ->  "; //visito a la raiz

                izq = pos * 2; //pos del HI
                der = pos * 2 + 1; // pos del HD

                if (izq <= this.ultimo){ //si no es nulo imprimo el elem izq
                    cad = cad + "HI: " + this.heap[izq].toString() + "    ";
                } else {
                    cad = cad + "HI: -    ";
                }
                if (der <= this.ultimo){ //si no es nulo imprimo el elem der
                    cad = cad + "HD: " + this.heap[der].toString() + "\n";
                } else {
                    cad = cad + "HD: -\n";
                }
            }
        }

        return cad;
    }
}
