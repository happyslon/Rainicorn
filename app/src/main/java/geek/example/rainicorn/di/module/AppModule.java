package geek.example.rainicorn.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Application app;

    AppModule(Application app){
        this.app = app;
    }

    @Provides
    public Context provideAppContext(){
        return app;
    }
}
