package Presenter.Classes;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Date;
import java.util.function.Consumer;

import DataBase.BookRepository;
import Model.Book;
import Screens.Interaces.ICreatorScreen;
import Screens.Interaces.IDetailsScreen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import swagger.api.DetailsApi;
import swagger.api.EditorApi;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class DetailsPresenterTest {

    @Mock
    BookRepository repo;
    @Mock
    DetailsApi service;

    @Mock
    IDetailsScreen screen;


    DetailsPresenter presenter;

    @Before
    public void setUp() throws Exception {
        repo = mock(BookRepository.class);
        service = mock(DetailsApi.class);
        screen = mock(IDetailsScreen.class);

        presenter = new DetailsPresenter(repo, service);
        presenter.AttachScreen(screen);
    }

    @Test
    public void getBookId(){
        presenter.SetBookId(1);

        assertEquals((Integer)1, presenter.GetBookId());
    }

    @Test
    public void setBookId() {
        presenter.SetBookId(1);

        assertEquals((Integer)1, presenter.GetBookId());
    }

    @Test
    public void apiGetBook() {
        Book testBook = new Book("TestTitle", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);
        final Integer id = 1;

        presenter.SetBookId(1);

        Call<Book> mockedCall = Mockito.mock(Call.class);

        when(service.getDetails(any())).thenReturn(mockedCall);

        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<Book> callback = invocation.getArgumentAt(0, Callback.class);

                callback.onResponse(mockedCall, Response.success(testBook));

                return null;
            }
        }).when(mockedCall).enqueue(Mockito.any(Callback.class));

        presenter.ApiGetBook();

        verify(service, times(1)).getDetails(id);
        verify(screen, times(1)).SetBook(testBook);
    }

    @Test
    public void apiDeleteBook() {
        presenter.SetBookId(1);

        Call<Void> mockedCall = Mockito.mock(Call.class);

        when(service.deleteBook(any())).thenReturn(mockedCall);

        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<Void> callback = invocation.getArgumentAt(0, Callback.class);

                callback.onResponse(mockedCall, Response.success(null));

                return null;
            }
        }).when(mockedCall).enqueue(Mockito.any(Callback.class));

        presenter.ApiDeleteBook();

        verify(service, times(1)).deleteBook(anyInt());
        verify(screen, times(1)).BookDeleted();
    }

    @Test
    public void repoGetBook() {
        Book testBook = new Book("TestTitle", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);
        final Integer id = 1;

        presenter.SetBookId(1);

        Mockito.doAnswer(answer -> {
            Consumer<Book> messageConsumer = (Consumer<Book>) answer.getArgumentAt(1, Consumer.class);
            messageConsumer.accept(testBook);

            return null;
        }).when(repo).getBook(anyInt(), Mockito.any(Consumer.class));

        presenter.RepoGetBook();

        verify(repo, times(1)).getBook(anyInt(), any());
        verify(screen, times(1)).SetBook(testBook);
    }

    @Test
    public void repoDeleteBook() {
        presenter.SetBookId(1);

        Mockito.doAnswer(answer -> {
            Consumer<Void> messageConsumer = (Consumer<Void>) answer.getArgumentAt(1, Consumer.class);
            messageConsumer.accept(null);

            return null;
        }).when(repo).delete(anyInt(), Mockito.any(Consumer.class));

        presenter.RepoDeleteBook();

        verify(repo, times(1)).delete(anyInt(), any());
        verify(screen, times(1)).BookDeleted();
    }
}