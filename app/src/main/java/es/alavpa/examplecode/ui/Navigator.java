package es.alavpa.examplecode.ui;

import android.content.Context;
import android.content.Intent;

import es.alavpa.examplecode.ui.followers.FollowersActivity;
import es.alavpa.examplecode.ui.friends.FriendsActivity;
import es.alavpa.examplecode.ui.login.LoginActivity;

/**
 * Created by alavpa on 1/8/16.
 */
public class Navigator {

    private Intent getIntent(Context ctx, Class activityClass){
        return new Intent(ctx,activityClass);
    }

    public void goToFriendsActivity(Context ctx){
        if(ctx!=null) {
            Intent i = getIntent(ctx, FriendsActivity.class);
            ctx.startActivity(i);
        }
    }

    public void goToFollowersActivity(Context ctx){
        if(ctx!=null) {
            Intent i = getIntent(ctx, FollowersActivity.class);
            ctx.startActivity(i);
        }
    }

    public void goToLoginActivity(Context ctx){
        if(ctx!=null) {
            Intent i = getIntent(ctx, LoginActivity.class);
            ctx.startActivity(i);
        }
    }
}
