package es.alavpa.examplecode.data.repositories;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import es.alavpa.examplecode.data.api.ExampleCodeTwitterApiClient;
import es.alavpa.examplecode.data.api.model.FollowersListResponse;
import es.alavpa.examplecode.data.api.model.FriendListResponse;

/**
 * Created by alavpa on 1/8/16.
 */
public class TwitterRepositoryImpl implements TwitterRepository{

    TwitterSession twitterSession;
    ExampleCodeTwitterApiClient client;

    private static TwitterRepository INSTANCE;
    public static TwitterRepository getInstance(){
        if(INSTANCE==null){
            INSTANCE = new TwitterRepositoryImpl();
        }

        return INSTANCE;
    }

    @Override
    public void setTwitterSession(TwitterSession twitterSession) {
        this.twitterSession = twitterSession;
    }

    @Override
    public TwitterSession getTwitterSession() {
        if(twitterSession==null){
            twitterSession = Twitter.getSessionManager().getActiveSession();
        }
        return twitterSession;
    }

    private ExampleCodeTwitterApiClient getClient(){
        if(client==null){
            client = new ExampleCodeTwitterApiClient(getTwitterSession());
        }

        return client;
    }

    @Override
    public void getUserInfo(Callback<User> callback){

        getClient().getAPI().show(getUserId(),callback);
    }

    @Override
    public void getFriendsList(long cursor, Callback<FriendListResponse> callback) {

        getClient().getAPI().friends(getUserId(),cursor,callback);

    }

    @Override
    public void getFollowersList(long cursor, Callback<FollowersListResponse> callback) {

        getClient().getAPI().followers(getUserId(),cursor,callback);
    }

    @Override
    public long getUserId() {

        return getTwitterSession().getUserId();

    }
}
