package Screens.Interaces;

import Model.Book;

public interface IDetailsScreen extends IScreenBase {

    void SetBook(Book book);

    void BookDeleted();
}
