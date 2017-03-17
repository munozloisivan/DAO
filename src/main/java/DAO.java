import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

/**
 * Created by ivanm on 15/03/2017.
 */
public abstract class DAO {

    //Conexión a la BBDD
    public static Connection getConnection(){
        Connection con = null;
        try{
            String host = "localhost", database = "dao";
            int port = 3306;
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
            Properties info = new Properties();
            info.setProperty("user", "root");
            info.setProperty("password", "mysql");
            info.setProperty("useSSL", "false");
            info.setProperty("serverTimezone", "UTC");
            con = DriverManager.getConnection(url, info);
            System.out.println("Conexión BBDD creada \n");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }

    private String getValues(Field field){
        String val = null;
        try {
            Method met = this.getClass().getMethod(getUpper(field.getName()),null);
            val = met.invoke(this,null).toString();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return val;
    }

    //necesaria sino REVIENTA pq no pilla bien la clase
    private String getUpper(String m){
        String res = Character.toUpperCase(m.charAt(0)) + m.substring(1);
        return "get".concat(res);
    }


    public void insert(){
        StringBuffer sb = new StringBuffer("INSERT INTO ").append(this.getClass().getName());

        Field[] fields = this.getClass().getDeclaredFields();
        sb.append(" (");

        int i = 0;
        for (Field f : fields){
            sb.append(f.getName());
            i++;
            if (i!= fields.length)
                sb.append(",");
        }
        sb.append(")");
        System.out.println(sb.toString());
        sb.append(" VALUES (");
    /*    int j = 0;
        for (Field f : fields){
            sb.append("?");
            j++;
            if (j!= fields.length)
                sb.append(",");
        }*/
        int j = 0;
        for (Field f : fields){
            if (j == fields.length -1){
                 sb.append(getValues(f));
            }
            else {
                sb.append(getValues(f)+",");
            }
            j++;
        }
        sb.append(")");
        System.out.println("INSERT query --> "+sb.toString());
    }

    public void update(){
        System.out.println("Update: " +this.getClass().getName());
        StringBuffer sb = new StringBuffer("UPDATE ").append(this.getClass().getName()).append(" SET ");

        Field[] fields = this.getClass().getDeclaredFields();
        int numfields = 0;
        for (Field f : fields){
            if (numfields == fields.length -1){
                sb.append(f.getName() + "=?");
            }
            else{
                sb.append(f.getName() + "=?,");
            }
            numfields++;
        }
        System.out.println("UPDATE query --> "+sb.toString());
    }

    public void select(){
        StringBuffer sb = new StringBuffer("SELECT * FROM ").append(this.getClass().getName());
        sb.append(" WHERE ");
        sb.append(" ? = ?");

        System.out.println("SELECT query --> "+sb.toString() );
    }

    public void delete(){
        StringBuffer sb = new StringBuffer("DELETE FROM ");
        sb.append(this.getClass().getName());
        sb.append(" WHERE ");
        sb.append(" ? =").append(" ?");
        System.out.println("DELETE query --> "+sb.toString());
    }
}
