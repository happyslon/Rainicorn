package geek.example.rainicorn.views.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import geek.example.rainicorn.R;
import geek.example.rainicorn.data.models.owner.Photo;
import geek.example.rainicorn.data.models.owner.profile.OwnerProfile;
import geek.example.rainicorn.data.models.owner.profile.Person;
import geek.example.rainicorn.presenter.BasePresenter;
import geek.example.rainicorn.presenter.OwnerPresenter;
import geek.example.rainicorn.presenter.OwnerProfilePresenter;
import geek.example.rainicorn.presenter.OwnerProfileView;
import geek.example.rainicorn.presenter.OwnerView;
import geek.example.rainicorn.utils.Constants;


public class OwnerFragment extends MvpAppCompatFragment implements OwnerView,OwnerProfileView{
    @InjectPresenter
    OwnerPresenter ownerPresenter;
    @InjectPresenter
    OwnerProfilePresenter ownerProfilePresenter;
    BasePresenter callbackActivity;

    private String userId;
    private RecyclerView photoRecyclerView;
    private List<Photo> mItems = new ArrayList<>();
    OwnerProfile ownerProfile;
    @BindView(R.id.profile_image)
    CircleImageView circleImageView;
    @BindView(R.id.text_username)
    TextView userName;
    @BindView(R.id.text_realname)
    TextView realName;
    @BindView(R.id.text_fist_date)
    TextView fistDate;
    @BindView(R.id.text_description)
    TextView description;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_owner_profile, container, false);
        if(getArguments() != null) {
            userId = getArguments().getString(Constants.OWNER_GALLERY);
            ownerPresenter.setUser(userId);
            ownerProfilePresenter.setUser(userId);
        }
        photoRecyclerView = v.findViewById(R.id.photo_owner_recycler_view);
        photoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        ButterKnife.bind(this, v);
        setupAdapter();
        return v;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callbackActivity = (BasePresenter) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setupAdapter() {
        photoRecyclerView.setAdapter(new OwnerFragment.PhotoAdapter(mItems));
    }

    private class PhotoHolder extends RecyclerView.ViewHolder {
        private ImageView itemImageView;
        private TextView textView;

        public PhotoHolder(View itemView) {
            super(itemView);
            itemImageView = itemView.findViewById(R.id.photo_gallery_image_view);
            textView = itemView.findViewById(R.id.text_item);
        }
    }

    private class PhotoAdapter extends RecyclerView.Adapter<OwnerFragment.PhotoHolder> {
        private List<Photo> mGalleryItems;

        public PhotoAdapter(List<Photo> items) {
            mGalleryItems = items;
        }

        @NonNull
        @Override
        public OwnerFragment.PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(R.layout.gallery_item, parent, false);

            return new OwnerFragment.PhotoHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull OwnerFragment.PhotoHolder holder, int position) {
            Photo galleryItem = mGalleryItems.get(position);
            holder.textView.setText(galleryItem.getTitle());
            Glide.with(getActivity())
                    .load("https://farm"+galleryItem.getFarm()+".staticflickr.com/"+galleryItem.getServer()+"/"
                            +galleryItem.getId()+"_"+galleryItem.getSecret()+"_m.jpg")
                    .into(holder.itemImageView);
            holder.itemImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callbackActivity.onPicture("https://farm"+galleryItem.getFarm()
                            +".staticflickr.com/"+galleryItem.getServer()+"/"
                            +galleryItem.getId()+"_"+galleryItem.getSecret()+"_c.jpg");
                }
            });
        }

        @Override
        public int getItemCount() {
            return mGalleryItems.size();
        }
    }


    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void setOwnerPhotos(List<Photo> galleryItem) {
        if(galleryItem == null) return;
        mItems = galleryItem;
        setupAdapter();
    }

    @Override
    public void setOwnerProfile(OwnerProfile ownerProfile) {
        this.ownerProfile = ownerProfile;
        setupProfile();
    }

    private void setupProfile() {
        if(ownerProfile == null) return;
        Person person = ownerProfile.getPerson();
        if(isValid(person)) {
            Glide.with(getActivity())
                    .load("http://farm" + person.getIconfarm() + ".staticflickr.com/" + person.getIconserver()
                            + "/buddyicons/" + person.getNsid() + ".jpg")
                    .into(circleImageView);
        }

        if(person.getUsername() != null) userName.setText(person.getUsername().getContent());

        if(person.getRealname() != null) realName.setText(person.getRealname().getContent());

        if(person.getPhotos().getFirstdatetaken() != null) fistDate.setText(person.getPhotos().getFirstdatetaken().getContent());

        if(person.getDescription() != null) description.setText(person.getDescription().getContent());

    }
    public boolean isValid(Person person) {
        return person.getIconfarm() != null && person.getIconserver() != null && person.getNsid() != null;
    }

}
