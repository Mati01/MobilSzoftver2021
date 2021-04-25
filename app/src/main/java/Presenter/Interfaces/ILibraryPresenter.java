package Presenter.Interfaces;

import Screens.Interaces.ILibraryScreen;

public interface ILibraryPresenter extends IPresenterBase<ILibraryScreen>{

    void GetBooklets(String title, String author);

    void DeleteBook(int id);
}
