package geek.example.rainicorn.di.component;

import javax.inject.Singleton;

import dagger.Component;
import geek.example.rainicorn.di.module.EventBusModule;
import geek.example.rainicorn.presenter.SearchPresenter;
import geek.example.rainicorn.views.MainActivity;

@Singleton
@Component(modules = {EventBusModule.class})
public interface EventBusComponent {
    void injectMainActivity(MainActivity mainActivity);
    void injectSearchPresenter(SearchPresenter searchPresenter);
}
