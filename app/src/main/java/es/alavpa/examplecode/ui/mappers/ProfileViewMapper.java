package es.alavpa.examplecode.ui.mappers;



import com.twitter.sdk.android.core.models.User;

import es.alavpa.examplecode.ui.model.ProfileView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by alavpa on 1/8/16.
 */
public class ProfileViewMapper {


    public static ProfileView map(User user){
        ProfileView userView = new ProfileView();
        userView.setName(user.name);
        userView.setNickname(user.screenName);
        userView.setAvatar(user.profileImageUrl);
        userView.setBackground(user.profileBackgroundImageUrl);

        return userView;
    }

    public Observable<ProfileView> execute(final User user){

        return Observable.just(user)
                .flatMap(new Func1<User, Observable<ProfileView>>() {
                    @Override
                    public Observable<ProfileView> call(User user) {
                        return Observable.just(map(user));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }
}
