package geek.example.rainicorn.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import geek.example.rainicorn.MainApplication;
import geek.example.rainicorn.data.database.realm.DataSource;
import geek.example.rainicorn.data.models.RealmModel;
import geek.example.rainicorn.data.models.gallery.GalleryItem;
import geek.example.rainicorn.data.models.gallery.Photo;
import geek.example.rainicorn.data.rest.NetApiClient;
import geek.example.rainicorn.di.component.DataComponent;
import geek.example.rainicorn.di.component.EventBusComponent;
import geek.example.rainicorn.utils.RxEventBus;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


@InjectViewState
public class SearchPresenter extends MvpPresenter<SearchView> implements Subscriber<GalleryItem>{
//    EventBusComponent eventBusComponent;
//    CompositeDisposable disposables;
//    DataComponent dataComponent;
//    @Inject
//    DataSource dataSource;
//    @Inject
//    RxEventBus rxEventBus;
    @SuppressLint("CheckResult")
    @Override
    public void attachView(SearchView view) {
//        dataComponent = MainApplication.getComponent();
//        dataComponent.dataComponent(this);
//        disposables = new CompositeDisposable();
//        eventBusComponent = MainApplication.getEventBusComponent();
//        eventBusComponent.injectSearchPresenter(this);
//        disposables.add(
//        rxEventBus.getEvents()
//                .subscribeOn(Schedulers.io())
//                .debounce(5L,TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new DisposableObserver<String>() {
//                    @Override
//                    public void onNext(String s) {
//                        loadDateForText(s);
////                        getViewState().sendMessage(s);
//                        Log.d("sendMessage",s);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                }));
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        getViewState().sendMessage(s);
//                    }
//                });
        super.attachView(view);
        loadDate();
    }

    private void loadDateForText(String s) {
        NetApiClient.getInstance().getGallerySearch(s)
                .subscribe(this);
    }

    @Override
    public void detachView(SearchView view) {
//        disposables.dispose();
        super.detachView(view);
    }

    @Override
    public void onDestroy() {
//        disposables.dispose();
        super.onDestroy();
    }

    private void loadDate() {
        getViewState().startLoad();
        NetApiClient.getInstance().getGallery()
                .subscribe(this);

    }


    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onNext(GalleryItem galleryItems) {
        getViewState().setPhotos(galleryItems.getPhotos().getPhoto());
//        List<Photo> modelList = galleryItems.getPhotos().getPhoto();
//        dataSource.saveAllRealm(modelList)
//        .subscribeWith(new DisposableCompletableObserver() {
//                    @Override
//                    public void onComplete() {
//                        getPhotoRealm();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        getViewState().showError(e);
//                    }
//                });
    }


    @Override
    public void onError(Throwable e) {
        getViewState().showError(e);
    }

    @Override
    public void onComplete() {
        getViewState().finishLoad();
    }

    @SuppressLint("CheckResult")
    public void getPhotoRealm() {
//        dataSource.readAllRealm()
//                .subscribeWith(new DisposableSingleObserver<List<RealmModel>>() {
//                    @Override
//                    public void onSuccess(List<RealmModel> realmList) {
//                        getViewState().setPhotos(realmList);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        getViewState().showError(e);
//                    }
//                });

    }
}
