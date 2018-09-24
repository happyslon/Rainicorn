package geek.example.rainicorn;

import android.app.Application;

import geek.example.rainicorn.di.component.DaggerDataComponent;
import geek.example.rainicorn.di.component.DataComponent;
import geek.example.rainicorn.di.module.DataModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainApplication extends Application {

    static DataComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
        component = DaggerDataComponent
                .builder()
                .dataModule(new DataModule())
                .build();

    }
    public static DataComponent getComponent() {
        return component;
    }


}
