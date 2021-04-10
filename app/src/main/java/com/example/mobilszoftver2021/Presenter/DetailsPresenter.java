package com.example.mobilszoftver2021.Presenter;

import com.example.mobilszoftver2021.Screens.Interaces.ICreatorScreen;
import com.example.mobilszoftver2021.Screens.Interaces.IDetailsScreen;

public class DetailsPresenter{

    protected static IDetailsScreen screen;


    public static void AttachScreen(IDetailsScreen screen) {
        DetailsPresenter.screen = screen;
    }

    public static void DetachScreen() {
        DetailsPresenter.screen = null;
    }
}
