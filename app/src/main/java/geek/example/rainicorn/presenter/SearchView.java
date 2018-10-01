package geek.example.rainicorn.presenter;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import geek.example.rainicorn.data.models.RealmModel;
import geek.example.rainicorn.data.models.gallery.Photo;

public interface SearchView extends MvpView {

    void startLoad();

    void finishLoad();

    void showError(Throwable e);

    void setPhotos(List<Photo> galleryItem);

    void sendMessage(String s);

}
