package com.example.mobilszoftver2021.Screens.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mobilszoftver2021.Presenter.CreatorPresenter;
import com.example.mobilszoftver2021.Presenter.DetailsPresenter;
import com.example.mobilszoftver2021.R;
import com.example.mobilszoftver2021.Screens.Interaces.ICreatorScreen;

public class CreatorActivity extends AppCompatActivity implements ICreatorScreen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creator);
    }

    @Override
    protected void onStart() {
        super.onStart();
        CreatorPresenter.AttachScreen(this);
    }

    @Override
    protected void  onStop() {
        super.onStop();
        CreatorPresenter.DetachScreen();
    }
}