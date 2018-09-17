package geek.example.rainicorn.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import geek.example.rainicorn.data.models.owner.Owner;
import geek.example.rainicorn.data.rest.NetApiClient;

@InjectViewState
public class OwnerPresenter extends MvpPresenter<OwnerView> implements Subscriber<Owner> {
    @Override
    public void attachView(OwnerView view) {
        super.attachView(view);
//        loadDate();
    }

    private void loadDate(String user) {
        NetApiClient.getInstance().getOwner(user)
                .subscribe(this);
    }

    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onNext(Owner owner) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
