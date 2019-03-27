package net;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GetHandler implements HttpHandler {


    @Override
    public void handle(HttpExchange t) throws IOException {

        // Only accept GET requests
        if (t.getRequestMethod().equalsIgnoreCase("GET")) {

            //pull the headers
            Headers h = t.getRequestHeaders();

            //debug line
            //System.out.println(h.get("filename"));

            //get the filename from the headers
            List<String> l = h.get("filename");
            String filename = l.get(0);

            //Debug lines
            //System.out.println("Filename");
            //System.out.println(filename);

            //instantiating the response
            String response = "";
            int responseCode = 500;
            try {
                // access the file that was requested
                BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
                String cur;
                while( (cur = br.readLine()) != null) {
                    response += cur + "\n";
                }
                responseCode = 200;
            } catch (FileNotFoundException e) {
                response = "<h1>404 Error</h1> <p>File not found</p>";
                responseCode = 404;
            }

            OutputStream os = t.getResponseBody();

            t.sendResponseHeaders(responseCode, response.length());

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
