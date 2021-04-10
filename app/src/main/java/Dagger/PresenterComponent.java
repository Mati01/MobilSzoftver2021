package Dagger;

import Screens.Activities.CreatorActivity;
import Screens.Activities.DetailsActivity;
import Screens.Activities.LibraryActivity;
import dagger.Component;

@Component (modules = PresenterModule.class)
public interface PresenterComponent {

    void inject(LibraryActivity libraryActivity);

    void inject(CreatorActivity creatorActivity);

    void inject(DetailsActivity detailsActivity);
}
