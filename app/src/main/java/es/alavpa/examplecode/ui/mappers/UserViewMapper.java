package es.alavpa.examplecode.ui.mappers;



import com.twitter.sdk.android.core.models.User;

import java.util.List;

import es.alavpa.examplecode.ui.model.UserView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by alavpa on 1/8/16.
 */
public class UserViewMapper {


    public static UserView map(User user){
        UserView userView = new UserView();
        userView.setName(user.name);
        userView.setNickname(user.screenName);
        userView.setUrl(user.profileImageUrl);

        return userView;
    }

    public Observable<List<UserView>> execute(List<User> users){
        return Observable.from(users)
                .flatMap(new Func1<User, Observable<UserView>>() {
                    @Override
                    public Observable<UserView> call(User user) {
                        return Observable.just(map(user));
                    }
                })
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }
}
