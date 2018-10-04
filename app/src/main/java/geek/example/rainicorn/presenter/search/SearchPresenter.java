package geek.example.rainicorn.presenter.search;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import javax.inject.Inject;

import geek.example.rainicorn.MainApplication;
import geek.example.rainicorn.data.models.gallery.GalleryItem;
import geek.example.rainicorn.data.rest.NetApiClient;

@InjectViewState
public class SearchPresenter extends MvpPresenter<SearcherView> implements Subscriber<GalleryItem> {

    @Inject
    NetApiClient netApiClient;

    public void loadDateForText(String s) {
        netApiClient.getGallerySearch(s)
                .subscribe(this);
    }

    @Override
    public void attachView(SearcherView view) {
        super.attachView(view);
        loadDate();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        MainApplication.getComponent().injectsToSearchPresenter(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void loadDate() {
        getViewState().startLoad();
        netApiClient.getGallery()
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

