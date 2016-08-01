package es.alavpa.examplecode.ui;

import android.os.Bundle;

import es.alavpa.examplecode.R;
import es.alavpa.examplecode.ui.base.BaseActivity;

public class StartActivity extends BaseActivity {

    Navigator nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nav = new Navigator();
        nav.goToLoginActivity(this);
        finish();
    }
}
