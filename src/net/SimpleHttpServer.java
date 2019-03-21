package net;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleHttpServer {

    private HttpServer server;

    private boolean running = false;

    public SimpleHttpServer() throws IOException {
        this(null, new InetSocketAddress(8081), 0);
    }

    public SimpleHttpServer(Executor exec, InetSocketAddress addr, int backlog) throws IOException {
        AtomicInteger counter = new AtomicInteger();

        server = HttpServer.create(addr, backlog);
        server.setExecutor(exec);
        server.createContext("/get", new GetHandler(counter));
    }

    public boolean isRunning() {
        return running;
    }

    public void start() {
        server.start();
        running = true;
    }

    public void stop(int delay) {
        server.stop(delay);
        running = false;
    }
}
