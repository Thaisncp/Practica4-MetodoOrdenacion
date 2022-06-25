package modelo;
/**
 * @author Thais Caruche
 */
public class Casa {
    private Integer precio;
    private Integer fecha;

    public Casa(Integer precio, Integer fechaConstruccion) {
        this.precio = precio;
        this.fecha = fechaConstruccion;
    }
    
    public Casa (){
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getFecha() {
        return fecha;
    }

    public void setFecha(Integer fechaConstruccion) {
        this.fecha = fechaConstruccion;
    }  

    @Override
    public String toString() {
        return "PRECIO = " + precio + "\tFECHA = " + fecha;
    }
    
    
}
