package estructuras.lineales.testings;
import estructuras.lineales.dinamicas.*; 
/**
 *
 * @author Guille
 */
public class PruebaLista {
    public static void main(String[] args) {
        Lista l1; 
        //menu();

        l1 = new Lista();
        l1.insertar(1, 1);
        l1.insertar(2, 2);
        l1.insertar(3, 3);
        l1.insertar(4, 4);
        l1.insertar(5, 5);
        l1.insertar(6, 6);
        /*l1.insertar(2, 7);
        l1.insertar(0, 8);
        l1.insertar(2, 9);
        l1.insertar(2, 10);
        l1.insertar(1, 11);*/
        System.out.println("Lista original: "+l1.toString());
        l1.invertir();
        System.out.println("Lista Modif: "+l1.toString());
    } 
    public static void elegirOpcion(char opcion){
        Lista l1, l2, listaNueva; 
        l1 = new Lista();
        l2 = new Lista();

        l1.insertar(1, 1);
        l1.insertar(2, 1);
        l1.insertar(3, 1);

        l2.insertar(4, 1);
        l2.insertar(5, 1);
        l2.insertar(6, 1);

        switch(opcion){
            case 'a': 
                System.out.println("Opcion elegida: ");
                System.out.println("a) Concatenar 2 listas y crear una nueva");

                System.out.println("Lista 1: "+l1.toString());
                System.out.println("Lista 2: "+l2.toString());
                listaNueva = concatenarLista(l1, l2);
                System.out.println("Lista Nueva: "+ listaNueva.toString());
                break;
            case 'b': 
                l1.vaciar();
                l1.insertar(1, 1);
                l1.insertar(2, 2);
                l1.insertar(2, 3);
                l1.insertar(0, 4);
                l1.insertar(1, 5);
                l1.insertar(2, 6);
                l1.insertar(2, 7);
                l1.insertar(0, 8);
               l1.insertar(2, 9);
                l1.insertar(2, 10);
                l1.insertar(1, 11);    

                System.out.println("Opcion elegida: ");
                System.out.println("b) Comprobar si una lista tiene el formato cadena0cadena0cadenaInvertida");

                System.out.println("Lista 1: "+l1.toString());
                if (mismoFormato(l1)){
                    System.out.println("Tiene mismo formato");
                } else {
                    System.out.println("No tiene mismo formato");
                }
                break;
            case 'c': 
                System.out.println("Opcion elegida: ");
                System.out.println("c) Invertir una lista");
                System.out.println("Lista 1: "+l1.toString());
                listaNueva = obtenerListaInvertida(l1);
                System.out.println("Lista Nueva: "+listaNueva.toString());
                break;
            default: 
                System.out.println("Error"); 
                break;
        }
    }
    public static boolean mismoFormato(Lista l1){
        boolean verif = true; 

        if (l1.esVacia()){ //error lista vacia
            verif = false;
        } else {
            int elem, longitud, pos; 
            elem = (int) l1.recuperar(1); //guardo el primer elem
            longitud = l1.longitud();
            pos = 2; //Tiene al menos un elemento, por lo que me paro en la pos 2

            //cola y pila aux para guardar la cad (1er tramo) y su inversa
            Cola c1 = new Cola();  
            Pila p1 = new Pila();

            while (elem != 0 & pos <= longitud){ //buscamos la cadena
                c1.poner(elem);
                p1.apilar(elem);
                elem =  (int) l1.recuperar(pos);
                pos++;
            }

            if (pos <= longitud){ 
                elem = (int) l1.recuperar(pos); //guardo el elemento después del 0
                Cola c1Aux = c1.clone(); //creo un clon aux para ir vaciando la cola sin afectar la original
                pos++; //para pasar al sig elemento

                while (verif & elem != 0 & !c1Aux.esVacia() & pos <= longitud){ //comparo parte intermedia para ver si es igual al 1er tramo
                    if ((int) c1Aux.obtenerFrente() != elem){ 
                        verif = false;
                    } else {
                        elem = (int) l1.recuperar(pos);
                        c1Aux.sacar(); 
                    }
                    pos++;
                }
 
                if (pos > longitud){ //error se termino la lista
                    verif = false;
                } else {
                    while (verif & pos <= longitud && !p1.esVacia()){ //comparo si el ultimo tramo es la inversa del primero
                        if (l1.recuperar(pos) != p1.obtenerTope()){
                            verif = false;
                        } else {
                            pos++;
                            p1.desapilar();
                        }
                    }
                }  
            } else {
                verif = false; //error se termino la lista
            }
        }
        return verif;
    }

    public static Lista obtenerListaInvertida(Lista l1){
        Lista inversa = new Lista();
        int pos, aux = 1;

        for (pos = l1.longitud(); pos > 0; pos--){
            inversa.insertar(l1.recuperar(pos), aux);
            aux++;
        }

        return inversa;
    }
    public static Lista concatenarLista(Lista l1, Lista l2){
        Lista ret = l1.clone(); //creo un clon de la primera lista
        int j, aux = ret.longitud()+1;
        
        for (j = 1; j <= l2.longitud(); j++){ //concateno los elem de la 2da lista
            ret.insertar(l2.recuperar(j), aux);
            aux++;
        }
        return ret;
    }
    public static void menu(){
        System.out.println("Elija una opción: ");
        System.out.println("a) Concatenar 2 listas y crear una nueva");
        System.out.println("b) Comprobar si una lista tiene el formato cadena0cadena0cadenaInvertida");
        System.out.println("c) Invertir una lista");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("");
        
        elegirOpcion('a');
    }
}
