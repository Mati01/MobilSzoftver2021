package Presenter.Interfaces.Api;

import Model.Book;
import Presenter.Interfaces.IPresenterBase;
import Screens.Interaces.ICreatorScreen;

public interface IApiCreatorPresenter extends IPresenterBase<ICreatorScreen> {

    void ApiSetBookId(Integer id);

    void ApiSaveBook(Book book);

    void ApiGetBook();
}
