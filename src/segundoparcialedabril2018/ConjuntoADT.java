package segundoparcialedabril2018;

import java.util.Iterator;

public interface ConjuntoADT <T> extends Iterable <T>{   
        public boolean agrega(T otro);       
	public T quitaRandom();        
        public T quita (T element);
	public boolean estaVacio();          
	public int getCardinalidad();        
	public boolean contiene(T elemento); 	
        public Iterator<T> iterator(); 
}


