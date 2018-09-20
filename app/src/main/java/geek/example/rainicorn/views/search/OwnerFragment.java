package geek.example.rainicorn.views.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import geek.example.rainicorn.R;
import geek.example.rainicorn.data.models.owner.Photo;
import geek.example.rainicorn.presenter.OwnerPresenter;
import geek.example.rainicorn.presenter.OwnerView;
import geek.example.rainicorn.utils.Constants;


public class OwnerFragment extends MvpAppCompatFragment implements OwnerView{
    @InjectPresenter
    OwnerPresenter ownerPresenter;

    private String userId;
    private RecyclerView photoRecyclerView;
    private List<Photo> mItems = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        if(getArguments() != null) {
            userId = getArguments().getString(Constants.OWNER_GALLERY);
            ownerPresenter.setUser(userId);
        }
        photoRecyclerView = v.findViewById(R.id.photo_gallery_recycler_view);
        photoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        setupAdapter();
        return v;
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

        public PhotoHolder(View itemView) {
            super(itemView);
            itemImageView = itemView.findViewById(R.id.photo_gallery_image_view);
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
            Glide.with(getActivity())
                    .load("https://farm"+galleryItem.getFarm()+".staticflickr.com/"+galleryItem.getServer()+"/"
                            +galleryItem.getId()+"_"+galleryItem.getSecret()+"_m.jpg")
                    .into(holder.itemImageView);
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


}
