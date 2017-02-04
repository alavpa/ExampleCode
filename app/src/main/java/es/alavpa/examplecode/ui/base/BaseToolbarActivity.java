package es.alavpa.examplecode.ui.base;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import es.alavpa.examplecode.R;
import es.alavpa.examplecode.ui.menu.MenuFragment;
import es.alavpa.examplecode.ui.menu.MenuParentView;

/**
 * Created by alavpa on 1/8/16.
 */
public class BaseToolbarActivity extends BaseActivity implements MenuParentView{


    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    public DrawerLayout drawerLayout;
    MenuFragment menuFragment;
    int position;
    private ActionBarDrawerToggle toggle;

    public void initMenu(int position){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        menuFragment = MenuFragment.getInstance(position);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu,menuFragment).commit();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(toggle);
        drawerLayout.setStatusBarBackground(R.color.colorPrimary);
        toggle.syncState();

        this.position = position;

    }

    @Override
    public void hideMenu(){
        drawerLayout.closeDrawers();
    }

    @Override
    protected void onResume() {
        super.onResume();

        String title = getResources().getStringArray(R.array.menu_items)[position];
        toolbar.setTitle(title);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }
}
