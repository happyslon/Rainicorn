package geek.example.rainicorn.di.component;

import dagger.Component;
import geek.example.rainicorn.di.module.DataModule;
import geek.example.rainicorn.presenter.SearchPresenter;

@Component(modules = {DataModule.class})
public interface DataComponent {
    void dataComponent(SearchPresenter searchPresenter);
}
