package es.alavpa.examplecode.ui.login;

import android.content.Intent;
import android.os.Bundle;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import butterknife.BindView;
import es.alavpa.examplecode.R;
import es.alavpa.examplecode.ui.Navigator;
import es.alavpa.examplecode.ui.base.BaseActivity;

public class LoginActivity extends BaseActivity implements LoginView{

    @BindView(R.id.btn_login)
    TwitterLoginButton btn_login;

    LoginPresenter presenter;
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        navigator = new Navigator();
        presenter = new LoginPresenter(this);
        presenter.init();

        btn_login.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                presenter.onLoginSuccess(result);
            }

            @Override
            public void failure(TwitterException exception) {
                presenter.onLoginFailure(exception);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        btn_login.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void goToMain() {
        navigator.goToMainActivity(this);
    }
}
