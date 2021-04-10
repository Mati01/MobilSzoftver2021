package Screens.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import Presenter.DetailsPresenter;

import com.example.mobilszoftver2021.R;
import Screens.Interaces.IDetailsScreen;

public class DetailsActivity extends AppCompatActivity implements IDetailsScreen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    @Override
    protected void onStart() {
        super.onStart();
        DetailsPresenter.AttachScreen(this);
    }

    @Override
    protected void  onStop() {
        super.onStop();
        DetailsPresenter.DetachScreen();
    }
}