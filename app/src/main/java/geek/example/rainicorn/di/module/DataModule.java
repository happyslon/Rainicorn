package geek.example.rainicorn.di.module;

import dagger.Module;
import dagger.Provides;
import geek.example.rainicorn.data.database.realm.DataSource;


@Module
public class DataModule {
    @Provides
    public DataSource provideSaveAllRealm(){
        return new DataSource();
    }

}
