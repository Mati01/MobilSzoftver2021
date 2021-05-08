package Presenter.Classes;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import DataBase.BookRepository;
import Model.Book;
import Model.Booklet;
import Screens.Interaces.ICreatorScreen;
import Screens.Interaces.ILibraryScreen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import swagger.api.EditorApi;
import swagger.api.LibraryApi;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class CreatorPresenterTest {

    @Mock
    BookRepository repo;
    @Mock
    EditorApi service;

    @Mock
    ICreatorScreen screen;


    CreatorPresenter presenter;

    @Before
    public void setUp() throws Exception {
        repo = mock(BookRepository.class);
        service = mock(EditorApi.class);
        screen = mock(ICreatorScreen.class);

        presenter = new CreatorPresenter(repo, service);
        presenter.AttachScreen(screen);
    }

    @Test
    public void repoSaveBookInsert() throws NoSuchFieldException {
        Book testBook = new Book("TestTitle", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);
        Integer id = null;

        FieldSetter a = new FieldSetter(presenter, presenter.getClass().getDeclaredField("Id"));
        a.set(id);

        Mockito.doAnswer(answer -> {
            Consumer<Integer> messageConsumer = (Consumer<Integer>) answer.getArgumentAt(1, Consumer.class);
            messageConsumer.accept(1);

            return null;
        }).when(repo).insert(any(), Mockito.any(Consumer.class));

        presenter.RepoSaveBook(testBook);

        Assert.assertEquals(id, testBook.getId());

        verify(repo, times(1)).insert(any(), any());
        verify(screen, times(1)).BookCreated(1);
    }

    @Test
    public void repoSaveBookUpdate() throws NoSuchFieldException {
        Book testBook = new Book("TestTitle", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);
        Integer id = 1;

        FieldSetter a = new FieldSetter(presenter, presenter.getClass().getDeclaredField("Id"));
        a.set(id);

        Mockito.doAnswer(answer -> {
            Consumer<Void> messageConsumer = (Consumer<Void>) answer.getArgumentAt(1, Consumer.class);
            messageConsumer.accept(null);

            return null;
        }).when(repo).update(any(), Mockito.any(Consumer.class));

        presenter.RepoSaveBook(testBook);

        Assert.assertEquals(id, testBook.getId());

        verify(repo, times(1)).update(any(), any());
        verify(screen, times(1)).BookUpdated(1);
    }

    @Test
    public void apiSaveBookInsert() throws NoSuchFieldException {
        Book testBook = new Book("TestTitle", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);
        final Integer[] id = {null};

        FieldSetter a = new FieldSetter(presenter, presenter.getClass().getDeclaredField("Id"));
        a.set(id[0]);

        Call<Book> mockedCall = Mockito.mock(Call.class);

        when(service.postBook(any())).thenReturn(mockedCall);

        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<Book> callback = invocation.getArgumentAt(0, Callback.class);

                id[0] = 1;
                testBook.setId(id[0]);
                callback.onResponse(mockedCall, Response.success(testBook));

                return null;
            }
        }).when(mockedCall).enqueue(Mockito.any(Callback.class));

        presenter.ApiSaveBook(testBook);

        Assert.assertEquals(id[0], testBook.getId());

        verify(service, times(1)).postBook(testBook);

        verify(screen, times(1)).BookCreated(id[0]);
    }

    @Test
    public void apiSaveBookUpdate() throws NoSuchFieldException {
        Book testBook = new Book("TestTitle", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);
        final Integer id = 1;

        FieldSetter a = new FieldSetter(presenter, presenter.getClass().getDeclaredField("Id"));
        a.set(id);

        Call<Book> mockedCall = Mockito.mock(Call.class);

        when(service.updateBook(any())).thenReturn(mockedCall);

        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<Book> callback = invocation.getArgumentAt(0, Callback.class);

                callback.onResponse(mockedCall, Response.success(testBook));

                return null;
            }
        }).when(mockedCall).enqueue(Mockito.any(Callback.class));

        presenter.ApiSaveBook(testBook);

        Assert.assertEquals(id, testBook.getId());

        verify(service, times(1)).updateBook(testBook);
        verify(screen, times(1)).BookUpdated(id);
    }

    @Test
    public void apiGetBook() throws NoSuchFieldException {
        Book testBook = new Book("TestTitle", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);
        final Integer id = 1;

        FieldSetter a = new FieldSetter(presenter, presenter.getClass().getDeclaredField("Id"));
        a.set(id);

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
    public void repoGetBook() throws NoSuchFieldException {
        Book testBook = new Book("TestTitle", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);
        Integer id = 1;
        testBook.setId(id);

        FieldSetter a = new FieldSetter(presenter, presenter.getClass().getDeclaredField("Id"));
        a.set(id);

        Mockito.doAnswer(answer -> {
            Consumer<Book> messageConsumer = (Consumer<Book>) answer.getArgumentAt(1, Consumer.class);
            messageConsumer.accept(testBook);

            return null;
        }).when(repo).getBook(anyInt(), Mockito.any(Consumer.class));

        presenter.RepoGetBook();

        verify(repo, times(1)).getBook(anyInt(), any());
        verify(screen, times(1)).SetBook(testBook);
    }
}