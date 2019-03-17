import net.SimpleHttpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

public class Main {

    public static void main(String[] args) {
        try {
            Executor e = new Executor() {
                @Override
                public void execute(Runnable command) {
                    System.out.println("Hey");
                }
            };

            String hostname = "asdf";
            int port = 8080;
            InetSocketAddress isa = new InetSocketAddress(hostname, port);

            int backlog = 0;

            SimpleHttpServer shs = new SimpleHttpServer(e, isa, backlog);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
