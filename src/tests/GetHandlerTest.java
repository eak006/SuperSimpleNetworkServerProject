package tests;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import net.GetHandler;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetHandlerTest {

    @Mock
    HttpExchange mockExchange;

    @Spy
    OutputStream os;

    @Spy
    AtomicInteger counter;

    @Spy
    Headers header;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    protected GetHandler handler;

    @Before
    public void setUp() throws Exception {
        counter = Mockito.spy(new AtomicInteger());
        os = Mockito.spy(new ByteArrayOutputStream());
        header = new Headers();
        List<String> l = new ArrayList<String>();
        l.add("test.txt");
        header.put("filename", l);

        handler = new GetHandler();
    }

    @Test
    public void handle() throws IOException {
        when(mockExchange.getRequestMethod()).thenReturn("GET");
        when(mockExchange.getRequestHeaders()).thenReturn(header);
        when(mockExchange.getResponseBody()).thenReturn(os);

        //when(os.write(anyByte())).then()

        handler.handle(mockExchange);

        assertEquals(os.toString(), "Lorem ipsum dolor sit amet, usu paulo delectus consulatu an.\n" +
                "Et eam natum impetus. Sit sint oratio appetere ex.\n" +
                "Has nibh mazim molestie ex, commodo ornatus cu sit.\n" +
                "Ea vix summo labores, sed nibh numquam ea, deserunt scribentur his no.\n" +
                "Eum at tale assum, decore oblique neglegentur sed te.\n" +
                "Lorem ipsum dolor sit amet, usu paulo delectus consulatu an.\n" +
                "Et eam natum impetus. Sit sint oratio appetere ex.\n" +
                "Has nibh mazim molestie ex, commodo ornatus cu sit.\n" +
                "Ea vix summo labores, sed nibh numquam ea, deserunt scribentur his no.\n" +
                "Eum at tale assum, decore oblique neglegentur sed te.\n" +
                "Lorem ipsum dolor sit amet, usu paulo delectus consulatu an.\n" +
                "Et eam natum impetus. Sit sint oratio appetere ex.\n" +
                "Has nibh mazim molestie ex, commodo ornatus cu sit.\n" +
                "Ea vix summo labores, sed nibh numquam ea, deserunt scribentur his no.\n" +
                "Eum at tale assum, decore oblique neglegentur sed te.\n" +
                "Lorem ipsum dolor sit amet, usu paulo delectus consulatu an.\n" +
                "Et eam natum impetus. Sit sint oratio appetere ex.\n" +
                "Has nibh mazim molestie ex, commodo ornatus cu sit.\n" +
                "Ea vix summo labores, sed nibh numquam ea, deserunt scribentur his no.\n" +
                "Eum at tale assum, decore oblique neglegentur sed te.\n");
    }
}
