package Presenter;

import Screens.Interaces.ICreatorScreen;

public class CreatorPresenter{

    protected static  ICreatorScreen screen;

    public static void AttachScreen(ICreatorScreen screen) {
        CreatorPresenter.screen = screen;
    }

    public static void DetachScreen() {
        CreatorPresenter.screen = null;
    }
}
