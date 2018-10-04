package geek.example.rainicorn.presenter.owner;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import javax.inject.Inject;

import geek.example.rainicorn.MainApplication;
import geek.example.rainicorn.data.models.owner.profile.OwnerProfile;
import geek.example.rainicorn.data.rest.NetApiClient;


@InjectViewState
public class OwnerProfilePresenter extends MvpPresenter<OwnerProfileView> implements Subscriber<OwnerProfile> {

    @Inject
    NetApiClient netApiClient;
    private String user;


    private void loadDate() {
        netApiClient.getInfoOwnerProfile(user)
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

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        MainApplication.getComponent().injectsToOwnerProfilePresenter(this);
    }

    public void setUser(String user) {
        this.user = user;
    }
}
