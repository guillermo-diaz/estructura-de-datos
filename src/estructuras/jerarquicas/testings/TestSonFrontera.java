package estructuras.jerarquicas.testings;
import estructuras.jerarquicas.dinamicas.*;
import estructuras.lineales.dinamicas.*;
    /* 
        Estructuras De Datos
        Trabajo Práctico Obligatorio 
        Integrantes: 
        Ulises Corrales FAI-3350
        Guillermo Diaz FAI-3197
    */

public class TestSonFrontera {

    static String sOk = "\u001B[32m OK! \u001B[0m", sErr = " \u001B[31m ERROR \u001B[0m";
    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        Lista lis = new Lista();
        
        System.out.println("****************************************************************");
        System.out.println("*                 Testing Metodo sonFrontera                   *");
        System.out.println("****************************************************************");
        System.out.println("");


        llenarArbol(arbol); 
        System.out.println("                       Árbol 1.1 a Testear: ");
        System.out.println(" "
        + "\n                                A                   "
        + "\n                                |                   "
        + "\n                D-------------- C -----------B      "
        + "\n                |                            |      "
        + "\n            +---+---+                    +---+---+  "
        + "\n            |   |   |                    |   |   |  "
        + "\n            K   I   H                    G   F   E  "
        + "\n                    |                    |          "
        + "\n                    J                 ---+---       "
        + "\n                                      |     |       "
        + "\n                                      S     S       "
        );
        System.out.println();
        System.out.println("Informacion: \n"+arbol.toString());
        System.out.println();
        
        System.out.println("LOTE DE PRUEBAS:");
        System.out.println("- Lista con dos elementos hermanos del arbol:");
        llenarLista(lis, "EF");                        
        System.out.println("    Lista: "+lis.toString());
        System.out.println("    Se espera OK: "+((arbol.sonFrontera(lis)) ? sOk : sErr));
        System.out.println("----------------------------------------------------------------");

        System.out.println("- Lista con dos elementos hermanos invertidos del arbol:");
        lis.vaciar();
        llenarLista(lis, "FE");                          
        System.out.println("    Lista: "+lis.toString());
        System.out.println("    Se espera OK: "+((arbol.sonFrontera(lis)) ? sOk : sErr));
        System.out.println("----------------------------------------------------------------");
        
        System.out.println("- Arbol con elementos y una lista vacia");
        lis.vaciar();
        System.out.println("    Lista: "+lis.toString());
        System.out.println("    Se espera ERROR: "+((arbol.sonFrontera(lis)) ? sOk : sErr));
        System.out.println("----------------------------------------------------------------");
        
        System.out.println("- Arbol vacio, lista con elementos:");
        arbol.vaciar();
        lis.vaciar();
        llenarLista(lis, "SK");
        System.out.println("    Arbol: "+arbol.toString());
        System.out.println("    Lista: "+lis.toString());
        System.out.println("    Se espera ERROR: "+((arbol.sonFrontera(lis)) ? sOk : sErr));
        System.out.println("----------------------------------------------------------------");

        System.out.println("- Arbol vacio y lista vacia");
        lis.vaciar();
        System.out.println("    Arbol: "+arbol.toString());
        System.out.println("    Lista: "+lis.toString());
        System.out.println("    Se espera OK: "+((arbol.sonFrontera(lis)) ? sOk : sErr));
        System.out.println("----------------------------------------------------------------");

        System.out.println("- Arbol de la figura 1.1 y lista con un solo elemento que pertenece:");
        llenarArbol(arbol);
        llenarLista(lis, "S");
        System.out.println("    Lista: "+lis.toString());
        System.out.println("    Se espera OK: "+((arbol.sonFrontera(lis)) ? sOk : sErr));
        System.out.println("----------------------------------------------------------------");
        
        System.out.println("- Un solo elemento que no pertenece");
        lis.vaciar();
        llenarLista(lis, "Z");
        System.out.println("    Lista: "+lis.toString());
        System.out.println("    Se espera ERROR: "+((arbol.sonFrontera(lis)) ? sOk : sErr));
        System.out.println("----------------------------------------------------------------");

