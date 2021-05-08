package Presenter.Interfaces.Repo;

import Presenter.Interfaces.IPresenterBase;
import Screens.Interaces.IDetailsScreen;

public interface IRepoDetailsPresenter extends IPresenterBase<IDetailsScreen> {

    Integer GetBookId();

    void SetBookId(int id);

    void RepoGetBook();

    void RepoDeleteBook();
}
