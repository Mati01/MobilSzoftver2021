package Presenter.Classes;

import javax.inject.Inject;

import Presenter.Interfaces.ILibraryPresenter;
import Screens.Interaces.ILibraryScreen;

public class LibraryPresenter extends PresenterBase<ILibraryScreen> implements ILibraryPresenter {

    private static  final String TAG = "LibraryPresenter";

    @Inject
    public LibraryPresenter() {

    }
}
