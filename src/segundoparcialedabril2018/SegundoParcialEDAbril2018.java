
package segundoparcialedabril2018;

import java.util.Iterator;


public class SegundoParcialEDAbril2018 {
    public static<T> String toStringCola(ColaADT<T> cola){
        ColaADT<T> auxiliar;
        StringBuilder sb;
        String cadena;
        
        try{
            sb = new StringBuilder();
            auxiliar = new ColaArreglo();
            while(!cola.estaVacia()){
                sb.append(cola.primero() + " ");
                auxiliar.agrega(cola.quita());
            }
            cadena = sb.toString();
            while(!auxiliar.estaVacia())
                cola.agrega(auxiliar.quita());  
            
        }catch(NullPointerException e){
           cadena = null; 
        }
        return cadena;
    }
    
    /**
     * 
     * @param <T>
     * @param cola
     * @param dato
     * @param n: la posicion empezando en 1. n es menor o igual a la cantidad
     *  de elementos almacenados (se asume que esta segunda condicion siempre se
     *  cumple)
     * @return: 
     *   1) true: si el dato se pudo mover
     *   2) false: si cola es null, si dato no esta en cola, o si n es menor
     *      a uno.
     */
    public static<T> boolean cambiaPosicionDe(ColaADT<T> cola, T dato, int n){
        ColaADT<T> auxiliar;
        T encontrado;
        boolean cambio;
        int contador;
        
        cambio = false;
        if(n > 0){
            try{
                auxiliar = new ColaArreglo();
                encontrado = null;
                while(!cola.estaVacia()){
                    if(cola.primero().equals(dato)){
                        encontrado = cola.quita();
                    }
                    else{
                        auxiliar.agrega(cola.quita());
                    }
                }//fin while
                if(encontrado != null){ //dato si estaba en cola
                    cambio = true;
                    contador = 0;
                    while(!auxiliar.estaVacia()){
                        if(contador == n - 1){
                            cola.agrega(encontrado);
                        }
                        cola.agrega(auxiliar.quita());
                        contador++;
                    }
                    if(contador == n - 1)
                        cola.agrega(encontrado);
                }
                else{//dato no esta en cola
                    while(!auxiliar.estaVacia())
                        cola.agrega(auxiliar.quita());    
                }
            }catch(NullPointerException e){

            }
        }
        return cambio;
    }
    
    private static<T> boolean esSubConjuntoDe(ConjuntoADT<T> uno, ConjuntoADT<T> dos, Iterator<T> iterador){
        if(!iterador.hasNext())
            return true;
        if(dos.contiene(iterador.next())){
            return esSubConjuntoDe(uno, dos, iterador);   
        }
        return false;
    }
    
    /**
     * 
     * @param <T>
     * @param uno: supuesto subconjunto de dos
     * @param dos: conjunto que supuestamente contiene a uno
     * @return:
     *  1) true: si todos los elementos de uno estan en dos o si uno esta 
     *     vacio. 
     *  2) false: si uno y/o dos son null o si todos los elementos de uno no 
     *     estan contenidos en dos.
     */
    public static<T> boolean esSubConjuntoDe(ConjuntoADT<T> uno, ConjuntoADT<T> dos){
        boolean subConjunto;
        Iterator<T> iterador;
        
        if(uno != null && dos != null && uno.getCardinalidad() <= dos.getCardinalidad()){
            iterador = uno.iterator();
            subConjunto = esSubConjuntoDe(uno, dos, iterador); 
        }
        else
            subConjunto = false;
        return subConjunto;  
    }
    
    /**
     * Metodo auxiliar de calculaRaizCuadrada. 
     * @param numero: numero positivo del que se va a aproximar la raiz
     * @param error: maximo error aceptado 
     *        Si se da un valor negativo, se usa su valor absoluto. 
     * @param inferior: valor minimo de 0 
     * @param superior: valor maximo de numero
     * @return: 
     * 1) aproximacion cuando se encontro un valor que cumpla con el minimo de 
     *    error
     * 2) Llamada recursiva si el valor que se encontro tiene un error mayor
     *    al valor minimo aceptado de error.
     */
    private static double calculaRaizCuadrada(double numero, double error, double inferior, double superior){
        double aproximacion, aproximacionAlCuadrado;
        
        aproximacion = (inferior + superior) / 2;
        aproximacionAlCuadrado = Math.pow(aproximacion, 2);
        if(Math.abs(aproximacionAlCuadrado - numero) < error)
            return aproximacion;
        if(aproximacionAlCuadrado < numero)
            return calculaRaizCuadrada(numero, error, aproximacion, superior);
        return calculaRaizCuadrada(numero, error, inferior, aproximacion);  
    }
    
