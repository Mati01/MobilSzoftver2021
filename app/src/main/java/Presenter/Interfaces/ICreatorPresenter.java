package Presenter.Interfaces;

import Model.BookDetail;
import Screens.Interaces.ICreatorScreen;

public interface ICreatorPresenter extends IPresenterBase<ICreatorScreen> {

    Boolean CreateBook(BookDetail newBook);
}
