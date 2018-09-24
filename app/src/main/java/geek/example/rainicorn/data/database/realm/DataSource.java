package geek.example.rainicorn.data.database.realm;

import java.util.List;

import geek.example.rainicorn.data.models.gallery.Photo;
import io.reactivex.Completable;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import geek.example.rainicorn.data.models.RealmModel;
import io.realm.RealmResults;


public class DataSource {

    public Completable saveAllRealm(List<Photo> modelList){
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
    public Single<List<RealmModel>> readAllRealm(){
        return  Single.create((SingleOnSubscribe<List<RealmModel>>) emitter -> {
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
    }



}
