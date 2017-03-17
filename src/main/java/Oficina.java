/**
 * Created by ivanm on 15/03/2017.
 */
public class Oficina extends DAO{

    private int id;
    private String nombre, direccion;

    public Oficina(int id, String nombre, String direccion) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Oficina(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
