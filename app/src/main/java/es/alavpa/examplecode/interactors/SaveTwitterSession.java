package es.alavpa.examplecode.interactors;

import com.twitter.sdk.android.core.TwitterSession;

import java.util.concurrent.Callable;

import es.alavpa.examplecode.data.repositories.TwitterRemoteRepository;
import es.alavpa.examplecode.data.repositories.TwitterRepository;
import rx.Observable;

/**
 * Created by alavpa on 1/8/16.
 */
public class SaveTwitterSession {

    private
    TwitterRepository tr = TwitterRemoteRepository.getInstance();


    public Observable<Void> execute(final TwitterSession twitterSession){

        return Observable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                try {
                    tr.setTwitterSession(twitterSession);
                }catch (Throwable t){
                    throw new Exception(t.getMessage());
                }

                return null;
            }
        });

    }
}
