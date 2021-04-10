package Presenter.Interfaces;

import Screens.Interaces.IScreenBase;

public interface IPresenterBase<T extends IScreenBase> {

    void AttachScreen(T screen);

    void DetachScreen();
}
