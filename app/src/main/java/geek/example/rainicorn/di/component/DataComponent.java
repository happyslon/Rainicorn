package geek.example.rainicorn.di.component;

import dagger.Component;
import geek.example.rainicorn.di.module.DataModule;


@Component(modules = {DataModule.class})
public interface DataComponent {
    //void dataComponent(SearchPresenter searchPresenter);
}
