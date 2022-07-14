package estructuras.conjuntistas.testings;
import estructuras.conjuntistas.estaticas.*;;
/**
 *
 * @author Guille
 */

public class testHeap {
    public static void main(String[] args) {
        HeapMax a1, a2;
        a1 = new HeapMax();

        llenarArbol(a1);

        System.out.println("Arbol: ");
        System.out.println(a1.toString());
        a2 = a1.clone(); 
        a2.insertar(-1);
        a2.insertar(-2);
        System.out.println("Clon: ");
        a2.eliminarCima();
        System.out.println(a2.toString());
        
        

    }

    public static void llenarArbol(HeapMax a){
        int pos;

        for (pos = 9; pos > 0; pos--){
            a.insertar(pos);
        }
    }
}
