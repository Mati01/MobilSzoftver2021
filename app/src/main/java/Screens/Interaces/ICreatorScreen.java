package Screens.Interaces;

import Model.Book;

public interface ICreatorScreen extends IScreenBase {

    void BookCreated(int id);

    void BookUpdated(int id);

    void SetBook(Book book);
}
