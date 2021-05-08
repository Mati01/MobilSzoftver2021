package Presenter.Classes;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import DataBase.BookRepository;
import Model.Booklet;
import Screens.Interaces.ILibraryScreen;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import swagger.api.LibraryApi;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class LibraryPresenterTest {

    @Mock
    BookRepository repo;
    @Mock
    LibraryApi service;
    @Mock
    ILibraryScreen screen;


    LibraryPresenter libraryPresenter;

    @Before
    public void setUp() throws Exception {
        repo = mock(BookRepository.class);
        service = mock(LibraryApi.class);
        screen = mock(ILibraryScreen.class);

        libraryPresenter = new LibraryPresenter(repo, service);
        libraryPresenter.AttachScreen(screen);
    }

    @Test
    public void apiGetBooklets() {
        Call<List<Booklet>> mockedCall = Mockito.mock(Call.class);

        when(service.getBooklets(anyString(),anyString())).thenReturn(mockedCall);

        List<Booklet> booklets = new ArrayList<Booklet>();
        booklets.add(  new Booklet("TestTitle1", "TestSubTitle1", new Date(2020, 01, 01), "TestAuthor1"));
        booklets.add(  new Booklet("TestTitle2", "TestSubTitle1", new Date(2020, 01, 01), "TestAuthor1"));
        booklets.add(  new Booklet("TestTitle3", "TestSubTitle1", new Date(2020, 01, 01), "TestAuthor1"));


        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<List<Booklet>> callback = invocation.getArgumentAt(0, Callback.class);

                callback.onResponse(mockedCall, Response.success(booklets));

                return null;
            }
        }).when(mockedCall).enqueue(Mockito.any(Callback.class));

        libraryPresenter.ApiGetBooklets("","");

        verify(service, times(1)).getBooklets("","");
        verify(screen, times(1)).SetBooklets(booklets);

        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<List<Booklet>> callback = invocation.getArgumentAt(0, Callback.class);

                callback.onResponse(mockedCall, Response.error(404, Mockito.mock(ResponseBody.class)));

                return null;
            }
        }).when(mockedCall).enqueue(Mockito.any(Callback.class));

        libraryPresenter.ApiGetBooklets("","");
        verify(screen, times(1)).DisplayException(anyString());
    }

    @Test
    public void apiDeleteBook() {
        Call<Void> mockedCall = Mockito.mock(Call.class);

        when(service.deleteBooklet(anyInt())).thenReturn(mockedCall);

        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<Void> callback = invocation.getArgumentAt(0, Callback.class);

                callback.onResponse(mockedCall, Response.success(null));

                return null;
            }
        }).when(mockedCall).enqueue(Mockito.any(Callback.class));

        libraryPresenter.ApiDeleteBook(0);

        verify(service, times(1)).deleteBooklet(0);
        verify(screen, times(1)).RemoveBooklet(0);
    }

    @Test
    public void repoGetBooklets() {
        List<Booklet> booklets = new ArrayList<Booklet>();
        booklets.add(  new Booklet("TestTitle1", "TestSubTitle1", new Date(2020, 01, 01), "TestAuthor1"));
        booklets.add(  new Booklet("TestTitle2", "TestSubTitle1", new Date(2020, 01, 01), "TestAuthor1"));
        booklets.add(  new Booklet("TestTitle3", "TestSubTitle1", new Date(2020, 01, 01), "TestAuthor1"));


        Mockito.doAnswer(answer -> {
            Consumer<List<Booklet>> messageConsumer = (Consumer<List<Booklet>>) answer.getArgumentAt(2, Consumer.class);
            messageConsumer.accept(booklets);

            return null;
        }).when(repo).getBooklets(anyString(),anyString(), Mockito.any(Consumer.class));

        libraryPresenter.RepoGetBooklets("","");

        verify(repo, times(1)).getBooklets(anyString(),anyString(), Mockito.any());
        verify(screen, times(1)).SetBooklets(booklets);
    }

    @Test
    public void repoDeleteBook() {
        int id = 0;
        Mockito.doAnswer(answer -> {
            Consumer<Void> messageConsumer = (Consumer<Void>) answer.getArgumentAt(1, Consumer.class);
            messageConsumer.accept(null);

            return null;
        }).when(repo).delete(anyInt(), Mockito.any(Consumer.class));

        libraryPresenter.RepoDeleteBook(id);

        verify(repo, times(1)).delete(anyInt(), Mockito.any());
        verify(screen, times(1)).RemoveBooklet(id);
    }
}