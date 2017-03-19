import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException {
        System.out.println("Hello World!");

       Usuario u = new Usuario(4,"toni","toni@mail.com","password");
       //u.insert(); //OK
       // u.update(); //OK
       u.select(4); //
       // u.select(3);
       // u.delete(); //OK
     // Oficina of = new Oficina("CBL","Baixllobregat");
    //   of.insert(); //OK
        //of.select(1);

        //Lista USUARIOS
        List<Usuario> a = u.getAllUsers();
        int i = 0;
        for (Usuario usuario : a){
            System.out.println("id: "+(int)a.get(i).getId()+" | nombre: "+a.get(i).getNombre()+" | email: "+a.get(i).getEmail()+" | password: "+a.get(i).getPassword());
            i++;
        }
    }
}
