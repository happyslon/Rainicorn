package geek.example.rainicorn.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import geek.example.rainicorn.data.Endpoints;
import geek.example.rainicorn.data.rest.ServiceGenerator;
import geek.example.rainicorn.utils.RxEventBus;

@Module
public class EventBusModule {
    @Provides
    @Singleton
    public RxEventBus provideRxEventBus(){
        return RxEventBus.instanceOf();
    }
}
