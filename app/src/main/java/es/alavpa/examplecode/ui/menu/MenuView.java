package es.alavpa.examplecode.ui.menu;

/**
 * Created by alavpa on 1/8/16.
 */
public interface MenuView extends MenuParentView{
    void loadBg(String url);
    void loadAvatar(String url);
    void setName(String name);
    void setNickname(String nickname);

    void loadItems(String... items);
    void goToFriends();
    void goToFollowers();
    int getCurrentPosition();

}
