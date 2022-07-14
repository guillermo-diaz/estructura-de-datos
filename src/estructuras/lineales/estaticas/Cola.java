package estructuras.lineales.estaticas;

public class Cola {
    private Object[] array;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;
    
    public Cola(){
        this.array = new Object[TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }
    public boolean poner(Object elem){
        boolean verif;
        
        if (this.estaLlena()){
            verif = false;
        } else {
            this.array[this.fin] = elem;
            this.fin = (this.fin + 1) % this.TAMANIO;
            verif = true;
        }
        return verif;
    }
    private boolean estaLlena(){
        return (this.fin + 1) % this.TAMANIO == this.frente;
    }
    public boolean sacar(){
        boolean verif;
        
        if (esVacia()){
            verif = false;
        } else {
            this.array[this.frente] = null;
            this.frente = (this.frente + 1) % this.TAMANIO;
            verif = true;
        }
        return verif;
    }
    public boolean esVacia(){
        return this.fin == this.frente;
    }
    public Object obtenerFrente(){
        Object elem;
        
        if (this.esVacia()){
            elem = null;
        } else {
            elem = this.array[this.frente];
        }
        return elem;
    }
/*  public void vaciar(){
        this.array = new Object[this.TAMANIO]; 
        this.frente = 0;
        this.fin = 0;
    }
*/ 
    public void vaciar(){
        while (!this.esVacia()) {
            this.array[this.frente] = null;
            this.frente = (this.frente + 1) % this.TAMANIO;
        } 
    }
    public String toString(){
        String cad;
        if (this.esVacia()){
            cad = "Cola vacia";
        } else {
            int aux = this.frente;
            cad = "[";
            while (aux != this.fin){
                cad = cad + this.array[aux].toString();
                aux = (aux + 1) % this.TAMANIO;
                if (aux != this.fin){ //para dejar de poner comas
                    cad = cad + ", "; 
                }
            }
            cad = cad + "]";
        }
        return cad;
    }
    public Cola clone(){
        Cola clon = new Cola();
        
        if (!this.esVacia()){
            int aux = this.frente; //Uso un Aux para no modificar el frente del clon
            clon.frente = this.frente; //le paso el frente y el fin al clon
            clon.fin = this.fin;
            while (aux != this.fin){
                clon.array[aux] = this.array[aux];
                aux = (aux+1) % this.TAMANIO; //nos movemos de forma circular
            }
        }
        return clon;
    } 
}