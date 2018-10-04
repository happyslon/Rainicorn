package geek.example.rainicorn.data.database.realm.usecases;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import geek.example.rainicorn.MainApplication;
import geek.example.rainicorn.data.database.realm.RealmHelper;
import geek.example.rainicorn.data.models.RealmModel;
import geek.example.rainicorn.data.models.gallery.GalleryItem;
import geek.example.rainicorn.data.models.gallery.Photo;
import geek.example.rainicorn.data.rest.NetApiClient;
import io.reactivex.Completable;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

public class FeedUsecases implements Subscriber<GalleryItem>{
    @Inject
    NetApiClient netApiClient;
    List<Photo> modelList;

    public FeedUsecases() {
        MainApplication.getComponent().injectsToFeedUsecases(this);
        netApiClient.getGallery().subscribe(this);
    }

    public Completable getFeed() {
        return Completable.create((CompletableOnSubscribe) emitter -> {
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
                emitter.onComplete();
                realm.close();
            } catch (Exception e) {
                emitter.onError(e);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(GalleryItem galleryItem) {
        modelList = galleryItem.getPhotos().getPhoto();
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
