package es.alavpa.examplecode.ui.friends;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;

import java.util.List;

import es.alavpa.examplecode.data.api.model.FriendListResponse;
import es.alavpa.examplecode.interactors.LoadFriends;
import es.alavpa.examplecode.ui.mappers.UserViewMapper;
import es.alavpa.examplecode.ui.model.UserView;
import rx.Subscriber;

/**
 * Created by alavpa on 1/8/16.
 */
public class FriendsPresenter {

    FriendsView view;
    long cursor = -1;

    public FriendsPresenter(FriendsView view){
        this.view = view;
    }

    public void init(){

        loadFriends();
    }

    public void loadFriends(){

        if(view!=null){
            view.startLoader();
        }

        new LoadFriends().execute(cursor, new Callback<FriendListResponse>() {
            @Override
            public void success(Result<FriendListResponse> result) {
                cursor = result.data.getNextCursor();
                new UserViewMapper().execute(result.data.getUsers())
                        .subscribe(new Subscriber<List<UserView>>() {
                            @Override
                            public void onCompleted() {
                                if(view!=null){
                                    view.stopLoader();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                if(view!=null){
                                    view.stopLoader();
                                }
                            }

                            @Override
                            public void onNext(List<UserView> userViews) {
                                if(view!=null){
                                    view.showUsers(userViews);
                                }
                            }
                        });

            }

            @Override
            public void failure(TwitterException exception) {
                if(view!=null){
                    view.stopLoader();
                    view.showError(exception.getMessage());
                }
            }
        });
    }
}
