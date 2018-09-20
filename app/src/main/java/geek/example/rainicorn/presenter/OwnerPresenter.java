package geek.example.rainicorn.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import geek.example.rainicorn.data.models.owner.Owner;
import geek.example.rainicorn.data.rest.NetApiClient;

@InjectViewState
public class OwnerPresenter extends MvpPresenter<OwnerView> implements Subscriber<Owner> {
    private String user;
    @Override
    public void attachView(OwnerView view) {
        super.attachView(view);
        loadDate();
    }
//    public OwnerPresenter(String user){
//        loadDate(user);
//    }

    private void loadDate() {
        NetApiClient.getInstance().getOwner(user)
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
