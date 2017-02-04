package es.alavpa.examplecode.ui.menu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.alavpa.examplecode.R;
import es.alavpa.examplecode.ui.Navigator;
import es.alavpa.examplecode.ui.base.BaseFragment;

/**
 * Created by alavpa on 1/8/16.
 */
public class MenuFragment extends BaseFragment implements MenuView {

    public static final String KEY_CURRENT_POSITION = "currentPosition";
    public static final int MENU_FRIENDS = 0;
    public static final int MENU_FOLLOWERS = 1;

    @BindView(R.id.ll_items)
    public LinearLayout ll_items;

    @BindView(R.id.iv_header)
    public ImageView iv_header;

    @BindView(R.id.iv_profile)
    public ImageView iv_profile;

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_nickname)
    TextView tv_nickname;
    Navigator navigator;
    MenuPresenter presenter;
    MenuParentView parent;
    private int mCurrentSelectedPosition;

    public static MenuFragment getInstance(int position){

        MenuFragment menuFragment = new MenuFragment();

        Bundle args = new Bundle();
        args.putInt(KEY_CURRENT_POSITION,position);
        menuFragment.setArguments(args);

        return menuFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parent = (MenuParentView) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        parent = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);

        mCurrentSelectedPosition = getArguments().getInt(KEY_CURRENT_POSITION);
        navigator = new Navigator();
        presenter = new MenuPresenter(this);
        setBasePresenter(presenter);
        presenter.init();
    }

    @Override
    public void loadBg(String url) {
        if(url!=null && !url.isEmpty()) {
            Glide.with(getActivity())
                    .load(url)
                    .centerCrop()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv_header);
        }
    }

    @Override
    public void loadAvatar(String url) {


        Glide.with(getActivity())
                .load(url)
                .fitCenter()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(iv_profile);


        iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void setName(String name) {
        tv_name.setText(name);
    }

    @Override
    public void setNickname(String nickname) {
        tv_nickname.setText(nickname);
    }

    @Override
    public void loadItems(String[] items) {
        ll_items.removeAllViews();

        for(int i=0;i<items.length;i++){
            View v;
            switch (i){
                case MENU_FRIENDS:
                    v = createMenuItem(items[i]);
                    v.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            presenter.onClickFriends();
                        }
                    });
                    break;
                case MENU_FOLLOWERS:
                    v = createMenuItem(items[i]);
                    v.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            presenter.onClickFollowers();
                        }
                    });
                    break;
            }
        }
    }

    @Override
    public void goToFriends() {
        navigator.goToFriendsActivity(getActivity());
    }

    @Override
    public void goToFollowers() {
        navigator.goToFollowersActivity(getActivity());
    }

    public View createMenuItem(String text){
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.row_menuitem,ll_items,false);

        TextView tv_item = (TextView)v.findViewById(R.id.tv_item);
        tv_item.setText(text);

        ll_items.addView(v);

        return v;
    }

    @Override
    public int getCurrentPosition(){
        return mCurrentSelectedPosition;
    }

    @Override
    public void hideMenu() {
        if(parent!=null){
            parent.hideMenu();
        }
    }
}
