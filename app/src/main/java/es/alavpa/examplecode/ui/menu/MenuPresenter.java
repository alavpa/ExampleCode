package es.alavpa.examplecode.ui.menu;

import android.util.Log;

import com.twitter.sdk.android.core.models.User;

import es.alavpa.examplecode.R;
import es.alavpa.examplecode.interactors.LoadProfile;
import es.alavpa.examplecode.interactors.SimpleSubscriber;
import es.alavpa.examplecode.ui.App;
import es.alavpa.examplecode.ui.base.BasePresenter;
import es.alavpa.examplecode.ui.mappers.ProfileViewMapper;
import es.alavpa.examplecode.ui.model.ProfileView;

/**
 * Created by alavpa on 1/8/16.
 */
public class MenuPresenter extends BasePresenter {

    private
    MenuView view;

    private
    LoadProfile loadProfile;

    private
    ProfileViewMapper profileViewMapper;


    public MenuPresenter(MenuView view){
        this.view = view;
        this.loadProfile = new LoadProfile();
        profileViewMapper = new ProfileViewMapper();
        setUseCases(loadProfile);
    }

    public void init(){

        loadProfile.subscribe(new SimpleSubscriber<User>() {

            @Override
            public void onBegin() {

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onSuccess(User result) {
                ProfileView profileView = profileViewMapper.map(result);

                view.loadAvatar(profileView.getAvatar());
                view.loadBg(profileView.getBackground());
                view.setName(profileView.getName());
                view.setNickname("@" + profileView.getNickname());
                view.loadItems(App.getApplication().getResources().getStringArray(R.array.menu_items));

            }

            @Override
            public void onFail(Throwable e) {
                Log.e("Error", e.getMessage());
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
