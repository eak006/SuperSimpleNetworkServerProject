package tests;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import net.GetHandler;
import org.junit.After;
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
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyByte;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetHandlerTest {

    @Mock
    HttpExchange mockExchange;

    @Spy
    OutputStream os;

    @Spy
    AtomicInteger counter;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    protected GetHandler handler;

    @Before
    public void setUp() throws Exception {
        counter = Mockito.spy(new AtomicInteger());
        os = Mockito.spy(new ByteArrayOutputStream());

        handler = new GetHandler(counter);
    }

    @Test
    public void handle() throws IOException {
        when(mockExchange.getRequestMethod()).thenReturn("GET");
        when(mockExchange.getResponseBody()).thenReturn(os);
        //when(os.write(anyByte())).then()

        handler.handle(mockExchange);

        assertEquals(os.toString(), "Counter = " + String.valueOf(counter.get()));
    }
}
