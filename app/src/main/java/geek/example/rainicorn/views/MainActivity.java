package geek.example.rainicorn.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import butterknife.BindView;
import butterknife.ButterKnife;
import geek.example.rainicorn.R;
import geek.example.rainicorn.presenter.BasePresenter;
import geek.example.rainicorn.utils.Constants;
import geek.example.rainicorn.views.owner.OwnerFragment;
import geek.example.rainicorn.views.picture.PictureFragment;
import geek.example.rainicorn.views.search.SearchFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BasePresenter {

    Fragment fragment;
    FragmentTransaction transaction;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        if (savedInstanceState != null) {
            fragment = getSupportFragmentManager().getFragment(savedInstanceState, "Fragment");
            if (fragment == null) return;
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container_fragments, fragment, fragment.getClass().getName());
            transaction.commit();
        } else {
            placeFragment(SearchFragment.class.getName(), null);
        }


    }

    private void initUI() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (fragment == null) return;
        if (fragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "Fragment", fragment);
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void placeFragment(String fragmentTag, Bundle args) {
        transaction = getSupportFragmentManager().beginTransaction();
        fragment = Fragment.instantiate(this, fragmentTag, args);
        transaction.replace(R.id.container_fragments, fragment, fragmentTag);
        transaction.addToBackStack(MainActivity.class.getName());
        transaction.commit();
    }

    @Override
    public void onDetailsGalleryOwner(String owner) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.OWNER_GALLERY, owner);
        placeFragment(OwnerFragment.class.getName(), bundle);

    }

    @Override
    public void onPicture(String urls) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.PICTURE_URL, urls);
        placeFragment(PictureFragment.class.getName(), bundle);
    }
}
