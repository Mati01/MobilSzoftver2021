package DataBase;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import Model.Book;
import Model.Booklet;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class BookDaoTest {

    private BookDatabase bookDatabase;
    private BookDao bookDao;


    @Before
    public void setUp() {
        bookDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                BookDatabase.class
        ).allowMainThreadQueries().build();
        bookDao = bookDatabase.bookDao();
    }

    @After
    public void tearDown() {
        bookDatabase.close();
    }

    @Test
    public void insert() {
        Book testBook = new Book("TestTitle", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);

        Long id = bookDao.insert(testBook);
        testBook.setId(Math.toIntExact(id));
        List<Book> books = bookDao.getAllBooks();

        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals(testBook, books.get(0));
    }

    @Test
    public void update() {
        Book testBook = new Book("TestTitle", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);

        Long id = bookDao.insert(testBook);
        testBook.setId(Math.toIntExact(id));
        testBook.setTitle("TestTitle2");

        bookDao.update(testBook);
        List<Book> books = bookDao.getAllBooks();

        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals(testBook, books.get(0));
    }

    @Test
    public void delete() {
        Book testBook = new Book("TestTitle", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);
        Long id = bookDao.insert(testBook);

        bookDao.delete(Math.toIntExact(id));
        List<Book> books = bookDao.getAllBooks();

        assertNotNull(books);
        assertEquals(0, books.size());
    }

    @Test
    public void getBooklets() {
        Book testBook1 = new Book("TestTitle1", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor1", "TestDescription", "", Book.OrderInfoEnum.NONE);
        Book testBook2 = new Book("TestTitle2", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor2", "TestDescription", "", Book.OrderInfoEnum.NONE);
        Book testBook3 = new Book("TestTitle3", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor2", "TestDescription", "", Book.OrderInfoEnum.NONE);

        bookDao.insert(testBook1);
        bookDao.insert(testBook2);
        bookDao.insert(testBook3);

        List<Booklet> booklets = bookDao.getBooklets("","");

        assertNotNull(booklets);
        assertEquals(3, booklets.size());
        assertEquals(testBook1.getTitle(), booklets.get(0).getTitle());
        assertEquals(testBook2.getTitle(), booklets.get(1).getTitle());
        assertEquals(testBook3.getTitle(), booklets.get(2).getTitle());

        booklets = bookDao.getBooklets("TestTitle1","");

        assertNotNull(booklets);
        assertEquals(1, booklets.size());
        assertEquals(testBook1.getTitle(), booklets.get(0).getTitle());

        booklets = bookDao.getBooklets("","TestAuthor2");

        assertNotNull(booklets);
        assertEquals(2, booklets.size());
        assertEquals(testBook2.getTitle(), booklets.get(0).getTitle());
        assertEquals(testBook3.getTitle(), booklets.get(1).getTitle());

    }

    @Test
    public void getAllBooks() {
        Book testBook1 = new Book("TestTitle1", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);
        Book testBook2 = new Book("TestTitle2", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);
        Book testBook3 = new Book("TestTitle3", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);

        bookDao.insert(testBook1);
        bookDao.insert(testBook2);
        bookDao.insert(testBook3);

        List<Book> books = bookDao.getAllBooks();

        assertNotNull(books);
        assertEquals(3, books.size());
        assertEquals(testBook1.getTitle(), books.get(0).getTitle());
        assertEquals(testBook2.getTitle(), books.get(1).getTitle());
        assertEquals(testBook3.getTitle(), books.get(2).getTitle());
    }

    @Test
    public void getBookById() {
        Book testBook1 = new Book("TestTitle1", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);
        Book testBook2 = new Book("TestTitle2", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);
        Book testBook3 = new Book("TestTitle3", "TestSubTitle", new Date(2020, 01, 01), "TestAuthor", "TestDescription", "", Book.OrderInfoEnum.NONE);

        Integer id = Math.toIntExact(bookDao.insert(testBook1));
        testBook1.setId(id);
        bookDao.insert(testBook2);
        bookDao.insert(testBook3);

        Book book = bookDao.getBookById(id);

        assertNotNull(book);
        assertEquals(testBook1, book);
    }
}