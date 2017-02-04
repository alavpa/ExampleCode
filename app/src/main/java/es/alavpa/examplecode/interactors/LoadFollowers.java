package es.alavpa.examplecode.interactors;

import es.alavpa.examplecode.data.api.model.FollowersListResponse;
import es.alavpa.examplecode.data.repositories.TwitterRemoteRepository;
import es.alavpa.examplecode.data.repositories.TwitterRepository;
import rx.Observable;

/**
 * Created by alavpa on 1/8/16.
 */
public class LoadFollowers extends UseCase<FollowersListResponse> {

    private
    TwitterRepository tr = TwitterRemoteRepository.getInstance();

    private
    long cursor;

    public void setCursor(long cursor) {
        this.cursor = cursor;
    }

    @Override
    public Observable<FollowersListResponse> buildUseCase() {
        return tr.getFollowersList(cursor);
    }
}
