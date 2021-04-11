package Presenter.Classes;

import java.util.List;

import javax.inject.Inject;

import Model.BookCard;
import Presenter.Interfaces.ILibraryPresenter;
import Screens.Interaces.ILibraryScreen;

public class LibraryPresenter extends PresenterBase<ILibraryScreen> implements ILibraryPresenter {

    private static  final String TAG = "LibraryPresenter";

    @Inject
    public LibraryPresenter() {

    }

    @Override
    public List<BookCard> GetBookCards(String searchCriteria) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean DeleteBookCard(int id) {
        throw new UnsupportedOperationException();
    }
}
