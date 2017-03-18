import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException {
        System.out.println("Hello World!");

       Usuario u = new Usuario(3,"Ivan","ivan@mail.com","password");
      // u.insert(); //OK
       // u.update(); //OK
      // u.select(2); //
        u.select(3);
       // u.delete(); //OK
        //Oficina of = new Oficina(1,"EETAC","Castefa");
        //of.insert(); //OK
       // of.select(1);

    }
}
