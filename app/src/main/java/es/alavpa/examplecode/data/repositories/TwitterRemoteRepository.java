package es.alavpa.examplecode.data.repositories;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import es.alavpa.examplecode.data.api.TwitterApiClientProvider;
import es.alavpa.examplecode.data.api.model.FollowersListResponse;
import es.alavpa.examplecode.data.api.model.FriendListResponse;
import rx.Observable;

/**
 * Created by alavpa on 1/8/16.
 */
public class TwitterRemoteRepository implements TwitterRepository {

    private static TwitterRepository INSTANCE;
    private
    TwitterSession twitterSession;
    private
    TwitterApiClientProvider client;

    public static TwitterRepository getInstance(){
        if(INSTANCE==null){
            INSTANCE = new TwitterRemoteRepository();
        }

        return INSTANCE;
    }

    @Override
    public TwitterSession getTwitterSession() {
        if(twitterSession==null){
            twitterSession = Twitter.getSessionManager().getActiveSession();
        }
        return twitterSession;
    }

    @Override
    public void setTwitterSession(TwitterSession twitterSession) {
        this.twitterSession = twitterSession;
    }

    private TwitterApiClientProvider getClient() {
        if(client==null){
            client = new TwitterApiClientProvider(getTwitterSession());
        }

        return client;
    }
    @Override
    public Observable<FriendListResponse> getFriendsList(long cursor) {

        return getClient().getAPI().friends(getUserId(), cursor);

    }

    @Override
    public Observable<FollowersListResponse> getFollowersList(long cursor) {

        return getClient().getAPI().followers(getUserId(), cursor);
    }

    @Override
    public long getUserId() {

        return getTwitterSession().getUserId();

    }

    public Observable<User> getUserInfo() {
        return getClient().getAPI().show(getUserId());
    }
}
