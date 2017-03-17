import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by ivanm on 15/03/2017.
 */
public abstract class DAO {

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
