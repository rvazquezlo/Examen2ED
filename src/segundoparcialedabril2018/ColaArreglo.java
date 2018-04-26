package segundoparcialedabril2018;


public class ColaArreglo <T> implements ColaADT <T>{
    private T cola[];
    private int frente, fin;
    private final int MAX = 10;

    public ColaArreglo() {
        cola = (T[]) new Object[MAX];
        frente = -1;
        fin = -1;
    }

    public void agrega(T dato) {
        if (estaVacia())
            frente = 0;
        else
            if ((fin + 1) % cola.length  == frente)
                expandCapacity();
        fin = (fin + 1) % cola.length;
        cola[fin] = dato;
    }

    public boolean estaVacia() {
        return frente == -1;
    }

    public T primero() {
        if (!estaVacia())
            return cola[frente];
        else
            throw new EmptyCollectionException("Cola vacía");
    }

    public T quita() {       
        if (estaVacia())
            throw new EmptyCollectionException("Cola vacía");
        else {
            T resul = cola[frente];
            if (frente == fin){
                frente = -1;
                fin = -1;
            }
            else
                frente = (frente + 1) % cola.length;
            return resul;
        }      
    }

    private void expandCapacity(){
        T nuevo[] = (T[]) new Object[cola.length * 2];
        int i, j;

        j = 0;
        for (i= frente; i < cola.length; i++)
            nuevo[j++]= cola[i];
        for (i= 0; i < frente; i ++)
            nuevo[j++]= cola[i];
        frente = 0;
        fin = j-1;
        cola = nuevo;
    }
}
