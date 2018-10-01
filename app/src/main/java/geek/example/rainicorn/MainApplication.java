package geek.example.rainicorn;

import android.app.Application;

import geek.example.rainicorn.di.component.DaggerDataComponent;
import geek.example.rainicorn.di.component.DaggerEventBusComponent;
import geek.example.rainicorn.di.component.DataComponent;
import geek.example.rainicorn.di.component.EventBusComponent;
import geek.example.rainicorn.di.component.NetComponent;
import geek.example.rainicorn.di.module.DataModule;
import geek.example.rainicorn.di.module.EventBusModule;
import geek.example.rainicorn.di.module.NetModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainApplication extends Application {

    private static DataComponent component;
    private static EventBusComponent eventBusComponent;
//    private static NetComponent netComponent;

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
        eventBusComponent = DaggerEventBusComponent
                .builder()
                .eventBusModule(new EventBusModule())
                .build();


    }
    public static DataComponent getComponent() {
        return component;
    }
    public static EventBusComponent getEventBusComponent(){
        return eventBusComponent;
    }

}
