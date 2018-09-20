package geek.example.rainicorn.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import geek.example.rainicorn.data.database.realm.DataSource;
import geek.example.rainicorn.data.database.realm.RealmHelper;
import geek.example.rainicorn.data.models.RealmModel;
import geek.example.rainicorn.data.models.gallery.GalleryItem;
import geek.example.rainicorn.data.models.gallery.Photo;
import geek.example.rainicorn.data.rest.NetApiClient;
import geek.example.rainicorn.utils.Constants;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

@InjectViewState
public class SearchPresenter extends MvpPresenter<SearchView> implements Subscriber<GalleryItem>{

    @Override
    public void attachView(SearchView view) {
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

    @Override
    public void onNext(GalleryItem galleryItems) {
//        getViewState().setPhotos(galleryItems.getPhotos().getPhoto());
        List<Photo> modelList = galleryItems.getPhotos().getPhoto();
        Single<Bundle> singleSaveAllRealm = Single.create((SingleOnSubscribe<Bundle>) emitter -> {
            try {
                Realm realm = RealmHelper.newRealmInstance();
                for (Photo curItem : modelList) {
                    try {
                        realm.beginTransaction();
                        RealmModel realmModel = realm.createObject(RealmModel.class);
                        realmModel.setOwner(curItem.getOwner());
                        realmModel.setTitle(curItem.getTitle());
                        realmModel.setUrlS(curItem.getUrlS());
                        realm.commitTransaction();
                    }
                    catch (Exception e) {
                        realm.cancelTransaction();
                        emitter.onError(e);
                    }
                }
                long count = realm.where(RealmModel.class).count();
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.EXT_COUNT, (int) count);
                emitter.onSuccess(bundle);
                realm.close();
            }
            catch (Exception e) {
                emitter.onError(e);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        singleSaveAllRealm.subscribeWith(new DisposableSingleObserver<Bundle>() {

            @Override
            protected void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(@NonNull Bundle bundle) {
                getPhotoRealm();

            }

            @Override
            public void onError(@NonNull Throwable e) {
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

    private DisposableSingleObserver<List<RealmModel>> CreateObserver() {
        return new DisposableSingleObserver<List<RealmModel>>() {

            @Override
            protected void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(@NonNull List<RealmModel> realmList) {
                getViewState().setPhotos(realmList);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                getViewState().showError(e);
            }
        };
    }

    public void getPhotoRealm() {
        Single<List<RealmModel>> singleSelectAllRealm = Single.create((SingleOnSubscribe<List<RealmModel>>) emitter -> {
            try {
                Realm realm = RealmHelper.newRealmInstance();
                RealmResults<RealmModel> tempList = realm.where(RealmModel.class).findAll();
                List<RealmModel> finalList = realm.copyFromRealm(tempList);
                emitter.onSuccess(finalList);
                realm.close();
            }
            catch (Exception e) {
                emitter.onError(e);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        singleSelectAllRealm.subscribeWith(CreateObserver());

    }
}
