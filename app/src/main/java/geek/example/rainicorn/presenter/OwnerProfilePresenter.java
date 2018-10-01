package geek.example.rainicorn.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import geek.example.rainicorn.data.models.owner.profile.OwnerProfile;
import geek.example.rainicorn.data.rest.NetApiClient;
@InjectViewState
public class OwnerProfilePresenter extends MvpPresenter<OwnerProfileView> implements Subscriber<OwnerProfile> {

    private String user;


    private void loadDate() {
        NetApiClient.getInstance().getInfoOwnerProfile(user)
                .subscribe(this);
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(OwnerProfile ownerProfile) {
        getViewState().setOwnerProfile(ownerProfile);
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void attachView(OwnerProfileView view) {
        super.attachView(view);
        loadDate();
    }

    public void setUser(String user) {
        this.user = user;
    }
}
