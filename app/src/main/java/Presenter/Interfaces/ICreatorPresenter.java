package Presenter.Interfaces;

import Model.Book;
import Screens.Interaces.ICreatorScreen;

public interface ICreatorPresenter extends IPresenterBase<ICreatorScreen> {

    void CreateBook(Book book);

    void UpdateBook(Book book);
}
