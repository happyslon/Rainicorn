package geek.example.rainicorn.presenter.owner;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import javax.inject.Inject;

import geek.example.rainicorn.MainApplication;
import geek.example.rainicorn.data.models.owner.Owner;
import geek.example.rainicorn.data.rest.NetApiClient;

@InjectViewState
public class OwnerPresenter extends MvpPresenter<OwnerView> implements Subscriber<Owner> {

    @Inject
    NetApiClient netApiClient;

    private String user;

    @Override
    public void attachView(OwnerView view) {
        super.attachView(view);
        loadDate();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        MainApplication.getComponent().injectsToOwnerPresenter(this);
    }

    private void loadDate() {
        netApiClient.getOwner(user)
                .subscribe(this);
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Owner owner) {
        getViewState().setOwnerPhotos(owner.getPhotos().getPhoto());
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }

    public void setUser(String user) {
        this.user = user;
    }
}
