package estructuras.lineales.testings;
import estructuras.lineales.dinamicas.*;
/**
 *
 * @author Guille
 */
public class testCadenas {
    public static void main(String[] args) {
        Cola c1, c2;
        c1 = new Cola();
        llenarCola(c1, "{[[()]]}");
        System.out.println(c1.toString());
        if (verificarBalanceo(c1)){
            System.out.println("Está balanceado");
        } else {
            System.out.println("No esta balanceado");
        }

        Lista l1 = new Lista();
        l1.insertar(1, 1);
        l1.insertar(2, 2);
        l1.insertar(3, 3);
        l1.insertar(4, 4);
        l1.insertar(5, 5);
        l1.insertar(6, 6);
        l1.insertar(7, 7);;
        System.out.println("Original "+l1.toString());
        l1.agregarElem(0, 2);
        System.out.println("Modif: "+l1.toString());
        /*System.out.println(c1.toString());
        c2 = generar(c1);
        System.out.println("Nueva Cola: "+c2.toString());*/

    }
    public static boolean verificarBalanceo(Cola q){
        boolean verif;
        if (q.esVacia()){
            verif = false;
        } else {
            verif = true;
            Cola apertura = new Cola(); //cola donde guardare los simbolos de apertura ("{, [, (")
            Pila cierre = new Pila(); //pila donde guardare los simbolos de cierre ("}, ], )")
            
            buscarSignos(apertura, cierre, q);

            while (verif & !apertura.esVacia() & !cierre.esVacia()){
                if (!equalsSignos((char) apertura.obtenerFrente(), (char) cierre.obtenerTope())){
                    verif = false;
                } else {
                    apertura.sacar();
                    cierre.desapilar();

                    if ((apertura.esVacia() & !cierre.esVacia()) || (!apertura.esVacia() & cierre.esVacia())){ /*
                        Caso especial: una de las dos estructuras se vacia y la otra no, 
                        eso quiere decir que no estan balanceadas*/
                        verif = false;
                    }
                }
            }
        }
        return verif;
    }
    public static void buscarSignos(Cola apertura, Pila cierre, Cola c1){
        //Modifica las referencias agregando a la cola los signos de apertura y en la pila los de cierre
        Cola aux = c1.clone();
        char elem;

        while(!aux.esVacia()){
            elem = (char) aux.obtenerFrente();
            if (esDeApertura(elem)){
                apertura.poner(elem);
            }
            if (esDeCierre(elem)){
                cierre.apilar(elem);
            }
            aux.sacar();
        }
    }
    public static boolean equalsSignos(char ini, char fin){
        boolean verif;
        switch(ini){
            case '{': 
                verif = fin == '}'; break;
            case '[': 
                verif = fin == ']'; break;
            case '(': 
                verif = fin == ')'; break;
            default: 
                verif = false; break;
        }
        return verif;
    }
    public static boolean esDeApertura(char c){
        return c == '{' || c == '[' || c == '(';
    }
    public static boolean esDeCierre(char c){
        return c == '}' || c == ']' || c == ')';
    }
    public static Cola generar(Cola c1){
    /*recibe por parámetro  c1 que tiene el siguiente formato: a1#a2#a3#….#an, 
    donde cada ai en una sucesión de letras mayúsculas y apartir de c1 debe generar como salida otra Cola
    de la forma: a1a1´a1#a2a2´a2#….#anan´an donde cada ai´ es la secuencia de letras mayúsculas ai pero invertida
    
    Precondicion: c1 tiene que tener el formato del enunciado*/
        Cola ret = new Cola();

        if (!c1.esVacia()){
            Cola seq, clon;
            Pila inv = new Pila();
            char elem;
            seq = new Cola();
            clon = c1.clone();

            while (!clon.esVacia()){
                elem = (char) clon.obtenerFrente();
                while (elem != '#' && !clon.esVacia()){
                    seq.poner(elem);
                    inv.apilar(elem);
                    clon.sacar();
                    if (!clon.esVacia()){
                        elem = (char) clon.obtenerFrente();
                    }
                }
                seq = concatenarColaConPila(seq, inv);
                clon.sacar(); //saco el '#' (si existe)
                ponerEnCola(ret, seq);
                if (!clon.esVacia()){
                    ret.poner('#');
                }
            }
        }
        return ret;
    }
    public static Cola concatenarColaConPila(Cola aux, Pila p1){
        Cola ret = aux.clone();
        
        while (!p1.esVacia()){
            ret.poner(p1.obtenerTope());
            p1.desapilar();
        }
        while (!aux.esVacia()){
            ret.poner(aux.obtenerFrente());
            aux.sacar();
        }
        return ret;
    }
    public static void ponerEnCola(Cola c1, Cola aux){
        //pongo los eltos de aux en c1. Aux al finalizar queda vacio
        while (!aux.esVacia()){
            c1.poner(aux.obtenerFrente());
            aux.sacar();
        }
    }
    public static void llenarCola(Cola aux, String cad){
        int j, limite = cad.length();
        for (j = 0; j < limite; j++){
            aux.poner(cad.charAt(j));
        }
    }
}
