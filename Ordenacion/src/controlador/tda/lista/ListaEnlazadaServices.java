package controlador.tda.lista;

/**
 * @author Thais Cartuche
 */

public class ListaEnlazadaServices <E> {
    
    private ListaEnlazada<E> lista;
    
    public ListaEnlazada<E> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<E> lista) {
        this.lista = lista;
    }
    
    public ListaEnlazadaServices() {
        this.lista = new ListaEnlazada<>();
    }
    
    public Boolean insertarAlInicio(E dato) {       
            lista.insertarCabecera(dato);
            return true;
       
    }
   
    public Integer getSize() {
        return lista.getSize();
    }
    
    public void limpiarLista() {
        lista.vaciar();        
    }
    
}
