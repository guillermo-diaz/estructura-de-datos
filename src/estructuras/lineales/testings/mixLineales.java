package estructuras.lineales.testings;
import estructuras.lineales.dinamicas.*;
public class mixLineales {
    public static void main(String[] args) {
        Cola c1, c2;
        c1 = new Cola();
        c1.poner('A');
        c1.poner('B');
        c1.poner('$');
        c1.poner('C');
        c1.poner('$');
        c1.poner('D');
        c1.poner('E');
        c1.poner('F');

        System.out.println("Original: "+c1.toString());
        c2 = generarOtraCola(c1);
        System.out.println("Modif: "+c2.toString());

    }
    public static Cola generarOtraCola(Cola c1){
        Cola ret = new Cola();

        if (!c1.esVacia()){
            Cola aux, concat; 
            char elem;
            concat = new Cola(); //para guardar secuencia de letras
            aux = c1.clone(); //clon aux para vaciar la cola
            
            while (!aux.esVacia()){
                elem = (char) aux.obtenerFrente(); //existe al menos un elemento
                while (elem != '$' & !aux.esVacia()){ //busco la secuencia de letras y las guardo
                    concat.poner(elem);
                    aux.sacar(); 
                    if (!aux.esVacia()){
                        elem = (char) aux.obtenerFrente();
                    }
                }
                concat = concatenarInv(concat); //concateno la secuencia de letras con su inversa
                aux.sacar(); //saco el $ (si existe)

                while (!concat.esVacia()){ //inserto los elementos en la cola a retornar
                    ret.poner(concat.obtenerFrente());
                    concat.sacar();
                }
                
                if (!aux.esVacia()){ //si no rebasó entonces:
                    ret.poner('$');
                }
            }
        }          
        return ret;
    }
    public static Cola concatenarInv(Cola c1){
        //Concatena una cola con su inversa
        Cola ret = new Cola();
        if (!c1.esVacia()){
            Cola aux;
            Pila p1 = new Pila();
            ret = c1.clone(); 
            aux = c1.clone(); //guardo en aux para luego vaciarla
            
            while (!aux.esVacia()){ //copiar la inversa en una pila
                p1.apilar(aux.obtenerFrente());
                aux.sacar();
            }

            while (!p1.esVacia()){ //concateno la inversa
                ret.poner(p1.obtenerTope());
                p1.desapilar();
            }
        }   
        return ret;
    }
}
