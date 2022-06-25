package controlador.tda.lista;
import modelo.Casa;

/**
 *
 * @author Thais Cartuche
 */
public class CasaController {
    ListaEnlazada<Casa> lista2 = new ListaEnlazada<>();
    Casa[] matriz2 = new Casa[10000];
    Casa casita = new Casa();

    public ListaEnlazada<Casa> getLista2() {
        return lista2;
    }

    public void setLista2(ListaEnlazada<Casa> lista2) {
        this.lista2 = lista2;
    }

    public Casa[] getMatriz2() {
        return matriz2;
    }

    public void setMatriz2(Casa[] matriz2) {
        this.matriz2 = matriz2;
    }

    public Casa getCasita() {
        return casita;
    }

    public void setCasita(Casa casita) {
        this.casita = casita;
    }
    
    public ListaEnlazada llenarPrecio(ListaEnlazada<Casa> listaPrecio){
        Casa precioCasa = new Casa();
        for (int i = 0; i < 10000; i++) {
            precioCasa.setPrecio((int)(Math.random()*50000+10000));
            precioCasa.setFecha((int)(Math.random()*43+1980));
            precioCasa = new Casa(precioCasa.getPrecio(), precioCasa.getFecha());
            listaPrecio.insertar(precioCasa);
        }
        return listaPrecio;
    }
}
