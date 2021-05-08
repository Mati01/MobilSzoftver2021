package Screens.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import Dagger.DaggerPresenterComponent;
import Dagger.PresenterComponent;
import Model.Booklet;

import com.example.mobilszoftver2021.R;

import java.util.List;

import javax.inject.Inject;

import Presenter.Interfaces.Repo.IRepoLibraryPresenter;
import Screens.Adapters.BookletAdapter;
import Screens.Application.MyApplication;
import Screens.Interaces.ILibraryScreen;

public class LibraryActivity extends AppCompatActivity implements ILibraryScreen {

    @Inject
    IRepoLibraryPresenter presenter;
    RecyclerView recyclerView;
    BookletAdapter bookletAdapter;

    SearchView titleSearchView;
    SearchView authorSearchView;

    Button newButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        PresenterComponent component = DaggerPresenterComponent.create();
        component.inject(this);

        recyclerView = findViewById(R.id.library_recyclerView);
        titleSearchView = findViewById(R.id.library_title_search);
        authorSearchView = findViewById(R.id.library_author_search);
        newButton = findViewById(R.id.Library_new_button);

        bookletAdapter = new BookletAdapter(this );
        new ItemTouchHelper(itemtTouchHelperCallback).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(bookletAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int titleSearchPlateId = titleSearchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        EditText titleSearchPlate = titleSearchView.findViewById(titleSearchPlateId);
        titleSearchPlate.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                presenter.RepoGetBooklets(titleSearchView.getQuery().toString(), authorSearchView.getQuery().toString());
            }
            return false;
        });

        int authorSearchPlateId = authorSearchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        EditText authorSearchPlate = authorSearchView.findViewById(authorSearchPlateId);
        authorSearchPlate.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                presenter.RepoGetBooklets(titleSearchView.getQuery().toString(), authorSearchView.getQuery().toString());
            }
            return false;
        });

        newButton.setOnClickListener(v -> {
            Intent intent = new Intent(LibraryActivity.this, CreatorActivity.class);
            LibraryActivity.this.startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.presenter.AttachScreen(this);
        this.presenter.RepoGetBooklets("", "");
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
    public void SetBooklets(List<Booklet> booklets) {
       bookletAdapter.SetBooklets(booklets);
    }

    @Override
    public void RemoveBooklet(int id) {

    }

    ItemTouchHelper.SimpleCallback itemtTouchHelperCallback =
            new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    int position = viewHolder.getAdapterPosition();
                    int id = bookletAdapter.booklets.get(position).getId();
                    presenter.RepoDeleteBook(id);
                    bookletAdapter.Remove(position);
                }
            };
}