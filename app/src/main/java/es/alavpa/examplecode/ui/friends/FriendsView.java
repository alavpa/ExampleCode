package es.alavpa.examplecode.ui.friends;

import java.util.List;

import es.alavpa.examplecode.ui.base.BaseView;
import es.alavpa.examplecode.ui.model.UserView;

/**
 * Created by alavpa on 1/8/16.
 */
public interface FriendsView extends BaseView{

    void showUsers(List<UserView> users);
}
