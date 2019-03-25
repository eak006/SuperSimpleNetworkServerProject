package net;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GetHandler implements HttpHandler {

    AtomicInteger counter;

    public GetHandler(AtomicInteger counter) {
        this.counter = counter;
    }

    @Override
    public void handle(HttpExchange t) throws IOException {

        // Only accept GET requests
        if (t.getRequestMethod().equalsIgnoreCase("GET")) {

            //pull the headers
            Headers h = t.getRequestHeaders();

            //get the filename from the headers
            List<String> l = h.get("filename");
            String filename = l.get(0);

            //start reading the file
            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));


            // Increment counter
            int c = counter.incrementAndGet();

            System.out.println(t.getRequestHeaders().get("file"));

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
