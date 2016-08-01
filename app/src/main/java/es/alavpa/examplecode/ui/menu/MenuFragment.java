package es.alavpa.examplecode.ui.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

/**
 * Created by alavpa on 1/8/16.
 */
public class MenuFragment extends Fragment implements MenuView{

    @BindView(R.id.ll_items)
    public LinearLayout ll_items;

    @BindView(R.id.iv_header)
    public ImageView iv_header;

    @BindView(R.id.iv_profile)
    public ImageView iv_profile;

    @BindView(R.id.tv_current)
    TextView tv_current;

    private int mCurrentSelectedPosition = 0;


    MenuPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);
        presenter = new MenuPresenter(this);
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
        tv_current.setText(name);
    }

    @Override
    public void setNickname(String nickname) {

    }

}
