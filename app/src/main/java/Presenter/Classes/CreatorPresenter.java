package Presenter.Classes;

import javax.inject.Inject;

import Model.Book;
import Presenter.Interfaces.ICreatorPresenter;
import Screens.Interaces.ICreatorScreen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import swagger.api.EditorApi;

public class CreatorPresenter extends PresenterBase<ICreatorScreen> implements ICreatorPresenter {

    private static  final String TAG = "CreatorPresenter";

    private  EditorApi editorApi;

    @Inject
    public CreatorPresenter(EditorApi editorApi) {
        this.editorApi = editorApi;
    }

    @Override
    public void CreateBook(Book book) {
        book.setId(0);

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

    @Override
    public void UpdateBook(Book book) {
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
}
