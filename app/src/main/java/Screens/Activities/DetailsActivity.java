package Screens.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Dagger.DaggerPresenterComponent;
import Dagger.PresenterComponent;
import Model.Book;

import com.example.mobilszoftver2021.R;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import Presenter.Interfaces.Repo.IRepoDetailsPresenter;
import Screens.Application.MyApplication;
import Screens.Interaces.IDetailsScreen;

public class DetailsActivity extends AppCompatActivity implements IDetailsScreen {

    @Inject
    IRepoDetailsPresenter presenter;

    ImageView detailsImage;
    TextView titleView;
    TextView subTytleView;
    TextView authorView;
    TextView releaseDateView;
    TextView detailsView;
    TextView availibilityView;

    Button deleteButton;
    Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        PresenterComponent component = DaggerPresenterComponent.create();
        component.inject(this);

        detailsImage = findViewById(R.id.details_image);
        titleView = findViewById(R.id.details_title_text);
        subTytleView = findViewById(R.id.details_subtitle_text);
        authorView = findViewById(R.id.details_author_text);
        releaseDateView = findViewById(R.id.details_createdate_text);
        detailsView = findViewById(R.id.details_details_text);
        availibilityView = findViewById(R.id.details_availiblity_text);

        deleteButton = findViewById(R.id.details_delete_button);
        deleteButton.setOnClickListener(v -> presenter.RepoDeleteBook());
        editButton = findViewById(R.id.details_edit_button);
        editButton.setOnClickListener(v -> {
            Intent intent = new Intent(DetailsActivity.this, CreatorActivity.class);
            intent.putExtra("Id", presenter.GetBookId());
            DetailsActivity.this.startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.presenter.AttachScreen(this);

        if(getIntent().hasExtra("Id")){
            this.presenter.SetBookId(getIntent().getIntExtra("Id", 1));
        }

        this.presenter.RepoGetBook();
    }

    @Override
    protected void  onStop() {
        super.onStop();
        this.presenter.DetachScreen();
    }

    @Override
    public void DisplayException(String message) {
        Toast.makeText(MyApplication.getAppContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void SetBook(Book book) {
        titleView.setText(book.getTitle());
        subTytleView.setText(book.getSubtitle());
        authorView.setText(book.getAuthor());
        releaseDateView.setText(new SimpleDateFormat("yyyy-mm-dd").format(book.getReleaseDate()));
        detailsView.setText(book.getDescription());
        availibilityView.setText(book.getOrderInfo().toString());
    }

    @Override
    public void BookDeleted() {
        finish();
    }
}