    /**
     * Metodo que calcula la raiz cuadrada de numero utilizando el metodo de 
     * biseccion con un error maximo de valor del parametro error.
     * @param numero: numero positivo del que se va a aproximar la raiz
     * @param error: maximo error aceptado 
     *        Si se da un valor negativo, se usa su valor absoluto.  
     * @return: 
     * 1) Aproximacion de la raiz cuadrada de numero con error maximo de 
     *    valor error. 
     * 2) Si se pone numero negativo, se regresa -1.
     * 3) Si se pone error = 0, se regresa -1. 
     * @see calculaRaizCuadrada
     */
    public static double calculaRaizCuadrada(double numero, double error){
        double aproximacion;
        
        if(numero < 0 || error == 0)
            aproximacion = - 1;
        else{
            if(error < 0)
                error = Math.abs(error);
            aproximacion = calculaRaizCuadrada(numero, error, 0, numero);
        }
        return aproximacion;
    }
    
    public static void main(String[] args) {
        ColaADT<String> cola1;
        ConjuntoADT<Integer> conjunto1, conjunto2;
        int i;
        double numero, error;
        
        //Problema 1)
        cola1 = new ColaArreglo();
        cola1.agrega("uno");
        cola1.agrega("dos");
        cola1.agrega("tres");
        cola1.agrega("cuatro");
        System.out.println("Problema 1:");
        
        //1) imprime: true
        //   tres uno dos cuatro
        System.out.println(cambiaPosicionDe(cola1, "tres", 1));
        System.out.println(toStringCola(cola1));
        
        //2) imprime: true
        //   uno dos  tres cuatro
        System.out.println(cambiaPosicionDe(cola1, "tres", 3));
        System.out.println(toStringCola(cola1));
        
        //3) imprime: true
        //   uno cuatro dos  tres 
        System.out.println(cambiaPosicionDe(cola1, "cuatro", 2));
        System.out.println(toStringCola(cola1));
        
        //3) imprime: true
        //   uno cuatro dos  tres 
        System.out.println(cambiaPosicionDe(cola1, "cuatro", 4));
        System.out.println(toStringCola(cola1));
        
        //4) imprime: false
        //   uno cuatro dos  tres
        System.out.println(cambiaPosicionDe(cola1, "cinco", 2));
        System.out.println(toStringCola(cola1));
        
        //5) imprime: false
        //   null
        System.out.println(cambiaPosicionDe(null, "cinco", 2));
        System.out.println(toStringCola(null));
        
        //6) imprime: false
        //   uno cuatro dos  tres
        System.out.println(cambiaPosicionDe(cola1, "cinco", 0));
        System.out.println(toStringCola(cola1));
        
        //Problema 2)
        conjunto1 = new ConjuntoArreglo();
        conjunto2 = new ConjuntoArreglo();
        for(i = 0; i < 20; i++)
            conjunto2.agrega(i);  
        for(i = 10; i > 0; i--)
            conjunto1.agrega(i);
        System.out.println("\nProblema 2:");
        
        //7) imprime: true
        System.out.println(esSubConjuntoDe(conjunto1, conjunto2));
        
        //8) imprime: false
        System.out.println(esSubConjuntoDe(null, conjunto2));
        
        //9) imprime: false
        System.out.println(esSubConjuntoDe(conjunto2, conjunto1));
        
        //10) imprime: true
        System.out.println(esSubConjuntoDe(conjunto2, conjunto2));
        
        //11) imprime: false
        conjunto2 = new ConjuntoArreglo();
        for(i = 20; i < 30; i++)
            conjunto1.agrega(i);
        System.out.println(esSubConjuntoDe(conjunto1, conjunto2));
         
        //12) imprime: true
        conjunto1 = new ConjuntoArreglo();
        System.out.println(esSubConjuntoDe(conjunto1, conjunto2));
        
        //Problema 3)
        System.out.println("\nProblema 3:");
        System.out.println("1) Raiz verdadera: " + Math.sqrt(2) + "\n   Raiz"
                + " aproximada con .0001 de error: " + 
                calculaRaizCuadrada(2, .0001));
        System.out.println("2) Raiz verdadera: " + Math.sqrt(9) + "\n   Raiz"
                + " aproximada con .0001 de error: " + 
                calculaRaizCuadrada(9, .0001));
        System.out.println("3) Raiz verdadera: " + Math.sqrt(5) + "\n   Raiz"
                + " aproximada con .000001 de error: " + 
                calculaRaizCuadrada(5, .000001));
        System.out.println("4) Regresa - 1: \n   Raiz aproximada con 0 de error"
                + ": " + calculaRaizCuadrada(5, 0));
        System.out.println("5) Regresa - 1: \n   Raiz aproximada de un numero "
                + "< 0: " + calculaRaizCuadrada(-5, 0));
        
    }
}
