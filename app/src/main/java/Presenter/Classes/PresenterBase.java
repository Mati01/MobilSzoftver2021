package Presenter.Classes;

import Presenter.Interfaces.IPresenterBase;

import Screens.Interaces.IScreenBase;

public abstract class PresenterBase<T extends IScreenBase> implements IPresenterBase<T> {

    protected T screen;

    public void AttachScreen(T screen) {
        this.screen = screen;
    }

    public void DetachScreen() {
        this.screen = null;
    }
}
