package es.alavpa.examplecode.ui.friends;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import es.alavpa.examplecode.R;
import es.alavpa.examplecode.ui.base.BaseToolbarActivity;
import es.alavpa.examplecode.ui.model.UserView;

/**
 * Created by alavpa on 1/8/16.
 */
public class FriendsActivity extends BaseToolbarActivity implements FriendsView {

    FriendsAdapter adapter;
    @BindView(R.id.list)
    ListView list;

    FriendsPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMenu();

        presenter = new FriendsPresenter(this);
        presenter.init();
    }


    @Override
    public void showUsers(List<UserView> users) {
        if(adapter==null){
            adapter = new FriendsAdapter(this);
            list.setAdapter(adapter);
        }

        adapter.addAll(users);
        adapter.notifyDataSetChanged();
    }
}
