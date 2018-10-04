package geek.example.rainicorn.di.component;

import dagger.Component;
import geek.example.rainicorn.data.database.realm.usecases.FeedUsecases;
import geek.example.rainicorn.di.module.NetModule;
import geek.example.rainicorn.presenter.owner.OwnerPresenter;
import geek.example.rainicorn.presenter.owner.OwnerProfilePresenter;
import geek.example.rainicorn.presenter.search.SearchPresenter;


@Component(modules = {NetModule.class})
public interface NetComponent {
    void injectsToSearchPresenter(SearchPresenter searchPresenter);
    void injectsToOwnerProfilePresenter(OwnerProfilePresenter ownerProfilePresenter);
    void injectsToOwnerPresenter(OwnerPresenter ownerPresenter);
    void injectsToFeedUsecases(FeedUsecases feedUsecases);
}
