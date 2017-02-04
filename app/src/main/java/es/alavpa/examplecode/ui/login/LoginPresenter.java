package es.alavpa.examplecode.ui.login;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import es.alavpa.examplecode.interactors.SaveTwitterSession;
import rx.Subscriber;

/**
 * Created by alavpa on 1/8/16.
 */
class LoginPresenter {

    private
    LoginView view;

    LoginPresenter(LoginView view) {
        this.view = view;
    }

    public void init(){
        if(isActiveSession()){
            if(view!=null) {
                view.goToFriends();
                view.finish();
            }
        }else{
            setActiveSession();
        }
    }

    private boolean isActiveSession() {
        TwitterSession session = Twitter.getSessionManager().getActiveSession();
        return session!=null;
    }

    private void setActiveSession() {

    }

    void onLoginSuccess(Result<TwitterSession> result) {

        //save session
        new SaveTwitterSession()
                .execute(result.data)
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        if(view!=null){
                            view.goToFriends();
                            view.finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(view!=null){
                            view.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(Void aVoid) {

                    }
                });



    }

    void onLoginFailure(TwitterException e) {

        view.showError(e.getMessage());
    }

    public void saveSession(){

    }
}
