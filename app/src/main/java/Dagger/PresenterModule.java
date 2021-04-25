package Dagger;

import javax.inject.Singleton;

import Presenter.Classes.*;
import Presenter.Interfaces.*;
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

    @Provides
    static ILibraryPresenter providesILibraryPresenter(LibraryPresenter libraryPresenter) {
        return libraryPresenter;
    }

    @Provides
    static ICreatorPresenter providesICreatorPresenter(CreatorPresenter creatorPresenter) {
        return creatorPresenter;
    }

    @Provides
    static IDetailsPresenter providesIDetailsPresenter(DetailsPresenter detailsPresenter) {
        return detailsPresenter;
    }

    @Provides
    static OkHttpClient providesOkHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder.build();
    }

    @Provides
    static Retrofit providesRetofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl("")
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


}
