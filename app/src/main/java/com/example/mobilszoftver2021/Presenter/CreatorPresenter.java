package com.example.mobilszoftver2021.Presenter;

import com.example.mobilszoftver2021.Screens.Interaces.ICreatorScreen;

public class CreatorPresenter{

    protected static  ICreatorScreen screen;

    public static void AttachScreen(ICreatorScreen screen) {
        CreatorPresenter.screen = screen;
    }

    public static void DetachScreen() {
        CreatorPresenter.screen = null;
    }
}
