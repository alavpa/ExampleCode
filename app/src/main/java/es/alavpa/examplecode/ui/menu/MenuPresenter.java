package es.alavpa.examplecode.ui.menu;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.User;

import es.alavpa.examplecode.R;
import es.alavpa.examplecode.interactors.LoadProfile;
import es.alavpa.examplecode.ui.App;
import es.alavpa.examplecode.ui.mappers.ProfileViewMapper;
import es.alavpa.examplecode.ui.model.ProfileView;
import rx.Subscriber;

/**
 * Created by alavpa on 1/8/16.
 */
public class MenuPresenter {

    MenuView view;
    public MenuPresenter(MenuView view){
        this.view = view;
    }

    public void init(){

        new LoadProfile().execute(new Callback<User>() {
            @Override
            public void success(Result<User> result) {
                new ProfileViewMapper()
                        .execute(result.data)
                        .subscribe(new Subscriber<ProfileView>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(ProfileView profileView) {
                                view.loadAvatar(profileView.getAvatar());
                                view.loadBg(profileView.getBackground());
                                view.setName(profileView.getName());
                                view.setNickname("@" + profileView.getNickname());
                                view.loadItems(App.getApplication().getResources().getStringArray(R.array.menu_items));
                            }
                        });
            }

            @Override
            public void failure(TwitterException exception) {

            }
        });
    }

    public void onClickFriends(){
        if(view!=null){
            if(view.getCurrentPosition()!=MenuFragment.MENU_FRIENDS) {
                view.goToFriends();
            }
            view.hideMenu();
        }
    }

    public void onClickFollowers(){
        if(view!=null){
            if(view.getCurrentPosition()!=MenuFragment.MENU_FOLLOWERS) {
                view.goToFollowers();
            }
            view.hideMenu();
        }
    }


}
