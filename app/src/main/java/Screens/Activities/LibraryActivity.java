package Screens.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import Dagger.DaggerPresenterComponent;
import Dagger.PresenterComponent;
import Model.Booklet;
import Presenter.Interfaces.ILibraryPresenter;
import com.example.mobilszoftver2021.R;

import java.util.List;

import javax.inject.Inject;

import Screens.Interaces.ILibraryScreen;

public class LibraryActivity extends AppCompatActivity implements ILibraryScreen {

    @Inject
    ILibraryPresenter presenter;

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
    public void SetBooklets(List<Booklet> booklets) {

    }

    @Override
    public void RemoveBooklet(int id) {

    }

    @Override
    public void DisplayException(String message) {

    }
}