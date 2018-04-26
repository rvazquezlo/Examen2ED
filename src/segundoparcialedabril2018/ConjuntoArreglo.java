package segundoparcialedabril2018;


import java.util.*; //Para la clase Random

public class ConjuntoArreglo <T> implements ConjuntoADT <T >  {
    private T colec[];
    private int cardinalidad;
    private final int CAPACIDAD = 20;	
    private static Random rand = new Random();

    public ConjuntoArreglo() {
    	colec = (T[])(new Object[CAPACIDAD]);
    	cardinalidad = 0;
    }
    
     public ConjuntoArreglo(int cantidad) {
    	colec = (T[])(new Object[cantidad]);
    	cardinalidad = 0;
    }
    
    @Override
    public String toString() {
    	StringBuilder cad = new StringBuilder();
    	Iterator<T> it = iterator();    	
    	while (it.hasNext())
            cad.append(it.next()).append(" ");    
    	return cad.toString();
    }
    
    public int getCardinalidad(){
    	return cardinalidad;
    }
        
    private void expande(){
    	T masGrande[] = (T[]) (new Object[colec.length * 2]);
    	int i;
    	
    	for (i= 0; i < colec.length; i++)
           masGrande[i] = colec[i];
    	colec = masGrande;
    }    
    
    @Override
    public boolean agrega(T elemento){
        boolean resultado;   	    

        if (!contiene(elemento)){
            if (cardinalidad == colec.length)
                expande();
            colec[cardinalidad] = elemento;
            cardinalidad++;
            resultado = true;
        }
        else
            resultado = false;
    	return resultado;
    }
    
    @Override
    public boolean estaVacio(){
    	return cardinalidad == 0;
    }
    
    @Override
    public T quitaRandom() {  
    	if (estaVacio())
            throw new EmptyCollectionException("Conjunto vacío");
    	int elegido = rand.nextInt(cardinalidad); //Se elige una posici�n
    	T resultado = colec[elegido]; 
    	colec[elegido] = colec[cardinalidad - 1]; //Se reemplaza el valor quitado
    	colec[cardinalidad -1] = null;   
    	cardinalidad--;    //Queda un elemento menos en el conjunto
    	return resultado;
    } 
    	
    @Override
    public boolean contiene(T elemento){
        boolean resp;

        resp = false;
        Iterator <T> it = iterator();
        T x;
        while (it.hasNext() && !resp){
            x = it.next();
            resp = elemento.equals(x);
        }    		
        return resp;
    }    
        
    @Override
    public Iterator<T> iterator() {
    	return new IteradorArreglo(colec, cardinalidad);
    }
    
    @Override
    public T quita (T elemento){
        T resultado = null;
        
        if (elemento != null && !this.estaVacio()){
            int i;
            
            i = 0;
            while (i < cardinalidad && !colec[i].equals(elemento))
                i++;
            if (i < cardinalidad){
                resultado = colec[i];
                cardinalidad--;
                colec[i] = colec[cardinalidad];
                colec[cardinalidad] = null;
            }
        }
        return resultado;
    }
}