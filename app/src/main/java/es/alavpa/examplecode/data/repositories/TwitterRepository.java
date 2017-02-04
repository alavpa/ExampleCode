package es.alavpa.examplecode.data.repositories;

import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import es.alavpa.examplecode.data.api.model.FollowersListResponse;
import es.alavpa.examplecode.data.api.model.FriendListResponse;
import rx.Observable;

/**
 * Created by alavpa on 1/8/16.
 */
public interface TwitterRepository {

    TwitterSession getTwitterSession();

    void setTwitterSession(TwitterSession twitterSession);

    Observable<User> getUserInfo();

    Observable<FriendListResponse> getFriendsList(long cursor);

    Observable<FollowersListResponse> getFollowersList(long cursor);
    long getUserId();



}
