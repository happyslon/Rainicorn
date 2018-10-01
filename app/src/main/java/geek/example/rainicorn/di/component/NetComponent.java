package geek.example.rainicorn.di.component;

import dagger.Component;
import geek.example.rainicorn.di.module.NetModule;
import geek.example.rainicorn.presenter.SearchPresenter;

@Component(modules = {NetModule.class})
public interface NetComponent {
//    void injectsToSearchPresenter(SearchPresenter searchPresenter);
}
