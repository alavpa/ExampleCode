package es.alavpa.examplecode.ui;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import es.alavpa.examplecode.BuildConfig;
import io.fabric.sdk.android.Fabric;

/**
 * Created by alavpa on 1/8/16.
 */
public class App extends Application{

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.

    private static App application;
    public static App getApplication(){
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new Twitter(authConfig));

        application = this;
    }
}
