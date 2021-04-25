package Presenter.Classes;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import Model.Booklet;
import Presenter.Interfaces.ILibraryPresenter;
import Screens.Interaces.ILibraryScreen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import swagger.api.LibraryApi;

public class LibraryPresenter extends PresenterBase<ILibraryScreen> implements ILibraryPresenter {

    private static final String TAG = "LibraryPresenter";

    private LibraryApi libraryApi;

    private List<Booklet> booklets;

    @Inject
    public LibraryPresenter(LibraryApi libraryApi) {
        this.libraryApi = libraryApi;
        booklets = new ArrayList<Booklet>();
    }

    @Override
    public void GetBooklets(String title, String author) {
        Call<List<Booklet>> call = libraryApi.getBooklets(title, author);

        call.enqueue(new Callback<List<Booklet>>() {
            @Override
            public void onResponse(Call<List<Booklet>> call, Response<List<Booklet>> response) {

                if (!response.isSuccessful()) {
                    screen.DisplayException("Code " + response.code());
                    return;
                }

                booklets = response.body();

                screen.SetBooklets(booklets);
            }

            @Override
            public void onFailure(Call<List<Booklet>> call, Throwable t) {
                screen.DisplayException(t.getMessage());
            }
        });
    }

    @Override
    public void DeleteBook(int id) {
        Call<Void> call = libraryApi.deleteBooklet(id);

        call.enqueue(new Callback<Void>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    screen.DisplayException("Code " + response.code());
                    return;
                }

                booklets.removeIf(x -> x.getId().equals(id));

                screen.RemoveBooklet(id);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                screen.DisplayException(t.getMessage());
            }
        });
    }
}
