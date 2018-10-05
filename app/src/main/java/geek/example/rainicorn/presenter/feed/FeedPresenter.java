package geek.example.rainicorn.presenter.feed;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

import geek.example.rainicorn.data.database.realm.RealmHelper;
import geek.example.rainicorn.data.database.realm.usecases.FeedUsecases;
import geek.example.rainicorn.data.models.RealmModel;
import geek.example.rainicorn.data.models.view.FeedViewModel;
import io.realm.Realm;
import io.realm.RealmResults;

@InjectViewState
public class FeedPresenter extends MvpPresenter<FeedView> {
    Realm realm;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        realm = RealmHelper.newRealmInstance();
        update();
    }

    @Override
    public void attachView(FeedView view) {
        super.attachView(view);
        setData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void update() {
        new FeedUsecases();
    }

    private void setData() {
        RealmResults<RealmModel> feed = realm.where(RealmModel.class).findAll();
        ArrayList<FeedViewModel> result = new ArrayList<FeedViewModel>();
        for (RealmModel curItem : feed) {
            result.add(new FeedViewModel(curItem));
        }
        getViewState().setItems(result);
    }

}
