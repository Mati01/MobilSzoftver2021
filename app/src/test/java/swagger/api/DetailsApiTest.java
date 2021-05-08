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
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.internal.duplex.DuplexResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class DetailsApiTest {

    @Mock
    MockWebServer server;

    @Mock
    DetailsApi service;

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
                .create(DetailsApi.class);
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
    public void deleteBook() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200));
        Request request = service.deleteBook(0).request();
        Response<Void> response = service.deleteBook(0).execute();

        Assert.assertEquals("DELETE", request.method());
        Assert.assertNotNull(response);
        Assert.assertEquals(200,response.code());
    }
}