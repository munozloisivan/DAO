/**
 * Created by ivanm on 15/03/2017.
 */
public class Oficina extends DAO{

        private int nombre;
        private String  direccion;

    public Oficina(int nombre, String direccion) {
        super();
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Oficina(){}

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
