package estructuras.conjuntistas.testings;

import estructuras.conjuntistas.dinamicas.*;

public class testArbolAVL {
    public static void main(String[] args) {
        ArbolAVL a = new ArbolAVL();
        int[] arr = {15, 9, 20, 6, 14, 17, 35, 3};
        
        System.out.println("Probar rotacion simple a der");
        System.out.println();
        a.insertar(10);
        a.insertar(9);
        a.insertar(5);
        System.out.println(a.toString());
        a.insertar(8);
        System.out.println(a.toString());
        a.vaciar();

        System.out.println("Probar rotacion simple a Izq");
        System.out.println();
        a.insertar(1);
        a.insertar(2);
        a.insertar(3);
        System.out.println(a.toString());
        a.insertar(5);
        System.out.println(a.toString());
        a.vaciar();

        System.out.println("Probar rotacion doble DER-IZQ");
        a.insertar(10);
        a.insertar(5);
        a.insertar(15);
        a.insertar(12);
        a.insertar(17);
        System.out.println(a.toString());
        a.insertar(13);
        System.out.println(a.toString());
        a.vaciar();
        
        System.out.println("Probar rotacion doble IZQ-DER");
        a.insertar(12);
        a.insertar(5);
        a.insertar(23);
        a.insertar(3);
        a.insertar(8);
        System.out.println(a.toString());
        a.insertar(10);
        System.out.println(a.toString());
        a.vaciar();

        System.out.println("Eliminar hoja");
        llenarArbol(a, arr);
        System.out.println("Original: \n"+a.toString());
        a.eliminar(14);
        System.out.println(a.toString());
        a.vaciar();

        System.out.println("Eliminar nodo con 1 hijo");
        llenarArbol(a, arr);
        System.out.println("Original: \n"+a.toString());
        a.eliminar(6);
        System.out.println(a.toString());
        a.vaciar();

        System.out.println("Eliminar nodo con 2 hijos");
        llenarArbol(a, arr);
        System.out.println("Original: \n"+a.toString());
        a.eliminar(15);
        System.out.println(a.toString());

        
    }

    public static int balance(NodoAVL n){
        int izq = -1, der = -1;
    
        if (n.getIzquierdo() != null){
            izq = n.getIzquierdo().getAltura();
        } 
        if (n.getDerecho() != null){
            der = n.getDerecho().getAltura();
        }
        return izq - der;
    }
    public static void llenarArbol(ArbolAVL a, int[] arr){
        int j, limite = arr.length;
        for (j = 0; j < limite; j++){
            a.insertar(arr[j]);
        }
    }
    
}
