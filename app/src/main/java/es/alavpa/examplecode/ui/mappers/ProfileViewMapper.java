package es.alavpa.examplecode.ui.mappers;



import com.twitter.sdk.android.core.models.User;

import es.alavpa.examplecode.ui.model.ProfileView;

/**
 * Created by alavpa on 1/8/16.
 */
public class ProfileViewMapper extends Mapper<ProfileView, User> {

    @Override
    public ProfileView map(User user) {
        ProfileView userView = new ProfileView();
        userView.setName(user.name);
        userView.setNickname(user.screenName);
        userView.setAvatar(user.profileImageUrl);
        userView.setBackground(user.profileBackgroundImageUrl);

        return userView;
    }
}
