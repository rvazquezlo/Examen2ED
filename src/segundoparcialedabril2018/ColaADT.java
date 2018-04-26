package segundoparcialedabril2018;

public interface ColaADT <T> {
    public void agrega(T dato);
    public T quita();
    public boolean estaVacia();
    public T primero();
}
