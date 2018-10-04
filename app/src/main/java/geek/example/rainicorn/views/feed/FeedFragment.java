package geek.example.rainicorn.views.feed;

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
import geek.example.rainicorn.data.models.owner.Photo;
import geek.example.rainicorn.data.models.view.FeedViewModel;
import geek.example.rainicorn.presenter.feed.FeedPresenter;
import geek.example.rainicorn.presenter.feed.FeedView;


public class FeedFragment extends MvpAppCompatFragment implements FeedView {
    @InjectPresenter
    FeedPresenter feedPresenter;

    private String userId;
    private RecyclerView photoRecyclerView;
    private List<FeedViewModel> mItems = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        photoRecyclerView = v.findViewById(R.id.photo_gallery_recycler_view);
        photoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        setupAdapter();
        return v;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setupAdapter() {
        photoRecyclerView.setAdapter(new FeedFragment.PhotoAdapter(mItems));
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

    private class PhotoAdapter extends RecyclerView.Adapter<FeedFragment.PhotoHolder> {
        private List<FeedViewModel> mGalleryItems;

        public PhotoAdapter(List<FeedViewModel> items) {
            mGalleryItems = items;
        }

        @NonNull
        @Override
        public FeedFragment.PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(R.layout.gallery_item, parent, false);

            return new FeedFragment.PhotoHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull FeedFragment.PhotoHolder holder, int position) {
            FeedViewModel galleryItem = mGalleryItems.get(position);
            holder.textView.setText(galleryItem.getTitle());
            if (galleryItem.getUrlS() != null) {
                Glide.with(getActivity())
                        .load(galleryItem.getUrlS())
                        .into(holder.itemImageView);
            }
        }

        @Override
        public int getItemCount() {
            return mGalleryItems.size();
        }
    }
    @Override
    public void setItems(List<FeedViewModel> items) {
        if (items == null) return;
        mItems = items;
        setupAdapter();
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
    }
}
