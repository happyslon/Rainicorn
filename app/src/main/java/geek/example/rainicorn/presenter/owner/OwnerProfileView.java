package geek.example.rainicorn.presenter.owner;

import com.arellomobile.mvp.MvpView;

import geek.example.rainicorn.data.models.owner.profile.OwnerProfile;

public interface OwnerProfileView extends MvpView {

    void setOwnerProfile(OwnerProfile ownerProfile);
}
