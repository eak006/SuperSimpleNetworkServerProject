package Client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SimpleHttpClient {

    private URL url;
    HttpURLConnection con;

    public SimpleHttpClient() throws IOException {
        url = new URL("http://0.0.0.0:8081/get");
        con = (HttpURLConnection) url.openConnection();

        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
    }

}
