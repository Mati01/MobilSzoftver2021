package Presenter.Classes;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;

import javax.inject.Inject;

import Model.Book;
import Model.Booklet;
import Presenter.Interfaces.IDetailsPresenter;
import Screens.Interaces.IDetailsScreen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import swagger.api.DetailsApi;

public class DetailsPresenter extends PresenterBase<IDetailsScreen> implements IDetailsPresenter {

    private static  final String TAG = "DetailsPresenter";

    private DetailsApi detailsApi;

    private Book book;

    @Inject
    public DetailsPresenter(DetailsApi detailsApi) {
        this.detailsApi = detailsApi;
        book = null;
    }

    @Override
    public void GetBook(int id) {
        Call<Book> call = detailsApi.getDetails(id);

        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {

                if (!response.isSuccessful()) {
                    screen.DisplayException("Code " + response.code());
                    return;
                }

                book = response.body();

                screen.SetBook(book);
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                screen.DisplayException(t.getMessage());
            }
        });
    }

    @Override
    public void DeleteBook() {
        Call<Void> call = detailsApi.deleteBook(book.getId());

        call.enqueue(new Callback<Void>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    screen.DisplayException("Code " + response.code());
                    return;
                }

                book = null;

                screen.BookDeleted();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                screen.DisplayException(t.getMessage());
            }
        });
    }
}
