package geek.example.rainicorn.views.picture;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import geek.example.rainicorn.R;
import geek.example.rainicorn.presenter.PictureView;
import geek.example.rainicorn.utils.Constants;
import geek.example.rainicorn.views.GlideApp;
import io.reactivex.Completable;
import io.reactivex.CompletableOnSubscribe;


public class PictureFragment extends MvpAppCompatFragment implements PictureView {
    String urlPic;

    @BindView(R.id.pic_view)
    ImageView image;
    @BindView(R.id.linear_id)
    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_picture, container, false);
        ButterKnife.bind(this, v);
        if(getArguments() != null) {
            urlPic = getArguments().getString(Constants.PICTURE_URL);
        }
        return v;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
                GlideApp
                        .with(this)
                        .asBitmap()
                        .load(urlPic)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                image.setImageBitmap(resource);
                                setBackground();
                            }
                        });
    }
    private void setBackground() {
        Palette palette = Palette.from(((BitmapDrawable)image.getDrawable()).getBitmap()).generate();
        int defaultColor = 0x000000;
        int muted = palette.getMutedColor(defaultColor);
        linearLayout.setBackgroundColor(muted);
    }
}
