package estructuras.lineales.testings;
import estructuras.lineales.dinamicas.*;
/**
 *
 * @author Guille
 */

public class testSimulacro {
    public static void main(String[] args) {
        Lista l1, l2;
        l1 = new Lista();
        l2 = new Lista();


        l1.insertar(1, 1);
        l1.insertar(2, 2);
        l1.insertar(2, 3);
        l1.insertar(0, 4);
        l1.insertar(1, 5);
        /*l1.insertar(2, 6);
        l1.insertar(2, 7);
        l1.insertar(0, 8);*/
 
        //l2 = l1.obtenerMultiplos(1);
        System.out.println("Lista original: "+l1.toString());
        l1.eliminarApariciones2(2);
        System.out.println("Lista Modif: "+l1.toString());
        
    }
}
