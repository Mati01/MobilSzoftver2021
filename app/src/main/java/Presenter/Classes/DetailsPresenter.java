package Presenter.Classes;

import android.os.Build;

import androidx.annotation.RequiresApi;

import javax.inject.Inject;

import DataBase.BookRepository;
import Model.Book;
import Presenter.Interfaces.Api.IApiDetailsPresenter;
import Presenter.Interfaces.Repo.IRepoDetailsPresenter;
import Screens.Interaces.IDetailsScreen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import swagger.api.DetailsApi;

public class DetailsPresenter extends PresenterBase<IDetailsScreen> implements IApiDetailsPresenter, IRepoDetailsPresenter {

    private static final String TAG = "DetailsPresenter";

    private DetailsApi detailsApi;

    private static Integer Id;

    @Override
    public final Integer GetBookId() {
        return DetailsPresenter.Id;
    }

    @Override
    public void SetBookId(int id) {
        DetailsPresenter.Id = id;
    }

    @Inject
    public DetailsPresenter(BookRepository bookRepo, DetailsApi detailsApi) {
        super(bookRepo);
        this.detailsApi = detailsApi;
    }

    @Override
    public void ApiGetBook() {
        Call<Book> call = detailsApi.getDetails(Id);

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
    public void ApiDeleteBook() {
        Call<Void> call = detailsApi.deleteBook(Id);

        call.enqueue(new Callback<Void>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    screen.DisplayException("Code " + response.code());
                    return;
                }
                screen.BookDeleted();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                screen.DisplayException(t.getMessage());
            }
        });
    }

    @Override
    public void RepoGetBook() {
        bookRepo.getBook(Id, book -> screen.SetBook(book));
    }

    @Override
    public void RepoDeleteBook() {
        bookRepo.delete(Id, Void -> screen.BookDeleted());
    }
}
