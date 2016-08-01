package es.alavpa.examplecode.data.api;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.User;

import es.alavpa.examplecode.data.api.model.FollowersListResponse;
import es.alavpa.examplecode.data.api.model.FriendListResponse;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by alavpa on 1/8/16.
 */
public interface ExampleCodeTwitterApi {

    @GET("/1.1/users/show.json")
    void show(@Query("user_id") long id, Callback<User> cb);

    @GET("/1.1/friends/list.json")
    void friends(@Query("user_id") long id,@Query("cursor") long cursor, Callback<FriendListResponse> cb);

    @GET("/1.1/followers/list.json")
    void followers(@Query("user_id") long id,@Query("cursor") long cursor, Callback<FollowersListResponse> cb);
}
