package segundoparcialedabril2018;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteradorArreglo<T> implements Iterator <T> {	
    private int total;
    private int actual;
    private T contenido[];

    public IteradorArreglo(T contenido[], int total) {
    	this.contenido = contenido;
    	this.total = total;
    	actual = 0;
    }
    
    public boolean hasNext(){
    	return actual < total;
    }
    
    public T next() {      
    	if (!hasNext())
            throw new NoSuchElementException();	
    	else{
            T resul = contenido[actual];
            actual++;
            return resul;
        }
    }
        
    public void remove() {  
    	throw new UnsupportedOperationException();
    }    
}