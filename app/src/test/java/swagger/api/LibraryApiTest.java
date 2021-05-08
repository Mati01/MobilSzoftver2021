package swagger.api;

import com.google.gson.Gson;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.Book;
import Model.Booklet;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

public class LibraryApiTest {

    @Mock
    MockWebServer server;

    @Mock
    LibraryApi service;

    private Gson jsonConverter = new Gson();
    ;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();

        service = new Retrofit.Builder()
                .baseUrl(server.url("/"))
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LibraryApi.class);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void getBooklets() throws IOException {
        List<Booklet> booklets = new ArrayList<>();
        booklets.add(new Booklet("TestTitle", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor"));
        server.enqueue(new MockResponse().setBody(jsonConverter.toJson(booklets)));
        Request request = service.getBooklets("", "").request();
        Response<List<Booklet>> response = service.getBooklets("", "").execute();

        Assert.assertEquals("GET", request.method());
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.code());
        Assert.assertEquals(booklets, response.body());
        Assert.assertEquals(booklets.get(0).getAuthor(), response.body().get(0).getAuthor());
    }

    @Test
    public void deleteBooklet() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200));
        Request request = service.deleteBooklet(0).request();
        Response<Void> response = service.deleteBooklet(0).execute();

        Assert.assertEquals("DELETE", request.method());
        Assert.assertNotNull(response);
        Assert.assertEquals(200,response.code());
    }
}