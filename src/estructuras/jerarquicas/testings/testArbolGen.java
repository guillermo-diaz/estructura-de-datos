package estructuras.jerarquicas.testings;

import estructuras.jerarquicas.dinamicas.ArbolGen;
import estructuras.lineales.dinamicas.*;
public class testArbolGen {
    public static void main(String[] args) {
        ArbolGen a1, a2; 
        a1 = new ArbolGen();
        Lista l1 = new Lista();

        a1.insertar(1, 0);
        a1.insertar(2, 1);
        a1.insertar(3, 2);
        a1.insertar(4, 1);
        a1.insertar(5, 1);
        a1.insertar(6, 1);
        a1.insertar(7, 3);
        a1.insertar(8, 0);
        

        System.out.println("");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Arbol Gen: ");
        System.out.println(a1.toString());
        l1 = a1.listarPreorden();
        System.out.println("-Listados: \nPreorden: "+l1.toString());
        l1 = a1.listarInorden();
        System.out.println("Inorden: "+l1.toString());
        l1 = a1.listarPosorden();
        System.out.println("Posorden: "+l1.toString());
        l1 = a1.listarPorNiveles();
        System.out.println("Niveles: "+l1.toString());
        System.out.println("");
        System.out.println("Pertenece: "+a1.pertenece(5));
        System.out.println("Nivel: "+a1.nivel(1));
        System.out.println("Altura: "+a1.altura());
        l1 = a1.ancestros(9);
        System.out.println("Ancestros de 9: "+l1.toString());
        a2 = a1.clone();
        a1.vaciar();
        
        System.out.println("");
        System.out.println("Original");
        System.out.println(a1.toString());
    
        System.out.println("");
        System.out.println("Clon: ");       
        System.out.println(a2.toString());

        System.out.println("Verificar camino");
        l1.vaciar();
        llenarLista(l1, "123");
        System.out.println("Lista "+l1.toString());
        System.out.println("Esta en el arbol: "+a2.verificarCamino(l1));
    }
    public static void llenarLista(Lista ls, String cad){
        int i;
        
        for (i = 0; i < cad.length(); i++){
            ls.insertar(cad.charAt(i), ls.longitud()+1);
        }
    }
}
