package es.alavpa.examplecode.interactors;

import com.twitter.sdk.android.core.TwitterSession;

import es.alavpa.examplecode.data.repositories.TwitterRepository;
import es.alavpa.examplecode.data.repositories.TwitterRepositoryImpl;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by alavpa on 1/8/16.
 */
public class SaveTwitterSession {

    TwitterRepository tr = TwitterRepositoryImpl.getInstance();


    public Observable<Void> execute(final TwitterSession twitterSession){
        return Observable.defer(new Func0<Observable<Void>>() {
            @Override
            public Observable<Void> call() {
                try {
                    tr.setTwitterSession(twitterSession);
                }catch (Throwable t){
                    return Observable.error(t);
                }
                return Observable.empty();
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }
}
