package es.alavpa.examplecode.ui.base;

/**
 * Created by alavpa on 1/8/16.
 */
public interface BaseView {

    void finish();
    void showError(String message);
    void showMessage(String message);
    void startLoader();
    void stopLoader();


}
