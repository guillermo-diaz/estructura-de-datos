package estructuras.jerarquicas.testings;
import estructuras.jerarquicas.dinamicas.ArbolBin;
import estructuras.lineales.dinamicas.*;

public class testArbolBin {
    public static void main(String[] args) {
        ArbolBin a1, a2;
        a1 = new ArbolBin();
        Lista l1, l2;

        a1.insertar(1, 1, 'I');
        a1.insertar(2, 1, 'I');
        a1.insertar(3, 1, 'D');
        a1.insertar(4, 2, 'I');
        a1.insertar(5, 3, 'I');
        a1.insertar(6, 3, 'D');
        a1.insertar(7, 5, 'I');
        a1.insertar(8, 5, 'D');


        System.out.println("Arbol: ");
        System.out.println(a1.toString());
        a2 = a1.clonarInvertido();
        System.out.println("Arbol invertido: ");
        System.out.println(a2.toString());

        l1 = a1.listarPorNiveles();
        System.out.println("Recorrido por niveles "+l1.toString());
        System.out.println("Padre de 5: "+a1.padre(5));
        System.out.println("Altura: "+a1.altura());
        System.out.println("Nivel de : "+a1.nivel(1));
        a2 = a1.clone();
        System.out.println("Clon: \n"+a2.toString());
        l1 = a1.frontera();
        System.out.println("Frontera: "+l1.toString());
        l1.vaciar();
        l1.insertar(1, l1.longitud()+1);
        l1.insertar(3, l1.longitud()+1);
        l1.insertar(6, l1.longitud()+1);

        if (a1.verificarPatron(l1)){
            System.out.println("Mismo patron que la lista: "+l1.toString());
        } else {
            System.out.println("No existe el camino");
        }
        a2 = a1.clone();
        a2.insertar(9, 7, 'D');
        if (a1.equals(a2)){
            System.out.println("Son iguales");
        } else {
            System.out.println("No son iguales");
        }
        l1 = a1.obtenerAncestros(8);
        System.out.println("Ancestros: "+l1.toString());
        l1 = a1.obtenerDescendientes(3);
        System.out.println("Descendientes: "+l1.toString());


    }
}
