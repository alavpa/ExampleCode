package es.alavpa.examplecode.interactors;

import com.twitter.sdk.android.core.models.User;

import es.alavpa.examplecode.data.repositories.TwitterRemoteRepository;
import es.alavpa.examplecode.data.repositories.TwitterRepository;
import rx.Observable;

/**
 * Created by alavpa on 1/8/16.
 */
public class LoadProfile extends UseCase<User> {

    private
    TwitterRepository tr = TwitterRemoteRepository.getInstance();

    @Override
    public Observable<User> buildUseCase() {
        return tr.getUserInfo();
    }
}
