package es.alavpa.examplecode.interactors;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.User;

import es.alavpa.examplecode.data.repositories.TwitterRepository;
import es.alavpa.examplecode.data.repositories.TwitterRepositoryImpl;

/**
 * Created by alavpa on 1/8/16.
 */
public class LoadProfile {

    TwitterRepository tr = TwitterRepositoryImpl.getInstance();

    public void execute(Callback<User> callback){
        tr.getUserInfo(callback);
    }
}
