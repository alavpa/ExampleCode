package es.alavpa.examplecode.data.api;

import com.twitter.sdk.android.core.models.User;

import es.alavpa.examplecode.data.api.model.FollowersListResponse;
import es.alavpa.examplecode.data.api.model.FriendListResponse;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by alavpa on 1/8/16.
 */
public interface TwitterApi {

    @GET("/1.1/friends/list.json")
    Observable<FriendListResponse> friends(@Query("user_id") long id, @Query("cursor") long cursor);

    @GET("/1.1/followers/list.json")
    Observable<FollowersListResponse> followers(@Query("user_id") long id, @Query("cursor") long cursor);

    @GET("/1.1/users/show.json")
    Observable<User> show(@Query("user_id") long id);
}
