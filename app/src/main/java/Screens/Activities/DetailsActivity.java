package Screens.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import Dagger.DaggerPresenterComponent;
import Dagger.PresenterComponent;
import Model.Book;
import Presenter.Interfaces.IDetailsPresenter;

import com.example.mobilszoftver2021.R;

import javax.inject.Inject;

import Screens.Interaces.IDetailsScreen;

public class DetailsActivity extends AppCompatActivity implements IDetailsScreen {

    @Inject
    IDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creator);

        PresenterComponent component = DaggerPresenterComponent.create();
        component.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.presenter.AttachScreen(this);
    }

    @Override
    protected void  onStop() {
        super.onStop();
        this.presenter.DetachScreen();
    }

    @Override
    public void DisplayException(String message) {

    }

    @Override
    public void SetBook(Book book) {

    }

    @Override
    public void BookDeleted() {

    }
}