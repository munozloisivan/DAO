
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Usuario u = new Usuario(1,"Ivan","ivan@mail.com","password");
        u.insert(); //OK
        u.update(); //OK
        u.select(); //OK
        u.delete(); //OK
        Oficina of = new Oficina(1,"EETAC","Castefa");
        of.insert(); //OK

    }
}
