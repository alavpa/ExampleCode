package es.alavpa.examplecode.ui.friends;

import java.util.List;

import es.alavpa.examplecode.data.api.model.FriendListResponse;
import es.alavpa.examplecode.interactors.LoadFriends;
import es.alavpa.examplecode.interactors.SimpleSubscriber;
import es.alavpa.examplecode.ui.base.BasePresenter;
import es.alavpa.examplecode.ui.mappers.UserViewMapper;
import es.alavpa.examplecode.ui.model.UserView;

/**
 * Created by alavpa on 1/8/16.
 */
public class FriendsPresenter extends BasePresenter {

    private
    FriendsView view;

    private
    long cursor = -1;

    private
    LoadFriends loadFriends;

    private
    UserViewMapper userViewMapper;

    public FriendsPresenter(FriendsView view){
        this.view = view;
        loadFriends = new LoadFriends();
        this.userViewMapper = new UserViewMapper();
        setUseCases(loadFriends);
    }

    public void init(){

        loadFriends();
    }

    public void reload(){
        if(cursor!=0){
            loadFriends();
        }
    }

    private void loadFriends() {

        loadFriends.setCursor(cursor);

        loadFriends.subscribe(new SimpleSubscriber<FriendListResponse>() {
            @Override
            public void onBegin() {
                view.startLoader();
            }

            @Override
            public void onSuccess(FriendListResponse result) {
                List<UserView> userViews = userViewMapper.map(result.getUsers());
                view.showUsers(userViews);
                cursor = result.getNextCursor();
            }

            @Override
            public void onFail(Throwable e) {
                view.showError(e.getMessage());
            }

            @Override
            public void onCompleted() {
                view.stopLoader();
            }
        });
    }
}
