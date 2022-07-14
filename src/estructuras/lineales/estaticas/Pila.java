package estructuras.lineales.estaticas;

public class Pila {
    private Object[] arreglo;
    private int tope;
    private static final int TAMANIO = 10;

    //constructor
    public Pila(){
        this.arreglo = new Object[TAMANIO];
        this.tope = -1;
    }

    //apilamos un nuevo elemento a la pila
    public boolean apilar(Object nuevoElem){
        boolean verif;

        if (this.tope+1 < TAMANIO){
            //incrementa el tope y coloca el elemento en su lugar
            tope++;
            arreglo[tope] = nuevoElem;
            verif = true;
        } else {
            //error: pila llena
            verif = false;
        }
        return verif;
    }
    
    //desapilamos el tope de la pila
    public boolean desapilar(){
        boolean verif;

        if (this.tope != -1){
            arreglo[tope] = null;
            tope--;
            verif = true; 
        } else {
            verif = false;
        }

        return verif;
    }
    
    // obtiene el elemento tope de la pila
    public Object obtenerTope(){
        Object elem;

        if (this.tope >= 0){
            elem = arreglo[tope];
        } else {
            elem = null;
        }

        return elem;
    }
    
    //verifica si la pila esta vacia
    public boolean esVacia(){
        return this.tope == -1;
    }
    
    //vacia la pila
    public void vaciar(){
        this.arreglo = new Object[TAMANIO]; //creo un nuevo arreglo y lo reemplazo
        this.tope = -1;
    }
    //crea un clon de la pila actual
    public Pila clone(){
        Pila clon = new Pila();
        int i = 0;
        
        while(i <= this.tope){
            clon.arreglo[i] = this.arreglo[i];
            i++;
        }
        clon.tope = this.tope;
        return clon;
    }
    
    //retorna una cadena con todos los elementos de la pila
    public String toString(){
        String cad = "";

        if (this.tope == -1){
            cad = "Pila vacia";
        } else {
            int j;
            cad = "[";
            for (j = this.tope; j >= 0; j--){
                cad = cad + this.arreglo[j].toString();
                if (j != 0){
                    cad = cad + ", ";
                }  
            }
            cad = cad + "]";
        }
        
        return cad;
    } 
}

