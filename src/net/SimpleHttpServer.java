package net;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import sun.nio.cs.US_ASCII;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleHttpServer {

    private HttpServer server;

    public SimpleHttpServer() throws IOException {
        this(null, new InetSocketAddress(8080), 0);
    }

    public SimpleHttpServer(int port) throws IOException {
        this(null, new InetSocketAddress(port), 0);
    }

    public SimpleHttpServer(int port, int backlog) throws IOException {
        this(null, new InetSocketAddress(port), backlog);
    }

    public SimpleHttpServer(Executor exec, int port) throws IOException {
        this(exec, new InetSocketAddress(port), 0);
    }

    public SimpleHttpServer(Executor exec, int port, int backlog) throws IOException {
        this(exec, new InetSocketAddress(port), backlog);
    }

    public SimpleHttpServer(Executor exec, InetSocketAddress addr, int backlog) throws IOException {
        AtomicInteger counter = new AtomicInteger();

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

        private GetHandler(AtomicInteger counter) {
            this.counter = counter;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {

            // Only accept GET requests
            if (t.getRequestMethod().equalsIgnoreCase("GET")) {
                // Increment counter
                int c = counter.incrementAndGet();

                // Respond with "OK", counter in payload
                OutputStream os = t.getResponseBody();

                String response = "OK, " + String.valueOf(c);
                t.sendResponseHeaders(200, response.length());

                // Write payload
                os.write(response.getBytes(StandardCharsets.US_ASCII));

                // Close streams
                os.flush();
                os.close();
            } else {
                // Respond with "Method Not Allowed" client error
                t.sendResponseHeaders(405, 0);
            }
        }
    }
}
