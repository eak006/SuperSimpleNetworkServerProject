package net;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

class GetHandler implements HttpHandler {

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

            // Respond with "Counter", counter in payload
            OutputStream os = t.getResponseBody();

            String response = "Counter = " + String.valueOf(c);
            t.sendResponseHeaders(200, response.length());

            // Write payload
            os.write(response.getBytes(StandardCharsets.US_ASCII));

            // Close streams
            os.close();
        } else {
            // Client error:
            t.sendResponseHeaders(405, 0);
        }
    }
}
