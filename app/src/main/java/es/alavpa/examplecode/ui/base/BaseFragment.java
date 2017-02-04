package es.alavpa.examplecode.ui.base;

import android.support.v4.app.Fragment;

/**
 * Created by alavpa on 29/01/17.
 */

public class BaseFragment extends Fragment {

    BasePresenter basePresenter;

    public void setBasePresenter(BasePresenter basePresenter) {
        this.basePresenter = basePresenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        basePresenter.unsubscribeUseCases();
    }
}
