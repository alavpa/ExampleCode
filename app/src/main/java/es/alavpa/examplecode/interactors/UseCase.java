package es.alavpa.examplecode.interactors;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by alavpa on 29/01/17.
 */

public abstract class UseCase<T> {

    private CompositeSubscription subscriptions;

    protected UseCase() {
        subscriptions = new CompositeSubscription();
    }

    public void subscribe(Subscriber<T> subscriber) {
        Subscription subscription = buildUseCase()
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

        subscriptions.add(subscription);
    }

    public abstract Observable<T> buildUseCase();

    public void clearSubscriptions() {
        if (!subscriptions.isUnsubscribed()) {
            subscriptions.unsubscribe();
        }
    }
}
