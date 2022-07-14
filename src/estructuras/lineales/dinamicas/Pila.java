package estructuras.lineales.dinamicas;
/**
 *
 * @author Guille
 */
public class Pila {
    private Nodo tope;
    
    //constructor
    public Pila(){
        this.tope = null;
    }   
    
    //apilamos un nuevo elemento a la pila
    public boolean apilar(Object nuevoElem){
        // crea un nuevo nodo detras del antiguo tope
        Nodo nuevo = new Nodo (nuevoElem, this.tope);
        //actualiza el tope para que apunte al nodo nuevo
        this.tope = nuevo; 
        
        //nunca hay error de pila, por lo que:
        return true;
    }
    
    //desapilamos el tope de la pila
    public boolean desapilar(){
        boolean verif;
        
        if(this.esVacia()){ 
            verif = false;
        } else {
            //pone al enlace como nuevo tope
            Nodo nuevo = this.tope.getEnlace();
            this.tope = nuevo;     
            verif = true;
        }
        return verif;
    }
    
    //verifica si la pila esta vacia
    public boolean esVacia(){
        return this.tope == null;
    }  
    
    // obtiene el elemento tope de la pila
    public Object obtenerTope(){
        Object ret;
        if (this.tope == null){ 
            ret = null;
        } else {
            ret = this.tope.getElem(); 
        }
        return ret; 
    }
    
    //vacia la pila
    public void vaciar(){
        this.tope = null;
    }  
    
    //retorna una cadena con todos los elementos de la pila
    public String toString(){
        String cad = "";
        
        if (this.tope == null){
            cad = "Pila Vacia";
        } else {
            Nodo aux = this.tope;
            cad = "[";
            while (aux != null){
                cad = cad + aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null){
                    cad = cad + ", ";
                }
            }
            cad = cad + "]";
        }
        return cad;
    } 
    
   //crea un clon de la pila actual
   public Pila clone(){
       Pila clon = new Pila();
       //clon.cloneRecursivo(clon, this.tope); 
       clon.tope = cloneR(this.tope);
       return clon; 
   }
   
   private Nodo cloneR(Nodo n){
        Nodo ret;

        if (n != null){
            ret = new Nodo(n.getElem(), cloneR(n.getEnlace()));
        } else {
            ret = null;
        }
        return ret;
   }
   /*//no retorna nada debido a que está modificando una referencia
   private void cloneRecursivo(Pila clon, Nodo link){
       if (link != null){
           cloneRecursivo(clon, link.getEnlace()); // (ida) paso el enlace en el paso recursivo
           Nodo n = new Nodo(link.getElem(), clon.tope); //(vuelta) creo nuevo nodo
           clon.tope = n; //lo asigno al tope del clon
       }
   }*/


   
}

