package Presenter.Interfaces;

import Model.Book;
import Screens.Interaces.IDetailsScreen;

public interface IDetailsPresenter extends IPresenterBase<IDetailsScreen>{

    void GetBook(int id);

    void DeleteBook();
}
