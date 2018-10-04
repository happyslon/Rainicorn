package geek.example.rainicorn.presenter.feed;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import geek.example.rainicorn.data.models.view.FeedViewModel;

public interface FeedView extends MvpView {

    void setItems(List<FeedViewModel> items);

    void showError(Throwable e);
}
