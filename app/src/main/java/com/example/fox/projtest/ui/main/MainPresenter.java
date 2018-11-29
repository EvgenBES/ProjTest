package com.example.fox.projtest.ui.main;

import com.example.fox.projtest.di.app.App;
import com.example.fox.projtest.ui.base.BaseViewModel;

import java.util.List;

public class MainPresenter extends BaseViewModel {

    private MainView mainView;
    private FindItemsInteractor findItemsInteractor;

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    MainPresenter(MainView mainView, FindItemsInteractor findItemsInteractor) {
        this.mainView = mainView;
        this.findItemsInteractor = findItemsInteractor;
    }

    void onResume() {
        if (mainView != null) {
            mainView.showProgress();
        }

        findItemsInteractor.findItems(this::onFinished);
    }

    void onItemClicked(String url, String message) {
        if (mainView != null) {
            mainView.startInfoActivity(url, message);
        }
    }

    void onDestroy() {
        mainView = null;
    }

    public void onFinished(List<String> items) {
        if (mainView != null) {
            mainView.setItems(items);
            mainView.hideProgress();
        }
    }

    public MainView getMainView() {
        return mainView;
    }


}
