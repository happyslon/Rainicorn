package geek.example.rainicorn.presenter;

import com.arellomobile.mvp.MvpPresenter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import geek.example.rainicorn.data.models.picture.Picture;

public class PicturePresenter extends MvpPresenter<PictureView> implements Subscriber<Picture> {
    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Picture picture) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
