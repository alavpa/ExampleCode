package es.alavpa.examplecode.ui.base;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import es.alavpa.examplecode.R;
import es.alavpa.examplecode.ui.menu.MenuFragment;

/**
 * Created by alavpa on 1/8/16.
 */
public class BaseToolbarActivity extends BaseActivity{


    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    public DrawerLayout drawerLayout;

    private ActionBarDrawerToggle toggle;
    MenuFragment menuFragment = new MenuFragment();

    public void initMenu(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        getSupportFragmentManager().beginTransaction().replace(R.id.menu,menuFragment).commit();
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(toggle);
        drawerLayout.setStatusBarBackground(R.color.colorPrimary);
        toggle.syncState();


    }
}
