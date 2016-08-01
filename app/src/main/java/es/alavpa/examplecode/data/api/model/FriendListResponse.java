package es.alavpa.examplecode.data.api.model;

import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.models.User;

import java.util.List;

/**
 * Created by alavpa on 1/8/16.
 */
public class FriendListResponse {
    @SerializedName("previous_cursor")
    private
    long previousCursor;
    @SerializedName("next_cursor")
    private
    long nextCursor;
    @SerializedName("users")
    private
    List<User> users;

    public long getPreviousCursor() {
        return previousCursor;
    }

    public long getNextCursor() {
        return nextCursor;
    }

    public List<User> getUsers() {
        return users;
    }
}
