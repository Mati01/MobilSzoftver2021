package Presenter.Interfaces;

import java.util.List;

import Model.BookCard;
import Screens.Interaces.ILibraryScreen;

public interface ILibraryPresenter extends IPresenterBase<ILibraryScreen>{

    List<BookCard> GetBookCards(String searchCriteria);

    Boolean DeleteBookCard(int id);
}
