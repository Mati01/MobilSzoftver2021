package Presenter.Classes;

import javax.inject.Inject;

import Presenter.Interfaces.IDetailsPresenter;
import Screens.Interaces.IDetailsScreen;

public class DetailsPresenter extends PresenterBase<IDetailsScreen> implements IDetailsPresenter {

    private static  final String TAG = "DetailsPresenter";

    @Inject
    public DetailsPresenter() {

    }
}
