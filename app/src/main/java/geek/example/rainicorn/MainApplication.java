package geek.example.rainicorn;

import android.app.Application;

import geek.example.rainicorn.models.RxEventBus;

public class MainApplication extends Application {
    private RxEventBus bus;
    @Override
    public void onCreate() {
        super.onCreate();
        bus = RxEventBus.instanceOf();
    }
    public RxEventBus getBus(){
        return bus;
    }
}
