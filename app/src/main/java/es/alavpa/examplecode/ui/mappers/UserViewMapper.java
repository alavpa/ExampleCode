package es.alavpa.examplecode.ui.mappers;



import com.twitter.sdk.android.core.models.User;

import es.alavpa.examplecode.ui.model.UserView;

/**
 * Created by alavpa on 1/8/16.
 */
public class UserViewMapper extends Mapper<UserView, User> {

    @Override
    public UserView map(User user) {
        UserView userView = new UserView();
        userView.setName(user.name);
        userView.setNickname(user.screenName);
        userView.setUrl(user.profileImageUrl);

        return userView;
    }


}
