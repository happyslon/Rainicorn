package geek.example.rainicorn.presenter.feed;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;

import geek.example.rainicorn.data.database.realm.usecases.FeedUsecases;
import geek.example.rainicorn.data.models.RealmModel;
import geek.example.rainicorn.data.models.view.FeedViewModel;
import io.reactivex.observers.DisposableCompletableObserver;
import io.realm.Realm;
import io.realm.RealmResults;

@InjectViewState
public class FeedPresenter extends MvpPresenter<FeedView>{
    Realm realm;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        realm = Realm.getDefaultInstance();
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


    @SuppressLint("CheckResult")
    private void update() {
        new FeedUsecases().getFeed()
                .subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {
                getViewState().showError(e);
            }
        });
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
