/**
 * Created by ivanm on 15/03/2017.
 */
public class Oficina extends DAO{

    private String nombre, direccion;

    public Oficina(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Oficina(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
