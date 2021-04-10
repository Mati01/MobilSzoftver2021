package Screens.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import Presenter.LibraryPresenter;
import com.example.mobilszoftver2021.R;
import Screens.Interaces.ILibraryScreen;

public class LibraryActivity extends AppCompatActivity implements ILibraryScreen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LibraryPresenter.AttachScreen(this);
    }

    @Override
    protected void  onStop() {
        super.onStop();
        LibraryPresenter.DetachScreen();
    }
}