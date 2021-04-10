package Dagger;

import Presenter.Classes.*;
import Presenter.Interfaces.*;
import dagger.Module;
import dagger.Provides;

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
}
