package geek.example.rainicorn.presenter;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import geek.example.rainicorn.data.models.owner.Photo;


public interface OwnerView extends MvpView {
    
    void showError(Throwable e);

    void setOwnerPhotos(List<Photo> galleryItem);

}
