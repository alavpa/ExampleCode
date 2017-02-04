package es.alavpa.examplecode.ui.base;

import es.alavpa.examplecode.interactors.UseCase;

/**
 * Created by alavpa on 29/01/17.
 */

public class BasePresenter {
    private UseCase[] useCases;

    protected void setUseCases(UseCase... useCases) {
        this.useCases = useCases;
    }

    protected void unsubscribeUseCases() {
        if (useCases != null) {
            for (UseCase useCase : useCases) {
                useCase.clearSubscriptions();
            }
        }
    }
}
