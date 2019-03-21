import net.SimpleHttpServer;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        try {
            SimpleHttpServer shs = new SimpleHttpServer();
            shs.start();
            System.out.println("testing -Erik");
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
