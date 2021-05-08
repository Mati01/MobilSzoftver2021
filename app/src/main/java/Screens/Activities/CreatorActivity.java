package Screens.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import Dagger.DaggerPresenterComponent;
import Dagger.PresenterComponent;

import com.example.mobilszoftver2021.R;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import Model.Book;
import Presenter.Interfaces.Repo.IRepoCreatorPresenter;
import Screens.Application.MyApplication;
import Screens.Interaces.ICreatorScreen;

public class CreatorActivity extends AppCompatActivity implements ICreatorScreen {

    @Inject
    IRepoCreatorPresenter presenter;

    TextInputLayout titleDateView;
    TextInputLayout subTitleDateView;
    TextInputLayout authorDateView;
    TextInputLayout createDateView;
    TextInputLayout descriptionDateView;
    TextInputLayout availibilityView;
    Button acceptButton;
    DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creator);

        PresenterComponent component = DaggerPresenterComponent.create();
        component.inject(this);

        titleDateView = findViewById(R.id.creator_title_input);
        subTitleDateView = findViewById(R.id.creator_subtitle_input);
        authorDateView = findViewById(R.id.creator_author_input);
        createDateView = findViewById(R.id.creator_createdate_input);
        descriptionDateView = findViewById(R.id.creator_description_input);
        availibilityView = findViewById(R.id.creator_avaliability_input);
        acceptButton = findViewById(R.id.creator_accept_button);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean valid = true;
                if (TextUtils.isEmpty(titleDateView.getEditText().getText())){
                    titleDateView.getEditText().setError("Title can't be empty");
                    valid = false;
                }
                if (TextUtils.isEmpty(authorDateView.getEditText().getText())){
                    authorDateView.getEditText().setError("Author can't be empty");
                    valid = false;
                }
                if (TextUtils.isEmpty(createDateView.getEditText().getText())){
                    createDateView.getEditText().setError("Date can't be empty");
                    valid = false;
                }
                if (TextUtils.isEmpty(availibilityView.getEditText().getText())){
                    availibilityView.getEditText().setError("Avaibility can't be empty");
                    valid = false;
                }

                if(valid) {
                    try {
                        presenter.RepoSaveBook(new Book(
                                titleDateView.getEditText().getText().toString(),
                                subTitleDateView.getEditText().getText().toString(),
                                new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(createDateView.getEditText().getText().toString())),
                                authorDateView.getEditText().getText().toString(),
                                descriptionDateView.getEditText().getText().toString(),
                                "",
                                Book.OrderInfoEnum.fromString(availibilityView.getEditText().getText().toString())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        String[] availibilities = getResources().getStringArray(R.array.avaibilities);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(CreatorActivity.this,R.layout.dropdown_item, availibilities);
        ((AutoCompleteTextView)availibilityView.getEditText()).setAdapter(arrayAdapter);
        ((AutoCompleteTextView)availibilityView.getEditText()).setText(arrayAdapter.getItem(0));
        arrayAdapter.getFilter().filter(null);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        createDateView.getEditText().setOnFocusChangeListener((v, hasFocus) -> {
            if (v.isInTouchMode() && hasFocus)
                v.performClick();
        });

        createDateView.getEditText().setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    CreatorActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    dateSetListener, year, month, day);

            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                createDateView.getEditText().setText(date);
                createDateView.getEditText().setError(null);
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.presenter.AttachScreen(this);

        if(getIntent().hasExtra("Id")){
            this.presenter.RepoSetBookId(getIntent().getIntExtra("Id", 1));
            acceptButton.setText(R.string.update);
        }
    }

    @Override
    public void SetBook(Book book) {
        titleDateView.getEditText().setText(book.getTitle());
        subTitleDateView.getEditText().setText(book.getSubtitle());
        authorDateView.getEditText().setText(book.getAuthor());
        createDateView.getEditText().setText(new SimpleDateFormat("dd/MM/yyyy").format(book.getReleaseDate()));
        availibilityView.getEditText().setText(book.getOrderInfo().toString());
        descriptionDateView.getEditText().setText(book.getDescription());
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.presenter.DetachScreen();

        this.presenter.RepoSetBookId(null);
    }

    @Override
    public void DisplayException(String message) {
        Toast.makeText(MyApplication.getAppContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void BookCreated(int id) {
        finish();
    }

    @Override
    public void BookUpdated(int id) {
        finish();
    }
}