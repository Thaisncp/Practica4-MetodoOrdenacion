package controlador.tda.lista;
import controlador.utiles.TipoOrdenacion;
import controlador.utiles.Utilidades;
import static controlador.utiles.Utilidades.getMethod;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * @author Thais Cartuche
 */

public class ListaEnlazada<E> {

    private NodoLista<E> cabecera;

    private Integer size;
    public NodoLista<E> getCabecera() {
        return cabecera;
    }

    public void setCabecera(NodoLista<E> cabecera) {
        this.cabecera = cabecera;
    }

    /**
     * Constructor de la clase se inicializa la lista en null y el tamanio en 0
     */
    public ListaEnlazada() {
        cabecera = null;
        size = 0;
    }

    /**
     * Permite ver si la lista esta vacia
     *
     * @return Boolean true si esta vacia, false si esta llena
     */
    public Boolean estaVacia() {
        return cabecera == null;
    }

    public void insertar(E dato) {
        NodoLista<E> nuevo = new NodoLista<>(dato, null);
        if (estaVacia()) {
            cabecera = nuevo;
        } else {
            NodoLista<E> aux = cabecera;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
        size++;
    }

    public void insertarCabecera(E dato) {
        if (estaVacia()) {
            insertar(dato);
        } else {
            NodoLista<E> nuevo = new NodoLista<>(dato, null);

            nuevo.setSiguiente(cabecera);
            cabecera = nuevo;
            size++;
        }
    }
        
    public String imprimir() {
        String lista = "";
        NodoLista<E> aux = cabecera;
        for (int i = 0; i < getSize(); i++) {
            lista += aux.getDato().toString() + "\n";
            aux = aux.getSiguiente();
        }
        return lista;
    }

    public Integer getSize() {
        return size;
    }
   
    public void vaciar() {
        cabecera = null;
        size = 0;
    }

     public E[] toArray() {
        Class<E> clazz = null;
        E[] matriz = null;
        if (this.size > 0) {
            matriz = (E[]) java.lang.reflect.Array.newInstance(cabecera.getDato().getClass(), this.size);
            NodoLista<E> aux = cabecera;
            for (int i = 0; i < this.size; i++) {
                matriz[i] = aux.getDato();
                aux = aux.getSiguiente();
            }
        }

        return matriz;
    }

    public ListaEnlazada<E> toList(E[] matriz) {
        this.vaciar();
        for (int i = 0; i < matriz.length; i++) {
            this.insertar(matriz[i]);
        }
        return this;
    }
    public ListaEnlazada<E> metodoQuick(String objeto, TipoOrdenacion tipoOrden) throws Exception {
        CasaController casita = new CasaController();
        Class<E> clazz = null;
        E[] matriz = null;
        if (size > 0) {
            clazz = (Class<E>) cabecera.getDato().getClass();//primitivo, Dato envolvente, Object
            Boolean isObject = Utilidades.isObject(clazz);//si es objeto
            Field field = Utilidades.getField(objeto, clazz);
            matriz = toArray();
            matriz = datoQuickSort(matriz, objeto, field.getType(), tipoOrden);
        }
        if (matriz != null) {
            toList(matriz);
            System.out.println("Vuelve");
        }
        return this;
    }

    private E[] datoQuickSort(E[] matriz, String atributo, Class clazz, TipoOrdenacion tipoOrdenacion) throws Exception {
        E[] mat = matriz;
        quickSort(mat, 0, mat.length - 1, atributo, clazz, tipoOrdenacion);
        return mat;
    }

    private void quickSort(E[] arrelo, int inicio, int fin, String atributo, Class clazz, TipoOrdenacion tipoOrdenacion) throws Exception {
        E pivote = arrelo[inicio];
        int i = inicio;
        int j = fin;
        while (i < j) {      
            Method method = getMethod("get" + Utilidades.capitalizar(atributo), arrelo[i].getClass());
            Method method1 = getMethod("get" + Utilidades.capitalizar(atributo), arrelo[j].getClass());
            Method method2 = getMethod("get" + Utilidades.capitalizar(atributo), pivote.getClass());
            if (Utilidades.isNumber(clazz)) {
                if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString())) {
                    while (((Number) method.invoke(arrelo[i])).doubleValue() <= ((Number) method2.invoke(pivote)).doubleValue() && i < j) {
                        // cambioBurbuja(j, matriz);
                        i++;
                    }
                    while (((Number) method1.invoke(arrelo[j])).doubleValue() > ((Number) method2.invoke(pivote)).doubleValue()) {
                        // cambioBurbuja(j, matriz);
                        j--;
                    }
                } else {
                    while (((Number) method.invoke(arrelo[i])).doubleValue() >= ((Number) method2.invoke(pivote)).doubleValue() && i < j) {
                        // cambioBurbuja(j, matriz);
                        i++;
                    }
                    while (((Number) method1.invoke(arrelo[j])).doubleValue() < ((Number) method2.invoke(pivote)).doubleValue()) {
                        // cambioBurbuja(j, matriz);
                        j--;
                    }
                }
                if (i < j) {
                    E aux = arrelo[i];
                    arrelo[i] = arrelo[j];
                    arrelo[j] = aux;                   
                }             
            } else if (Utilidades.isString(clazz)) {
                if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString())) {
                    while (((String) method.invoke(arrelo[i])).toLowerCase().compareTo(((String) method2.invoke(pivote)).toLowerCase()) <= 0 && i < j) {
                        // cambioBurbuja(j, matriz);
                        i++;
                    }
                    while (((String) method1.invoke(arrelo[j])).toLowerCase().compareTo(((String) method2.invoke(pivote)).toLowerCase()) > 0) {
                        // cambioBurbuja(j, matriz);
                        j--;
                    }
                } else {
                    while (((String) method.invoke(arrelo[i])).toLowerCase().compareTo(((String) method2.invoke(pivote)).toLowerCase()) >= 0 && i < j) {
                        // cambioBurbuja(j, matriz);
                        i++;
                    }
                    while (((String) method1.invoke(arrelo[j])).toLowerCase().compareTo(((String) method2.invoke(pivote)).toLowerCase()) < 0) {
                        // cambioBurbuja(j, matriz);
                        j--;
                    }
                }
                if (i < j) {
                    E aux = arrelo[i];
                    arrelo[i] = arrelo[j];
                    arrelo[j] = aux;       
                }
            } else if (Utilidades.isCharacter(clazz)) {
                if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString())) {
                    while (((Character) method.invoke(arrelo[i])) <= ((Character) method2.invoke(pivote)) && i < j) {
                        // cambioBurbuja(j, matriz);
                        i++;
                    }
                    while (((Character) method1.invoke(arrelo[j])) > ((Character) method2.invoke(pivote))) {
                        // cambioBurbuja(j, matriz);
                        j--;
                    }
                } else {
                    while (((Character) method.invoke(arrelo[i])) >= ((Character) method2.invoke(pivote)) && i < j) {
                        // cambioBurbuja(j, matriz);
                        i++;
                    }
                    while (((Character) method1.invoke(arrelo[j])) < ((Character) method2.invoke(pivote))) {
                        // cambioBurbuja(j, matriz);
                        j--;
                    }
                }
                if (i < j) {
                    E aux = arrelo[i];
                    arrelo[i] = arrelo[j];
                    arrelo[j] = aux;
                }
            }
        }
        arrelo[inicio] = arrelo[j];
        arrelo[j] = pivote;

        if (inicio < j - 1) {
            quickSort(arrelo, inicio, j - 1, atributo, clazz, tipoOrdenacion);
        }

        if (j + 1 < fin) {
            quickSort(arrelo, j + 1, fin, atributo, clazz, tipoOrdenacion);
        }

    }
    
    public ListaEnlazada<E> Shell(String atributo, TipoOrdenacion tipoOrdenacion) throws  Exception {
        Class<E> clazz = null;
        E[] matriz = null;
        if (size > 0) {
            clazz = (Class<E>) cabecera.getDato().getClass();//primitivo, Dato envolvente, Object
            Boolean isObject = Utilidades.isObject(clazz);//si es objeto
            matriz = toArray();
            int i, salto;
            boolean ordenado;

            for (salto = matriz.length / 2; salto != 0; salto /= 2) {
                ordenado = true;
                while (ordenado) {
                    ordenado = false;
                    for (i = salto; i < matriz.length; i++) {
                        if (isObject) {
                            Field field = Utilidades.getField(atributo, clazz);
                            Method method = getMethod("get" + Utilidades.capitalizar(atributo), matriz[i - salto].getClass());
                            Method method1 = getMethod("get" + Utilidades.capitalizar(atributo), matriz[i].getClass());
                            Object[] aux = evaluaCambiarDato(field.getType(), matriz[i - salto], matriz[i], method, method1, tipoOrdenacion, i - salto, matriz);
                            if (aux[0] != null) {
                                E cambio = matriz[i];
                                matriz[i] = matriz[i - salto];
                                matriz[i - salto] = cambio;
                                ordenado = true;
                            }
                        } else {
                            Object[] aux = evaluaCambiarDatoNoObjeto(clazz, matriz[i - salto], matriz[i], tipoOrdenacion, i - salto, matriz);
                            if (aux[0] != null) {
                                E cambio = matriz[i];
                                matriz[i] = matriz[i - salto];
                                matriz[i - salto] = cambio;
                                ordenado = true;
                            }
                        }
                    }
                }
            }
        }
        if (matriz != null) {
            toList(matriz);
        }
        return this;
    }

    private Object[] evaluaCambiarDatoNoObjeto(Class clazz, E auxJ, E auxJ1, TipoOrdenacion tipoOrdenacion, Integer j, E[] matriz) throws Exception {
        Object aux[] = new Object[2];//aux[0];--->null
        if (clazz.getSuperclass().getSimpleName().equalsIgnoreCase("Number")) {
            // Number datoJ = (Number) auxJ;
            // Number datoJ1 = (Number) auxJ1;
            if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString())) {
                if ((((Number) auxJ).doubleValue() > ((Number) auxJ1).doubleValue())) {
                    aux[0] = auxJ1;
                    aux[1] = j;
                    //  cambioBurbuja(j, matriz);
                }
            } else {
                if ((((Number) auxJ).doubleValue() < ((Number) auxJ1).doubleValue())) {
                    // cambioBurbuja(j, matriz);
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            }
        } else if (Utilidades.isString(clazz)) {
            String datoJ = (String) auxJ;
            String datoJ1 = (String) auxJ1;
            if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString())) {
                if ((datoJ.toLowerCase().compareTo(datoJ1.toLowerCase()) > 0)) {
                    //cambioBurbuja(j, matriz);
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            } else {
                if ((datoJ.toLowerCase().compareTo(datoJ1.toLowerCase()) < 0)) {
                    //cambioBurbuja(j, matriz);
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            }

        } else if (Utilidades.isCharacter(clazz)) {
            Character datoJ = (Character) auxJ;
            Character datoJ1 = (Character) auxJ1;
            if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString())) {
                if (datoJ > datoJ1) {
                    //cambioBurbuja(j, matriz);
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            } else {
                if (datoJ < datoJ1) {
                    //cambioBurbuja(j, matriz);
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            }

        }
        return aux;
    }

    private Object[] evaluaCambiarDato(Class clazz, E auxJ, E auxJ1, Method method, Method method1, TipoOrdenacion tipoOrdenacion, Integer j, E[] matriz) throws Exception {
        Object aux[] = new Object[2];
        if (clazz.getSuperclass().getSimpleName().equalsIgnoreCase("Number")) {
            Number datoJ = (Number) method.invoke(auxJ);
            Number datoJ1 = (Number) method1.invoke(auxJ1);
            if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString())) {
                if ((datoJ.doubleValue() > datoJ1.doubleValue())) {
                    // cambioBurbuja(j, matriz);
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            } else {
                if ((datoJ.doubleValue() < datoJ1.doubleValue())) {
                    //    cambioBurbuja(j, matriz);
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            }
        } else if (Utilidades.isString(clazz)) {
            String datoJ = (String) method.invoke(auxJ);
            String datoJ1 = (String) method1.invoke(auxJ1);
            if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString())) {
                if ((datoJ.toLowerCase().compareTo(datoJ1.toLowerCase()) > 0)) {
                    //   cambioBurbuja(j, matriz);
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            } else {
                if ((datoJ.toLowerCase().compareTo(datoJ1.toLowerCase()) < 0)) {
                    //  cambioBurbuja(j, matriz);
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            }

        } else if (Utilidades.isCharacter(clazz)) {
            Character datoJ = (Character) method.invoke(auxJ);
            Character datoJ1 = (Character) method1.invoke(auxJ1);
            if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString())) {
                if (datoJ > datoJ1) {
                    // cambioBurbuja(j, matriz);
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            } else {
                if (datoJ < datoJ1) {
                    //  cambioBurbuja(j, matriz);
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            }

        }
        return aux;
    }
}
