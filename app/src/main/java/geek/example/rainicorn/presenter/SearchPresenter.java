package geek.example.rainicorn.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import geek.example.rainicorn.data.models.gallery.GalleryItem;
import geek.example.rainicorn.data.rest.NetApiClient;

@InjectViewState
public class SearchPresenter extends MvpPresenter<SearchView> implements Subscriber<GalleryItem> {
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
        getViewState().setPhotos(galleryItems.getPhotos().getPhoto());
    }


    @Override
    public void onError(Throwable e) {
        getViewState().showError(e);
    }

    @Override
    public void onComplete() {
        getViewState().finishLoad();
    }
}
