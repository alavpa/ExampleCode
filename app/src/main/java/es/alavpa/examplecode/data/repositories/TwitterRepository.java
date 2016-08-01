package es.alavpa.examplecode.data.repositories;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import es.alavpa.examplecode.data.api.model.FollowersListResponse;
import es.alavpa.examplecode.data.api.model.FriendListResponse;

/**
 * Created by alavpa on 1/8/16.
 */
public interface TwitterRepository {

    void setTwitterSession(TwitterSession twitterSession);
    TwitterSession getTwitterSession();
    void getUserInfo(Callback<User> callback);
    void getFriendsList(long cursor, Callback<FriendListResponse> callback);
    void getFollowersList(long cursor, Callback<FollowersListResponse> callback);
    long getUserId();



}
