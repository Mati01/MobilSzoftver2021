package Screens.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import Dagger.DaggerPresenterComponent;
import Dagger.PresenterComponent;

import com.example.mobilszoftver2021.R;

import javax.inject.Inject;

import Presenter.Interfaces.ICreatorPresenter;
import Screens.Interaces.ICreatorScreen;

public class CreatorActivity extends AppCompatActivity implements ICreatorScreen {

    @Inject
    ICreatorPresenter presenter;

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
}