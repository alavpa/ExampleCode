package es.alavpa.examplecode.ui.followers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import es.alavpa.examplecode.R;
import es.alavpa.examplecode.ui.base.BaseToolbarActivity;
import es.alavpa.examplecode.ui.menu.MenuFragment;
import es.alavpa.examplecode.ui.model.UserView;

/**
 * Created by alavpa on 1/8/16.
 */
public class FollowersActivity extends BaseToolbarActivity implements FollowersView {

    FollowersAdapter adapter;
    @BindView(R.id.list)
    RecyclerView list;

    private boolean loading = true;

    FollowersPresenter presenter;
    LinearLayoutManager mLayoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMenu(MenuFragment.MENU_FOLLOWERS);

        presenter = new FollowersPresenter(this);
        presenter.init();

        mLayoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(mLayoutManager);

        list.addOnScrollListener(new RecyclerView.OnScrollListener() {

            int pastVisiblesItems, visibleItemCount, totalItemCount;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy > 0)
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loading = false;
                            presenter.reload();
                        }
                    }
                }
            }

        });
    }


    @Override
    public void showUsers(List<UserView> users) {
        if(adapter==null){
            adapter = new FollowersAdapter();
            list.setAdapter(adapter);
        }

        loading = true;
        adapter.addAll(users);
        adapter.notifyDataSetChanged();
    }
}
