package geek.example.rainicorn.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import geek.example.rainicorn.MainApplication;
import geek.example.rainicorn.data.database.realm.DataSource;
import geek.example.rainicorn.data.models.RealmModel;
import geek.example.rainicorn.data.models.gallery.GalleryItem;
import geek.example.rainicorn.data.models.gallery.Photo;
import geek.example.rainicorn.data.rest.NetApiClient;
import geek.example.rainicorn.di.component.DataComponent;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;


@InjectViewState
public class SearchPresenter extends MvpPresenter<SearchView> implements Subscriber<GalleryItem>{
    DataComponent dataComponent;
    @Inject
    DataSource dataSource;
    @Override
    public void attachView(SearchView view) {
        dataComponent = MainApplication.getComponent();
        dataComponent.dataComponent(this);
        super.attachView(view);
        loadDate();
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
//        getViewState().setPhotos(galleryItems.getPhotos().getPhoto());
        List<Photo> modelList = galleryItems.getPhotos().getPhoto();
        dataSource.saveAllRealm(modelList)
        .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        getPhotoRealm();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showError(e);
                    }
                });
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
        dataSource.readAllRealm()
                .subscribeWith(new DisposableSingleObserver<List<RealmModel>>() {
                    @Override
                    public void onSuccess(List<RealmModel> realmList) {
                        getViewState().setPhotos(realmList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showError(e);
                    }
                });

    }
}
