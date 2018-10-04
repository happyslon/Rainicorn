package geek.example.rainicorn.presenter.search;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import geek.example.rainicorn.data.models.gallery.Photo;

public interface SearcherView extends MvpView {
    void startLoad();

    void finishLoad();

    void showError(Throwable e);

    void setPhotos(List<Photo> galleryItem);
}