        System.out.println("- Lista con elementos y solo uno de ellos pertenece");
        lis.vaciar();
        llenarLista(lis, "ZWYS");
        System.out.println("    Lista: "+lis.toString());
        System.out.println("    Se espera ERROR: "+((arbol.sonFrontera(lis)) ? sOk : sErr));
        System.out.println("----------------------------------------------------------------");

        System.out.println("- Lista que no tiene todas las hojas del arbol");
        lis.vaciar();
        llenarLista(lis, "KIJ");
        System.out.println("    Lista: "+lis.toString());
        System.out.println("    Se espera OK: "+((arbol.sonFrontera(lis)) ? sOk : sErr));
        System.out.println("----------------------------------------------------------------");

        System.out.println("- Un elemento que es interno:");
        lis.vaciar();
        llenarLista(lis, "G");
        System.out.println("    Lista: "+lis.toString());
        System.out.println("    Se espera ERROR: "+((arbol.sonFrontera(lis)) ? sOk : sErr));
        System.out.println("----------------------------------------------------------------");  
        
        System.out.println("- Elemento que es raíz del árbol:");
        lis.vaciar();
        llenarLista(lis, "A");
        System.out.println("    Lista: "+lis.toString());        
        System.out.println("    Se espera ERROR: "+((arbol.sonFrontera(lis)) ? sOk : sErr));
        System.out.println("----------------------------------------------------------------");
        llenarArbol(arbol);
        
        System.out.println("- Arbol de la figura 1.1 y hoja que está en medio de hermanos con hijos:");
        lis.vaciar();
        llenarLista(lis, "C");
        System.out.println("    Lista: "+lis.toString());       
        System.out.println("    Se espera OK: "+((arbol.sonFrontera(lis)) ? sOk : sErr));
        System.out.println("----------------------------------------------------------------");
        
        System.out.println("- Una lista con una hoja y un arbol con 1 sola raiz:");
        lis.vaciar();
        arbol.vaciar();
        arbol.insertar('A', 0); 
        llenarLista(lis, "A");
        System.out.println("    Arbol: "+arbol.toString());
        System.out.println("    Lista: "+lis.toString());
        System.out.println("    Se espera OK: "+((arbol.sonFrontera(lis)) ? sOk : sErr)); 
        System.out.println("----------------------------------------------------------------");       
        
        arbol.vaciar();
        llenarArbol(arbol);                
        
        System.out.println("- Una lista con todos las hojas del arbol 1.1:");
        lis.vaciar();
        llenarLista(lis, "EFSJCIK");
        System.out.println("    Lista: "+lis.toString());        
        System.out.println("    Se espera OK: "+((arbol.sonFrontera(lis)) ? sOk : sErr));
        System.out.println("----------------------------------------------------------------");  
        
    }
    public static void llenarArbol(ArbolGen arb){
        //Método para llenar el arbol con elementos de prueba
        arb.insertar('A', 0); //RAIZ
        arb.insertar('B', 'A');
        arb.insertar('C', 'A');//Hoja que posee hermanos con hijos
        arb.insertar('D', 'A');
        arb.insertar('E', 'B');//Hijo Izquierdo sin hijos
        arb.insertar('F', 'B');        
        arb.insertar('G', 'B'); 
        arb.insertar('H', 'D'); 
        arb.insertar('I', 'D'); 
        arb.insertar('S', 'G'); 
        arb.insertar('S', 'G'); //Elemento repetido
        arb.insertar('J', 'H'); //Hijo único
        arb.insertar('K', 'D'); //Hermano final sin hijo
        
    }
    public static void llenarLista(Lista lis, String cad){
        //metodo auxiliar para llenar una lista con los caracteres de un string
        int pos, limite = cad.length();

        for (pos = 0; pos < limite; pos++){
            lis.insertar(cad.charAt(pos), 1);
        }
    }
    
}

