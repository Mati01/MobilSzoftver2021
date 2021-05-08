package DataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import Model.Book;

@Database(entities = {Book.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class BookDatabase extends RoomDatabase {

    public   static BookDatabase instance;

    public  abstract BookDao bookDao();

    public  static  synchronized  BookDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BookDatabase.class, "book_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private  static RoomDatabase.Callback roomCallback = new  RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private BookDao bookDao;

        private  PopulateDbAsyncTask(BookDatabase db) {
            bookDao = db.bookDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            bookDao.insert(new Book("test1", "", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "TestAuthor1", "TestDescription1", "", Book.OrderInfoEnum.NONE));
            bookDao.insert(new Book("test2", "", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "TestAuthor1", "TestDescription2", "", Book.OrderInfoEnum.NONE));
            bookDao.insert(new Book("test3", "", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "TestAuthor2", "TestDescription3", "", Book.OrderInfoEnum.NONE));
            bookDao.insert(new Book("test4", "", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "TestAuthor2", "TestDescription4", "", Book.OrderInfoEnum.NONE));
            return null;
        }
    }
}
