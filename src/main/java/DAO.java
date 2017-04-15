import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created by ivanm on 15/03/2017.
 */
public abstract class DAO {

    //final static Logger logger = getLogger("DAO");

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
            System.out.println(e.getMessage());
        }
        return con;
    }

    private String getValues(Field field){
        String val = null;
        try {
            Method met = this.getClass().getMethod(getUpper(field.getName()),null);
            val = met.invoke(this,null).toString();
        } catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        } catch (InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
        return val;
    }

    //necesaria sino REVIENTA pq no pilla bien la clase
    private String getUpper(String m){
        String res = Character.toUpperCase(m.charAt(0)) + m.substring(1);
        return "get".concat(res);
    }


    public void insertElementos(PreparedStatement preparedStatement) throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
        int i = 1;
        Field[] fields = this.getClass().getDeclaredFields();
        System.out.println("fields: "+fields.length);
        for (Field f : fields){
            String res = getValues(f);
            System.out.println("posicion: "+i+" value: "+res);
            preparedStatement.setObject(i,res);
            i++;
        }
    }

    public void insert() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
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
        sb.append(" VALUES (");
        int j = 0;
        for (Field f : fields){
            sb.append("?");
            j++;
            if (j!= fields.length)
                sb.append(",");
        }
       /* int j = 0;
        for (Field f : fields){
            if (j == fields.length -1){
                 sb.append(getValues(f));
            }
            else {
                sb.append(getValues(f)+",");
            }
            j++;
        }*/
        sb.append(")");
        System.out.println("INSERT --> "+sb.toString()+"\n");

        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sb.toString());
            insertElementos(preparedStatement);
           /* int x = 1;
            Field[] fields2 = this.getClass().getDeclaredFields();
            for (Field f : fields2){
                String res = getValues(f);
                System.out.println("VALORES "+res.toString()); // los coge bien
                preparedStatement.setObject(i,res);
                x++;
            } */
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
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
        System.out.println("UPDATE --> "+sb.toString());
    }

    public void select(){
        StringBuffer sb = new StringBuffer("SELECT * FROM ").append(this.getClass().getName());
        sb.append(" WHERE ");
        sb.append(" ? = ?");

        System.out.println("SELECT --> "+sb.toString() );
    }

    public void select(int pk){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(this.getClass().getName()).append(" WHERE ID = "+pk);
        System.out.println("SELECT (PK) --> "+sb.toString()+"\n");

        Connection con = getConnection();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sb.toString());
            ResultSetMetaData rsmd = rs.getMetaData();
            rs.next();

            for (int i=1; i< rsmd.getColumnCount() +1; i++){ //nº de columnas de la tabla
                //separaas en funcion tel tipo que sean para convertir cada una
                if (rsmd.getColumnTypeName(i).equals("INT")){
                    System.out.println(rsmd.getColumnLabel(i)+" = "+rs.getInt(i));
                }
                if (rsmd.getColumnTypeName(i).equals("VARCHAR")){
                    System.out.println(rsmd.getColumnLabel(i)+" = "+rs.getString(i));
                }
                if (i== rsmd.getColumnCount()){ //cuando i=nº columnas sales del bucle yendo al siguiente (NEXT)
                    rs.next();
                    i = 0;
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(){
        StringBuffer sb = new StringBuffer("DELETE FROM ");
        sb.append(this.getClass().getName());
        sb.append(" WHERE ");
        sb.append(" ? =").append(" ?");
        System.out.println("DELETE --> "+sb.toString());
    }

    public void delete2() throws SQLException {
        StringBuffer sb = new StringBuffer("DELETE FROM ").append(this.getClass().getName());
        if(this.getClass().getName() == "Usuario"){sb.append(" WHERE id = ");}
        else{sb.append(" WHERE nombre = '");}
        Field[] fields = this.getClass().getDeclaredFields();
        if (this.getClass().getName() == "Usuario"){
        sb.append(Integer.parseInt(getValues(fields[0])));}
        else{
            sb.append(getValues(fields[0])+"'");
        }
        System.out.println("DELETE 2 --> "+sb.toString());

        Connection con = getConnection();
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sb.toString());
            /* executeUpdate --> executeQuery
            Use executeUpdate() to issue data manipulation statements. executeQuery()
            is only meant for SELECT queries (i.e. queries that return a result set).
            http://stackoverflow.com/questions/1905607/cannot-issue-data-manipulation-statements-with-executequery
            * */
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /*public void delete3() throws SQLException {
        StringBuffer sb = new StringBuffer("DELETE FROM ").append(this.getClass().getName());
        sb.append(" WHERE ")
    }*/

    public static List<Usuario> getAllUsers() throws SQLException {
        List<Usuario> listaUs = new ArrayList<Usuario>();
        Connection con = getConnection();
        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Usuario");
        while(rs.next()){
            Usuario us = new Usuario(rs.getInt("id"),rs.getString("nombre"),rs.getString("email"),rs.getString("password"));
            listaUs.add(us);
        }
        return listaUs;
    }

    public  static  List<Oficina> getAllOficinas() throws SQLException {
        List<Oficina> listaOf = new ArrayList<Oficina>();
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Oficina");
        while(rs.next()){
            Oficina of = new Oficina(rs.getString("nombre"),rs.getString("direccion"));
            listaOf.add(of);
        }
        return listaOf;
    }
}
