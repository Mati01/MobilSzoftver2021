package Dagger;

import DataBase.BookRepository;
import Presenter.Classes.*;
import Presenter.Interfaces.Api.IApiCreatorPresenter;
import Presenter.Interfaces.Api.IApiDetailsPresenter;
import Presenter.Interfaces.Api.IApiLibraryPresenter;
import Presenter.Interfaces.Repo.IRepoCreatorPresenter;
import Presenter.Interfaces.Repo.IRepoDetailsPresenter;
import Presenter.Interfaces.Repo.IRepoLibraryPresenter;
import Screens.Application.MyApplication;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import swagger.api.DetailsApi;
import swagger.api.EditorApi;
import swagger.api.LibraryApi;

@Module
public class PresenterModule {

    private  static  BookRepository bookRepository;
    private static OkHttpClient okHttpClient;

    @Provides
    static IApiLibraryPresenter providesIApiLibraryPresenter(LibraryPresenter libraryPresenter) {
        return libraryPresenter;
    }

    @Provides
    static IApiCreatorPresenter providesIApiCreatorPresenter(CreatorPresenter creatorPresenter) {
        return creatorPresenter;
    }

    @Provides
    static IApiDetailsPresenter providesIApiDetailsPresenter(DetailsPresenter detailsPresenter) {
        return detailsPresenter;
    }

    @Provides
    static IRepoLibraryPresenter providesIRepoLibraryPresenter(LibraryPresenter libraryPresenter) {
        return libraryPresenter;
    }

    @Provides
    static IRepoCreatorPresenter providesIRepoCreatorPresenter(CreatorPresenter creatorPresenter) {
        return creatorPresenter;
    }

    @Provides
    static IRepoDetailsPresenter providesIRepoDetailsPresenter(DetailsPresenter detailsPresenter) {
        return detailsPresenter;
    }

    @Provides
    static OkHttpClient providesOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder().build();
        }

        return okHttpClient;
    }

    @Provides
    static Retrofit providesRetofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl("http://test-test.domainname.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    static LibraryApi providesLibraryApi(Retrofit retrofit){
        return retrofit.create(LibraryApi.class);
    }

    @Provides
    static DetailsApi providesDetailsApi(Retrofit retrofit){
        return retrofit.create(DetailsApi.class);
    }

    @Provides
    static EditorApi providesEditorApi(Retrofit retrofit){
        return retrofit.create(EditorApi.class);
    }

    @Provides
    static BookRepository providesBookRepository(){
        if (bookRepository == null){
            bookRepository = new BookRepository(MyApplication.getAppContext());
        }
       return bookRepository;
    }


}
