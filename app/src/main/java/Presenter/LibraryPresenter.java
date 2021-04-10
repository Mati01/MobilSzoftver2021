package Presenter;

import Screens.Interaces.ILibraryScreen;

public class LibraryPresenter{

    protected static ILibraryScreen screen;

    public static void AttachScreen(ILibraryScreen screen) {
        LibraryPresenter.screen = screen;
    }

    public static void DetachScreen() {
        LibraryPresenter.screen = null;
    }
}
