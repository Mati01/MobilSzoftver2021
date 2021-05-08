package Presenter.Classes;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

import DataBase.BookRepository;
import Model.Book;
import Model.Booklet;
import Presenter.Interfaces.Api.IApiLibraryPresenter;
import Presenter.Interfaces.Repo.IRepoLibraryPresenter;
import Screens.Interaces.ILibraryScreen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import swagger.api.LibraryApi;

public class LibraryPresenter extends PresenterBase<ILibraryScreen> implements IApiLibraryPresenter, IRepoLibraryPresenter {

    private static final String TAG = "LibraryPresenter";

    private LibraryApi libraryApi;

    @Inject
    public LibraryPresenter(BookRepository bookRepo, LibraryApi libraryApi) {
        super(bookRepo);
        this.libraryApi = libraryApi;
    }

    @Override
    public void ApiGetBooklets(String title, String author) {
        Call<List<Booklet>> call = libraryApi.getBooklets(title, author);

        call.enqueue(new Callback<List<Booklet>>() {
            @Override
            public void onResponse(Call<List<Booklet>> call, Response<List<Booklet>> response) {
                if (!response.isSuccessful()) {
                    screen.DisplayException("Code " + response.code());
                    return;
                }
                screen.SetBooklets(response.body());
            }

            @Override
            public void onFailure(Call<List<Booklet>> call, Throwable t) {
                screen.DisplayException(t.getMessage());
            }
        });
    }

    @Override
    public void ApiDeleteBook(int id) {
        Call<Void> call = libraryApi.deleteBooklet(id);

        call.enqueue(new Callback<Void>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    screen.DisplayException("Code " + response.code());
                    return;
                }
                screen.RemoveBooklet(id);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                screen.DisplayException(t.getMessage());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void RepoGetBooklets(String title, String author) {
        bookRepo.getBooklets("%" + title +"%", "%" + author + "%", booklets -> screen.SetBooklets(booklets));
    }

    @Override
    public void RepoDeleteBook(int id) {
        bookRepo.delete(id, Void -> screen.RemoveBooklet(id));
    }
}
