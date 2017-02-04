package es.alavpa.examplecode.ui.followers;

import java.util.List;

import es.alavpa.examplecode.data.api.model.FollowersListResponse;
import es.alavpa.examplecode.interactors.LoadFollowers;
import es.alavpa.examplecode.interactors.SimpleSubscriber;
import es.alavpa.examplecode.ui.base.BasePresenter;
import es.alavpa.examplecode.ui.mappers.UserViewMapper;
import es.alavpa.examplecode.ui.model.UserView;

/**
 * Created by alavpa on 1/8/16.
 */
public class FollowersPresenter extends BasePresenter {

    private
    FollowersView view;

    private
    long cursor = -1;

    private
    LoadFollowers loadFollowers;

    private
    UserViewMapper userViewMapper;

    public FollowersPresenter(FollowersView view) {
        this.view = view;
        loadFollowers = new LoadFollowers();
        userViewMapper = new UserViewMapper();

        setUseCases(loadFollowers);
    }

    public void init() {

        loadFollowers();
    }

    public void reload() {
        if (cursor != 0) {
            loadFollowers();
        }
    }

    private void loadFollowers() {

        loadFollowers.setCursor(cursor);

        loadFollowers.subscribe(new SimpleSubscriber<FollowersListResponse>() {

            @Override
            public void onBegin() {
                view.startLoader();
            }

            @Override
            public void onCompleted() {
                view.stopLoader();
            }

            @Override
            public void onSuccess(FollowersListResponse result) {

                List<UserView> userViews = userViewMapper.map(result.getUsers());
                view.showUsers(userViews);
                cursor = result.getNextCursor();

            }

            @Override
            public void onFail(Throwable e) {

                view.showError(e.getMessage());

            }
        });
    }
}
