package swagger.api;

import com.google.gson.Gson;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.Date;

import Model.Book;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

public class EditorApiTest {

    @Mock
    MockWebServer server;

    @Mock
    EditorApi service;

    private Gson jsonConverter = new Gson();;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();

        service = new Retrofit.Builder()
                .baseUrl(server.url("/"))
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EditorApi.class);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void getDetails() throws IOException {
        Book testBook = new Book("TestTitle","TestSubTitle", new Date(2020,01,01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);
        server.enqueue(new MockResponse().setBody(jsonConverter.toJson(testBook)));
        Request request = service.getDetails(0).request();
        Response<Book> response = service.getDetails(0).execute();

        Assert.assertEquals("GET", request.method());
        Assert.assertNotNull(response);
        Assert.assertEquals(200,response.code());
        Assert.assertEquals(testBook, response.body());
        Assert.assertEquals(testBook.getAuthor(), response.body().getAuthor());
    }

    @Test
    public void updateBook() throws IOException {
        Book testBook = new Book("TestTitle","TestSubTitle", new Date(2020,01,01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);
        testBook.setId(1);
        server.enqueue(new MockResponse().setBody(jsonConverter.toJson(testBook)));
        Request request = service.updateBook(testBook).request();
        Response<Book> response = service.updateBook(testBook).execute();

        Assert.assertEquals("PUT", request.method());
        Assert.assertNotNull(response);
        Assert.assertEquals(200,response.code());
        Assert.assertEquals(testBook, response.body());
        Assert.assertEquals(testBook.getAuthor(), response.body().getAuthor());
    }

    @Test
    public void postBook() throws IOException {
        Book testBook = new Book("TestTitle","TestSubTitle", new Date(2020,01,01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);
        server.enqueue(new MockResponse().setBody(jsonConverter.toJson(testBook)));
        Request request = service.postBook(testBook).request();
        Response<Book> response = service.postBook(testBook).execute();

        Assert.assertEquals("POST", request.method());
        Assert.assertNotNull(response);
        Assert.assertEquals(200,response.code());
        Assert.assertEquals(testBook, response.body());
        Assert.assertEquals(testBook.getAuthor(), response.body().getAuthor());
    }
}