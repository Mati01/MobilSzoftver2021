package DataBase;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.function.Consumer;

import Model.Book;
import Model.Booklet;

public class BookRepository {

    private BookDao bookDao;

    public BookRepository(Application application) {
        BookDatabase database = BookDatabase.getInstance(application);
        bookDao = database.bookDao();
    }

    public void insert(Book book) {
        new InsertBookAsyncTask(bookDao).execute(book);
    }

    public void update(Book book) {
        new UpdateBookAsyncTask(bookDao).execute(book);
    }

    public void delete(int id) {
        new DeleteBookAsyncTask(bookDao).execute(id);
    }

    public void getBook(int id, Consumer<Book> callback){
        new QuerBookyAsyncTask(bookDao, callback).execute(id);
    }

    public void getBooklets(String title, String author, Consumer<List<Booklet>> callback) {
        new QuerBookletsyAsyncTask(bookDao, callback).execute(title, author);
    }

    private static class InsertBookAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDao bookDao;

        public InsertBookAsyncTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDao.insert(books[0]);
            return null;
        }
    }

    private static class UpdateBookAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDao bookDao;

        public UpdateBookAsyncTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDao.update(books[0]);
            return null;
        }
    }

    private static class DeleteBookAsyncTask extends AsyncTask<Integer, Void, Void> {
        private BookDao bookDao;

        public DeleteBookAsyncTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Integer... ids) {
            bookDao.delete(ids[0]);
            return null;
        }
    }

    private static class QuerBookyAsyncTask extends  AsyncTask<Integer, Void, Book> {
        private BookDao bookDao;
        private Consumer<Book> callback = null;

        QuerBookyAsyncTask(BookDao dao, Consumer<Book> callback) {
            bookDao = dao;
            this.callback = callback;
        }

        @Override
        protected Book doInBackground(final Integer... params) {
            return bookDao.getBookById(params[0]);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(Book result) {
            callback.accept(result);
        }
    }

    private static class QuerBookletsyAsyncTask extends  AsyncTask<String, Void, List<Booklet>> {
        private BookDao bookDao;
        private Consumer<List<Booklet>> callback = null;

        QuerBookletsyAsyncTask(BookDao dao, Consumer<List<Booklet>> callback) {
            bookDao = dao;
            this.callback = callback;
        }

        @Override
        protected List<Booklet> doInBackground(final String... params) {
            return bookDao.getBooklets(params[0], params[1]);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(List<Booklet> result) {
            callback.accept(result);
        }
    }
}
