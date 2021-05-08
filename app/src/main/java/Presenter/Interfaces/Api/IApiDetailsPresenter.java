package Presenter.Interfaces.Api;

import Model.Book;
import Presenter.Interfaces.IPresenterBase;
import Screens.Interaces.IDetailsScreen;

public interface IApiDetailsPresenter extends IPresenterBase<IDetailsScreen> {

    Integer GetBookId();

    void SetBookId(int id);

    void ApiGetBook();

    void ApiDeleteBook();
}
