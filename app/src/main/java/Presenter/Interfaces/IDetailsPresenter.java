package Presenter.Interfaces;

import Model.BookDetail;
import Screens.Interaces.IDetailsScreen;

public interface IDetailsPresenter extends IPresenterBase<IDetailsScreen>{

    BookDetail GetBook(int id);
}
