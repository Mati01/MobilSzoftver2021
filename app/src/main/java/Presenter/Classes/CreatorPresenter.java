package Presenter.Classes;

import javax.inject.Inject;

import DataBase.BookRepository;
import Model.Book;
import Presenter.Interfaces.Api.IApiCreatorPresenter;
import Presenter.Interfaces.Repo.IRepoCreatorPresenter;
import Screens.Interaces.ICreatorScreen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import swagger.api.EditorApi;

public class CreatorPresenter extends PresenterBase<ICreatorScreen> implements IApiCreatorPresenter, IRepoCreatorPresenter {

    private static final String TAG = "CreatorPresenter";

    private EditorApi editorApi;

    private static Integer Id = null;

    @Inject
    public CreatorPresenter(BookRepository bookRepo, EditorApi editorApi) {
        super(bookRepo);
        this.editorApi = editorApi;
    }

    @Override
    public void ApiSetBookId(Integer id) {
        CreatorPresenter.Id = id;
        if (id != null)
            ApiGetBook();
    }

    @Override
    public void RepoSetBookId(Integer id) {
        CreatorPresenter.Id = id;
        if (id != null)
            RepoGetBook();
    }

    @Override
    public void RepoSaveBook(Book book) {
        book.setId(Id);

        if (Id == null) {
            RepoCreateBook(book);
        } else {
            RepoUpdateBook(book);
        }
    }

    @Override
    public void ApiSaveBook(Book book) {
        book.setId(Id);

        if (Id == null) {
            ApiCreateBook(book);
        } else {
            ApiUpdateBook(book);
        }
    }

    private void ApiCreateBook(Book book) {

        Call<Book> call = editorApi.postBook(book);

        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {

                if (!response.isSuccessful()) {
                    screen.DisplayException("Code " + response.code());
                    return;
                }

                screen.BookCreated(response.body().getId());
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                screen.DisplayException(t.getMessage());
            }
        });
    }

    private void ApiUpdateBook(Book book) {
        Call<Book> call = editorApi.updateBook(book);

        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {

                if (!response.isSuccessful()) {
                    screen.DisplayException("Code " + response.code());
                    return;
                }

                screen.BookUpdated(response.body().getId());
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                screen.DisplayException(t.getMessage());
            }
        });
    }

    private void RepoCreateBook(Book book) {
        bookRepo.insert(book, id -> screen.BookCreated(id));
    }

    private void RepoUpdateBook(Book book) {
        bookRepo.update(book, r -> screen.BookUpdated(book.getId()));
    }


    @Override
    public void ApiGetBook() {
        Call<Book> call = editorApi.getDetails(Id);

        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {

                if (!response.isSuccessful()) {
                    screen.DisplayException("Code " + response.code());
                    return;
                }
                screen.SetBook(response.body());
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                screen.DisplayException(t.getMessage());
            }
        });
    }

    @Override
    public void RepoGetBook() {
        bookRepo.getBook(Id, book -> screen.SetBook(book));
    }
}
