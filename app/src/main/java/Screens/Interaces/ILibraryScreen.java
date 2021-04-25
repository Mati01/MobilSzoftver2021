package Screens.Interaces;

import java.util.List;

import Model.Booklet;

public interface ILibraryScreen extends IScreenBase {

    void SetBooklets(List<Booklet> booklets);

    void RemoveBooklet(int id);
}
