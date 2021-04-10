package com.example.mobilszoftver2021.Presenter;

import com.example.mobilszoftver2021.Screens.Interaces.IDetailsScreen;
import com.example.mobilszoftver2021.Screens.Interaces.ILibraryScreen;

public class LibraryPresenter{

    protected static ILibraryScreen screen;

    public static void AttachScreen(ILibraryScreen screen) {
        LibraryPresenter.screen = screen;
    }

    public static void DetachScreen() {
        LibraryPresenter.screen = null;
    }
}
