package Presenter.Interfaces.Repo;

import Presenter.Interfaces.IPresenterBase;
import Screens.Interaces.ILibraryScreen;

public interface IRepoLibraryPresenter extends IPresenterBase<ILibraryScreen> {

    void RepoGetBooklets(String title, String author);

    void RepoDeleteBook(int id);
}
