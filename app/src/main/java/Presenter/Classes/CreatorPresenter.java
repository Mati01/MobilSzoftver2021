package Presenter.Classes;

import javax.inject.Inject;

import Model.BookDetail;
import Presenter.Interfaces.ICreatorPresenter;
import Screens.Interaces.ICreatorScreen;

public class CreatorPresenter extends PresenterBase<ICreatorScreen> implements ICreatorPresenter {

    private static  final String TAG = "CreatorPresenter";

    @Inject
    public CreatorPresenter() {

    }

    @Override
    public Boolean CreateBook(BookDetail newBook) {
        throw new UnsupportedOperationException();
    }
}
