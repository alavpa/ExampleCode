package es.alavpa.examplecode.ui.friends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.alavpa.examplecode.R;
import es.alavpa.examplecode.ui.App;
import es.alavpa.examplecode.ui.model.UserView;

/**
 * Created by alavpa on 1/8/16.
 */
public class FriendsAdapter extends ArrayAdapter<UserView> {
    public FriendsAdapter(Context context) {
        super(context, R.layout.row_userview);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView==null){
            convertView = LayoutInflater.from(App.getApplication())
                    .inflate(R.layout.row_userview,parent,false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (Holder)convertView.getTag();
        }

        UserView userView = getItem(position);
        Glide.with(App.getApplication())
                .load(userView.getUrl())
                .fitCenter()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.iv_avatar);

        holder.tv_name.setText(userView.getName());
        holder.tv_nickname.setText("@" + userView.getNickname());

        return convertView;
    }

    public static class Holder{
        @BindView(R.id.iv_avatar)
        ImageView iv_avatar;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_nickname)
        TextView tv_nickname;

        public Holder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
