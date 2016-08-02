package es.alavpa.examplecode.ui.followers;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;

import java.util.List;

import es.alavpa.examplecode.data.api.model.FollowersListResponse;
import es.alavpa.examplecode.interactors.LoadFollowers;
import es.alavpa.examplecode.ui.mappers.UserViewMapper;
import es.alavpa.examplecode.ui.model.UserView;
import rx.Subscriber;

/**
 * Created by alavpa on 1/8/16.
 */
public class FollowersPresenter {

    FollowersView view;
    long cursor = -1;

    public FollowersPresenter(FollowersView view){
        this.view = view;
    }

    public void init(){

        loadFollowers();
    }

    public void reload(){
        if(cursor!=0){
            loadFollowers();
        }
    }

    public void loadFollowers(){

        if(view!=null){
            view.startLoader();
        }

        new LoadFollowers().execute(cursor, new Callback<FollowersListResponse>() {
            @Override
            public void success(Result<FollowersListResponse> result) {
                cursor = result.data.getNextCursor();
                new UserViewMapper().execute(result.data.getUsers())
                        .subscribe(new Subscriber<List<UserView>>() {
                            @Override
                            public void onCompleted() {
                                if (view != null) {
                                    view.stopLoader();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (view != null) {
                                    view.stopLoader();
                                }
                            }

                            @Override
                            public void onNext(List<UserView> userViews) {
                                if (view != null) {
                                    view.showUsers(userViews);
                                }
                            }
                        });

            }

            @Override
            public void failure(TwitterException exception) {
                if (view != null) {
                    view.stopLoader();
                    view.showError(exception.getMessage());
                }
            }
        });

    }
}
