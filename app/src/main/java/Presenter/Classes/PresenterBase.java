package Presenter.Classes;

import javax.inject.Inject;

import DataBase.BookRepository;
import Presenter.Interfaces.IPresenterBase;

import Screens.Interaces.IScreenBase;

public abstract class PresenterBase<T extends IScreenBase> implements IPresenterBase<T> {

    protected static BookRepository bookRepo;
    protected T screen;

    public PresenterBase(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void AttachScreen(T screen) {
        this.screen = screen;
    }

    public void DetachScreen() {
        this.screen = null;
    }
}
