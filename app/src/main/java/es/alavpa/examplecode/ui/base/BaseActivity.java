package es.alavpa.examplecode.ui.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by alavpa on 1/8/16.
 */
public class BaseActivity extends AppCompatActivity implements BaseView {

    ProgressDialog progressDialog;
    BasePresenter basePresenter;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void showError(String message) {
        AlertDialog dlg = new AlertDialog.Builder(this)
                .setTitle(android.R.string.dialog_alert_title)
                .setMessage(message)
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();

        dlg.show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    public void startLoader(){
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("loading");
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }
    }

    public void stopLoader(){
        if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.dismiss();
            progressDialog=null;
        }
    }

    public void setPresenter(BasePresenter basePresenter) {
        this.basePresenter = basePresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        basePresenter.unsubscribeUseCases();
    }
}
