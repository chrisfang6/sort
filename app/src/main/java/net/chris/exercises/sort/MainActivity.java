package net.chris.exercises.sort;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.clans.fab.FloatingActionMenu;

import net.chris.exercises.sort.Constants.Type;
import net.chris.exercises.sort.comm.RxSubject;
import net.chris.exercises.sort.inject.MainComponent;
import net.chris.exercises.sort.kotlin.KTSortFragment;
import net.chris.exercises.sort.ui.SortFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    RxSubject<Type> typeSubject;

    private Unbinder unbind;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.menu_choose_button)
    FloatingActionMenu floatingActionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbind = ButterKnife.bind(this);
        MainComponent.getMainComponent().inject(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new SortFragment(), "sort fragment").commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbind.unbind();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_insert:
                typeSubject.post(Type.INSERTION_SORT);
                break;
            case R.id.menu_item_bubble:
                typeSubject.post(Type.BUBBLE_SORT);
                break;
            case R.id.menu_item_merge:
                typeSubject.post(Type.MERGE_SORT);
                break;
            case R.id.menu_item_selection:
                typeSubject.post(Type.SELECTION_SORT);
                break;
            case R.id.menu_item_heap:
                typeSubject.post(Type.HEAP_SORT);
                break;
            case R.id.menu_item_quick:
                typeSubject.post(Type.QUICK_SORT);
                break;
            case R.id.menu_item_bucket:
                typeSubject.post(Type.BUCKET_SORT);
                break;
            case R.id.menu_item_counting:
                typeSubject.post(Type.COUNTING_SORT);
                break;
            case R.id.menu_item_radix:
                typeSubject.post(Type.RADIX_SORT);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick({R.id.menu_java_button, R.id.menu_kotlin_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu_java_button:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SortFragment(), "sort fragment").commit();
                break;
            case R.id.menu_kotlin_button:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, KTSortFragment.Companion.newInstance(), "kotlin sort fragment").commit();
                break;
        }
        floatingActionMenu.close(true);
    }

}
