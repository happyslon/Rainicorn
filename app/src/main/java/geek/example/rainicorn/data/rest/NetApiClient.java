package geek.example.rainicorn.data.rest;

import geek.example.rainicorn.data.Endpoints;
import geek.example.rainicorn.data.models.gallery.GalleryItem;
import geek.example.rainicorn.data.models.owner.Owner;
import geek.example.rainicorn.data.models.owner.profile.OwnerProfile;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NetApiClient {
    private static final String METHOD_RECENT="flickr.photos.getRecent";
    private static final String METHOD_SEARCH="flickr.photos.search";
    private static final String METHOD_INFO_OWNER="flickr.people.getInfo";
    private static final String API_KEY="1bc3bc4dae06cfc8e06e68ec3ef1a736";
    private static final String FORMAT="json";
    private static final String NOJSONCALLBACK="1";
    private static final String EXTRAS="url_s";


    private static final NetApiClient ourInstance = new NetApiClient();

    public static NetApiClient getInstance() {
        return ourInstance;
    }

    private Endpoints netApi = (Endpoints) new ServiceGenerator().createService(Endpoints.class);

    private NetApiClient() {
    }

    public Flowable<GalleryItem> getGallery(){
        return netApi.getResponse(METHOD_RECENT,API_KEY,FORMAT,NOJSONCALLBACK,EXTRAS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Flowable<GalleryItem> getGallerySearch(String text){
        return netApi.getResponseSearch(METHOD_SEARCH,API_KEY,text,FORMAT,NOJSONCALLBACK)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Flowable<Owner> getOwner(String user){
        return netApi.getOwnerGallery(METHOD_SEARCH,API_KEY,user,FORMAT,NOJSONCALLBACK)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    public Flowable<OwnerProfile> getInfoOwnerProfile(String user){
        return netApi.getInfoOwner(METHOD_INFO_OWNER,API_KEY,user,FORMAT,NOJSONCALLBACK)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
