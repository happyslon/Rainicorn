package geek.example.rainicorn.views;

import android.graphics.Color;
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
import android.widget.Toast;


import com.miguelcatalan.materialsearchview.MaterialSearchView;

import javax.inject.Inject;

import geek.example.rainicorn.MainApplication;
import geek.example.rainicorn.R;
import geek.example.rainicorn.di.component.EventBusComponent;
import geek.example.rainicorn.presenter.BasePresenter;
import geek.example.rainicorn.utils.Constants;
import geek.example.rainicorn.utils.RxEventBus;
import geek.example.rainicorn.views.picture.PictureFragment;
import geek.example.rainicorn.views.search.OwnerFragment;
import geek.example.rainicorn.views.search.SearchFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,BasePresenter {

    Fragment fragment;
    MaterialSearchView searchView;
    EventBusComponent eventBusComponent;
    @Inject
    RxEventBus rxEventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventBusComponent = MainApplication.getEventBusComponent();
        eventBusComponent.injectMainActivity(this);
        initGUI();

        placeFragment(SearchFragment.class.getName(),null);

    }

    private void initGUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Rainicorn");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        searchView = (MaterialSearchView)findViewById(R.id.search_view);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query != null && !query.isEmpty()){
                    rxEventBus.send(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != null && !newText.isEmpty()){
                    rxEventBus.send(newText);
                }
                return false;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void placeFragment(String fragmentTag,Bundle args) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        fragment = Fragment.instantiate(this, fragmentTag, args);
//        transaction.setCustomAnimations(
//                android.R.anim.fade_in, android.R.anim.fade_out,
//                android.R.anim.fade_out, android.R.anim.fade_in);
        transaction.replace(R.id.container_fragments, fragment, fragmentTag);

        transaction.addToBackStack(SearchFragment.class.getName());

        transaction.commit();
    }

    @Override
    public void onDetailsGalleryOwner(String owner) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.OWNER_GALLERY,owner);
        placeFragment(OwnerFragment.class.getName(),bundle);

    }

    @Override
    public void onPicture(String urls) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.PICTURE_URL,urls);
        placeFragment(PictureFragment.class.getName(),bundle);
    }
}
