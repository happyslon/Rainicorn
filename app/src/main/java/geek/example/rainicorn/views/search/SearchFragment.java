package geek.example.rainicorn.views.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import geek.example.rainicorn.R;
import geek.example.rainicorn.data.models.gallery.Photo;
import geek.example.rainicorn.presenter.BasePresenter;
import geek.example.rainicorn.presenter.search.SearchPresenter;
import geek.example.rainicorn.presenter.search.SearcherView;


public class SearchFragment extends MvpAppCompatFragment implements SearcherView,
        SearchView.OnQueryTextListener,
        MenuItem.OnActionExpandListener {

    @InjectPresenter
    SearchPresenter searchPresenter;

    BasePresenter callbackActivity;

    private RecyclerView photoRecyclerView;
    private List<Photo> mItems = new ArrayList<>();
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setHasOptionsMenu(true);
    }

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
        photoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        setupAdapter();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search");
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void setupAdapter() {
        photoRecyclerView.setAdapter(new PhotoAdapter(mItems));
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return false;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (query != null && !query.isEmpty()) {
            searchPresenter.loadDateForText(query);
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText != null && !newText.isEmpty()) {
            searchPresenter.loadDateForText(newText);
        }
        return false;
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
        private List<Photo> mGalleryItems;

        public PhotoAdapter(List<Photo> items) {
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
            Photo galleryItem = mGalleryItems.get(position);
            holder.textView.setText(galleryItem.getTitle());
            if (galleryItem.getUrlS() != null) {
                Glide.with(getActivity())
                        .load(galleryItem.getUrlS())
                        .into(holder.itemImageView);

            } else {
                Glide.with(getActivity())
                        .load("https://farm" + galleryItem.getFarm() + ".staticflickr.com/" + galleryItem.getServer() + "/"
                                + galleryItem.getId() + "_" + galleryItem.getSecret() + "_m.jpg")
                        .into(holder.itemImageView);
            }
            holder.itemImageView.setOnClickListener(v -> callbackActivity.onDetailsGalleryOwner(galleryItem.getOwner()));
            holder.itemImageView.setOnLongClickListener(v -> {
                callbackActivity.onPicture("https://farm" + galleryItem.getFarm()
                        + ".staticflickr.com/" + galleryItem.getServer() + "/"
                        + galleryItem.getId() + "_" + galleryItem.getSecret() + "_c.jpg");
                return false;
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
    public void setPhotos(List<Photo> galleryItem) {
        if (galleryItem == null) return;
        mItems = galleryItem;
        setupAdapter();
    }

}
