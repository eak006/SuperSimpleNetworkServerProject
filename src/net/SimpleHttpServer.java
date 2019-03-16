package net;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleHttpServer {

    AtomicInteger counter;
    HttpServer server;

    public SimpleHttpServer(Executor exec, InetSocketAddress addr, int backlog) throws IOException {
        counter = new AtomicInteger();
        server = HttpServer.create(addr, backlog);
        server.setExecutor(exec);
        server.createContext("/get", new GetHandler(counter));
    }

    public void start() {
        server.start();
    }

    public void stop(int delay) {
        server.stop(delay);
    }

    static class GetHandler implements HttpHandler {

        AtomicInteger counter;

        public GetHandler(AtomicInteger counter) {
            this.counter = counter;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {

            // Only accept GET requests
            if (t.getRequestMethod().equalsIgnoreCase("GET")) {
                // Increment counter
                int c = counter.incrementAndGet();

                // Respond with "OK", counter in payload
                t.sendResponseHeaders(200, 4);
                OutputStream os = t.getResponseBody();

                // Convert int to bytes
                ByteBuffer bb = ByteBuffer.allocate(4);
                bb.putInt(c);

                // Write payload
                os.write(bb.array());

                // Close streams
                os.close();
            } else {
                // Respond with "Method Not Allowed" client error
                t.sendResponseHeaders(405, 0);
            }
        }
    }
}
