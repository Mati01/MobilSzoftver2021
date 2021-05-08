package Screens.Application;

import android.app.Application;
import android.content.Context;

import Dagger.PresenterModule;
import DataBase.BookRepository;

public class MyApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}
