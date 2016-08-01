package es.alavpa.examplecode.interactors;

import com.twitter.sdk.android.core.Callback;

import es.alavpa.examplecode.data.api.model.FollowersListResponse;
import es.alavpa.examplecode.data.repositories.TwitterRepository;
import es.alavpa.examplecode.data.repositories.TwitterRepositoryImpl;

/**
 * Created by alavpa on 1/8/16.
 */
public class LoadFollowers {

    TwitterRepository tr = TwitterRepositoryImpl.getInstance();

    public void execute(long cursor, Callback<FollowersListResponse> callback){
        tr.getFollowersList(cursor, callback);
    }
}
