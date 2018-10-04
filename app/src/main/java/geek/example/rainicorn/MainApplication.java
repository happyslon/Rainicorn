package geek.example.rainicorn;

import android.app.Application;


import geek.example.rainicorn.di.component.DaggerNetComponent;
import geek.example.rainicorn.di.component.NetComponent;
import geek.example.rainicorn.di.module.NetModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainApplication extends Application {
    private static NetComponent component;
    public static NetComponent getComponent(){
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
        component = buildComponent();
    }

    private NetComponent buildComponent() {
        return DaggerNetComponent.builder()
                .netModule(new NetModule())
                .build();
    }
}
