package geek.example.rainicorn.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import geek.example.rainicorn.MainApplication;
import geek.example.rainicorn.R;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText editText;
    TextView textView;
    TextView textViewOb1;
    TextView textViewOb2;
    Button btnOb1;
    Button btnOb2;
    Button btnEventbus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initGUI();
    }

    private void initGUI() {
        editText = findViewById(R.id.edit_text);
        textView = findViewById(R.id.text_view);
        textViewOb1 = findViewById(R.id.text_view_observable1);
        textViewOb2 = findViewById(R.id.text_view_observable2);
        btnEventbus = findViewById(R.id.btn_eventbus);
        btnOb1 = findViewById(R.id.btnobservable1);
        btnOb2 = findViewById(R.id.btnobservable2);

        editText.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Observable.just(s)
                        .subscribe(new Subscriber<CharSequence>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(CharSequence charSequence) {
                                textView.setText(String.valueOf(charSequence));
                            }
                        });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnOb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainApplication)getApplication())
                        .getBus()
                        .send(new String("btnOb1"));
            }
        });
        btnOb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainApplication)getApplication())
                        .getBus()
                        .send(new String("btnOb2"));

            }
        });
        btnEventbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainApplication)getApplication())
                        .getBus()
                        .send(new String("btnEventbus"));

            }
        });
        ((MainApplication)getApplication())
                .getBus()
                .getEvents()
                .subscribe(new Action1<Object>() {

                    public void call(Object o) {

                        if (o.equals("btnOb1")) {
                            textViewOb1.setText("btnOb1");
                        } else if (o.equals("btnOb2")) {
                            textViewOb2.setText("btnOb2");
                        } else if (o.equals("btnEventbus")) {
                            textViewOb1.setText("btnEventbus");
                            textViewOb2.setText("btnEventbus");
                        }
                    }
                });
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

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
}
