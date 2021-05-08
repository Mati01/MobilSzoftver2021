package Presenter.Interfaces.Api;

import Presenter.Interfaces.IPresenterBase;
import Screens.Interaces.ILibraryScreen;

public interface IApiLibraryPresenter extends IPresenterBase<ILibraryScreen> {

    void ApiGetBooklets(String title, String author);

    void ApiDeleteBook(int id);
}
