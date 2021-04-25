package DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;

import java.util.List;

import Model.Book;
import Model.Booklet;

@Dao
public interface BookDao {

    @Insert
    void insert(Book book);

    @Update
    void update(Book book);

    @Query("DELETE FROM " + Book.tableName + " WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM " + Book.tableName +
            " WHERE (:title IS NULL OR Title like :title)" +
            " and (:author IS NULL OR Author like :author)")
    List<Booklet> getBooklets(String title, String author);

    @Query("SELECT * FROM " + Book.tableName + " ORDER BY id ASC")
    List<Book> getAllBooks();

    @Query("SELECT * FROM " + Book.tableName + " WHERE id = :id")
    Book getBookById(int id);
}
