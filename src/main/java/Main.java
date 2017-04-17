import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException {
        System.out.println("Hello World!");

     //  Usuario u = new Usuario(3,"ivan","ivan@mail.com","password");
      // Usuario prueba = new Usuario();
       //prueba.setId(3);
     //  u.insert(); //OK
       // u.update(); //OK
      // u.select(4); //
      // prueba.delete2();
       // u.select(3);
       // u.delete(); //OK
     //Oficina of = new Oficina("camping","Arenys de Mar");
      //of.insert(); //OK
        //of.select(1);

        Oficina ofPrueba = new Oficina("city bcn","barcelona");
        ofPrueba.select(3);
        ofPrueba.deleteXid(3);
        //Lista USUARIOS
       /* List<Usuario> a = u.getAllUsers();
        int i = 0;
        System.out.println("** USUARIOS **");
        for (Usuario usuario : a){
            System.out.println("id: "+(int)a.get(i).getId()+" | nombre: "+a.get(i).getNombre()+" | email: "+a.get(i).getEmail()+" | password: "+a.get(i).getPassword());
            i++;
        }*/

    /*   List<Oficina> b = ofPrueba.getAllOficinas();
       int j = 0;
        System.out.println("** OFICINAS **");
       for (Oficina oficina : b){
           System.out.println("nombre : "+b.get(j).getNombre()+" | direccion: "+b.get(j).getDireccion());
           j++;
       }*/
    }
}
