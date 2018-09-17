package geek.example.rainicorn.views.search;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import geek.example.rainicorn.data.models.owner.Photo;
import geek.example.rainicorn.presenter.OwnerPresenter;
import geek.example.rainicorn.presenter.OwnerView;

public class OwnerFragment extends MvpAppCompatFragment implements OwnerView{
    @InjectPresenter
    OwnerPresenter ownerPresenter;

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void setOwnerPhotos(List<Photo> galleryItem) {

    }
}
