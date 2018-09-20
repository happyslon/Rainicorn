package geek.example.rainicorn.views.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import geek.example.rainicorn.R;
import geek.example.rainicorn.data.models.RealmModel;
import geek.example.rainicorn.data.models.gallery.Photo;
import geek.example.rainicorn.presenter.BasePresenter;
import geek.example.rainicorn.presenter.SearchPresenter;
import geek.example.rainicorn.presenter.SearchView;

public class SearchFragment extends MvpAppCompatFragment implements SearchView {

    @InjectPresenter
    SearchPresenter searchPresenter;

    BasePresenter callbackActivity;

    private RecyclerView photoRecyclerView;
    private List<RealmModel> mItems = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callbackActivity = (BasePresenter) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
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
        photoRecyclerView.setAdapter(new PhotoAdapter(mItems));
    }

    private class PhotoHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView itemImageView;

        public PhotoHolder(View itemView) {
            super(itemView);
            itemImageView = itemView.findViewById(R.id.photo_gallery_image_view);
            textView = itemView.findViewById(R.id.text_item);
        }
    }

    private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {
        private List<RealmModel> mGalleryItems;

        public PhotoAdapter(List<RealmModel> items) {
            mGalleryItems = items;
        }

        @NonNull
        @Override
        public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(R.layout.gallery_item, parent, false);

            return new PhotoHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
            RealmModel galleryItem = mGalleryItems.get(position);
            holder.textView.setText(galleryItem.getTitle());
            Glide.with(getActivity())
                    .load(galleryItem.getUrlS())
                    .into(holder.itemImageView);
            holder.itemImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callbackActivity.onDetailsGalleryOwner(galleryItem.getOwner());
                }
            });
        }

        @Override
        public int getItemCount() {
            return mGalleryItems.size();
        }
    }

    @Override
    public void startLoad() {
    }

    @Override
    public void finishLoad() {

    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPhotos(List<RealmModel> galleryItem) {
        if (galleryItem == null) return;
        mItems = galleryItem;
        setupAdapter();
    }

}
