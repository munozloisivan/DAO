import java.lang.reflect.Field;

/**
 * Created by ivanm on 15/03/2017.
 */
public abstract class DAO {

    public void getValores(){

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
        int j = 0;
        for (Field f : fields){
            sb.append("?");
            j++;
            if (j!= fields.length)
                sb.append(",");
        }
        sb.append(")");
        System.out.println(sb.toString());

    }

    public void update(DAO d){
        System.out.println("Update: " +d.getClass().getDeclaredFields());
    }
}
