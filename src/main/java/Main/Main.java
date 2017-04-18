package Main;

import Models.Usuario;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.sql.SQLException;

public class Main {

    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/myapp/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */

    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in edu.upc.dsa package
        final ResourceConfig rc = new ResourceConfig().packages("DAO","Exceptions","JSONController","Main","Models");            // poner todos mis PACKAGES

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */


    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException, IOException {

        final HttpServer server = startServer();

        StaticHttpHandler staticHttpHandler = new StaticHttpHandler("./public/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/");

        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));

        System.in.read();
        server.stop();


      //  Usuario u = new Usuario("cambiado","cambiado","cambiado");
     //u.update(2);
       // Models.Usuario prueba = new Models.Usuario();
       //prueba.setId(3);
     //  u.insert(); //OK
       // u.update(); //OK
      // u.select(4); //
      // prueba.delete2();
       // u.select(3);
       // u.delete(); //OK
     //Models.Oficina of = new Models.Oficina("camping","Arenys de Mar");
      //of.insert(); //OK
        //of.select(1);

       // Models.Oficina ofPrueba = new Models.Oficina("eetac","castefa");
      //  ofPrueba.update(1);
       // ofPrueba.select(3);
       // ofPrueba.deleteXid(3);



        //Lista USUARIOS
       /* List<Models.Usuario> a = u.getAllUsers();
        int i = 0;
        System.out.println("** USUARIOS **");
        for (Models.Usuario usuario : a){
            System.out.println("id: "+(int)a.get(i).getId()+" | nombre: "+a.get(i).getNombre()+" | email: "+a.get(i).getEmail()+" | password: "+a.get(i).getPassword());
            i++;
        }*/



     /* List<Models.Oficina> b = ofPrueba.getAllOficinas();
       int j = 0;
        System.out.println("** OFICINAS **");
       for (Models.Oficina oficina : b){
           System.out.println("nombre : "+b.get(j).getNombre()+" | direccion: "+b.get(j).getDireccion());
           j++;
       }*/
    }
}
