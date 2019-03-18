package tests;

import net.SimpleHttpServer;
import org.junit.Rule;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class SimpleHttpServerTest   {

    @Mock
    protected Executor execMock;

    @Spy
    protected InetSocketAddress isaMock;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    protected SimpleHttpServer server;

    @org.junit.Before
    public void setUp() throws Exception {
        isaMock = Mockito.spy(new InetSocketAddress(8081));
        int backlog = 0;
        server = new SimpleHttpServer(execMock, isaMock, backlog);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void start() {
        server.start();
        assertTrue(server.isRunning());
    }

    @org.junit.Test
    public void stop() throws InterruptedException {
        server.start();

        int delay = 1000;
        server.stop(delay);

        TimeUnit.SECONDS.wait(delay);

        assertFalse(server.isRunning());
    }
}