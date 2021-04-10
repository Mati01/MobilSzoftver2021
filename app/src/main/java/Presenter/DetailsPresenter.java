package Presenter;

import Screens.Interaces.IDetailsScreen;

public class DetailsPresenter{

    protected static IDetailsScreen screen;


    public static void AttachScreen(IDetailsScreen screen) {
        DetailsPresenter.screen = screen;
    }

    public static void DetachScreen() {
        DetailsPresenter.screen = null;
    }
}
