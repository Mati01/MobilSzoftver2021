package Presenter.Interfaces.Repo;

import Model.Book;
import Presenter.Interfaces.IPresenterBase;
import Screens.Interaces.ICreatorScreen;

public interface IRepoCreatorPresenter extends IPresenterBase<ICreatorScreen> {

    void RepoSetBookId(Integer id);

    void RepoSaveBook(Book book);

    void RepoGetBook();
}
