package es.alavpa.examplecode.ui.followers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.alavpa.examplecode.R;
import es.alavpa.examplecode.ui.App;
import es.alavpa.examplecode.ui.model.UserView;

/**
 * Created by alavpa on 1/8/16.
 */
public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.Holder> {
    List<UserView> users;
    public FollowersAdapter(){
        users = new ArrayList<>();
    }

    public void addAll(List<UserView> users){
        this.users.addAll(users);
    }

    public UserView getItem(int position){
        return users.get(position);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_userview, parent, false);
        Holder holder = new Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        UserView userView = getItem(position);

        Glide.with(App.getApplication())
                .load(userView.getUrl())
                .fitCenter()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.iv_avatar);

        holder.tv_name.setText(userView.getName());
        holder.tv_nickname.setText("@" + userView.getNickname());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class Holder extends  RecyclerView.ViewHolder{
        @BindView(R.id.iv_avatar)
        ImageView iv_avatar;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_nickname)
        TextView tv_nickname;

        public Holder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
