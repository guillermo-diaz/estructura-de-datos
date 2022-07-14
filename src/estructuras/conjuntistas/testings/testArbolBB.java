package estructuras.conjuntistas.testings;
import estructuras.conjuntistas.dinamicas.*;
/**
 *
 * @author Guille
 */

public class testArbolBB {
    public static void main(String[] args) {
        ArbolBB a = new ArbolBB();
        /*int[] arr = {10, 8, 12};
        llenarArbol2(a, arr);

        System.out.println("Arbol: ");
        System.out.println(a.toString());
        /*System.out.println("Pertenece 5? "+a.pertenece(5));
        System.out.println("Elem min "+a.minimoElem());
        System.out.println("Elem max "+a.maximoElem());
        System.out.println("Lista menor a mayor "+a.listar().toString());
        System.out.println("Lista rango [2, 7]: "+a.listarRango(4, 9).toString());
        a.eliminar(64);
        System.out.println(a.toString());

        System.out.println("Arbol sin hojas en rango");
        a.eliminarHojasEnRango(10, 20);
        System.out.println(a.toString()); */
        a.insertar(20);
        a.insertar(14);
        a.insertar(44);
        a.insertar(22);
        a.insertar(50);

        a.insertar(18);
        a.insertar(8);
        a.insertar(7);
        a.insertar(16);
        a.insertar(19);


        System.out.println(a.toString());
        //   System.out.println(a.listar());
        a.eliminarHojasEnRango(10, 30);
        System.out.println(a.toString());
        System.out.println("Diferencia candidatos "+a.diferenciaCandidatos(14));
        System.out.println("Mejor candidato "+a.mejorCandidato(8));

    }
    public static void llenarArbol(ArbolBB a){
        for (int i = 9; i >= 0; i--){
            a.insertar(i);
        }
    }
    public static void llenarArbol2(ArbolBB a, int[] arr){
        int j, limite = arr.length;
        for (j = 0; j < limite; j++){
            a.insertar(arr[j]);
        }
    }
}
