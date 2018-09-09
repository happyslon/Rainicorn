package geek.example.rainicorn.presenters;

import geek.example.rainicorn.presenters.base.BaseRestPresenter;
import rx.Observable;

public class MainPresenter extends BaseRestPresenter<String> {

    public void update(){
        getViewState().startLoading();
        Observable.just("", "")
                .subscribe(this);
    }

    @Override
    public void onNext(String s) {

    }
}
