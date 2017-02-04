package es.alavpa.examplecode.interactors;

import es.alavpa.examplecode.data.api.model.FriendListResponse;
import es.alavpa.examplecode.data.repositories.TwitterRemoteRepository;
import es.alavpa.examplecode.data.repositories.TwitterRepository;
import rx.Observable;

/**
 * Created by alavpa on 1/8/16.
 */
public class LoadFriends extends UseCase<FriendListResponse> {

    private
    TwitterRepository tr = TwitterRemoteRepository.getInstance();

    private
    long cursor;

    public void setCursor(long cursor) {
        this.cursor = cursor;
    }

    @Override
    public Observable<FriendListResponse> buildUseCase() {
        return tr.getFriendsList(cursor);
    }
}
