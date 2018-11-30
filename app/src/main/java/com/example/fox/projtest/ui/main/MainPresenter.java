package com.example.fox.projtest.ui.main;

import com.example.fox.projtest.data.repositories.usecases.GetItemUseCase;
import com.example.fox.projtest.di.app.App;
import com.example.fox.projtest.entity.Item;
import com.example.fox.projtest.ui.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainPresenter extends BaseViewModel {

    private MainView mainView;
    private FindItemsInteractor findItemsInteractor;

    @Inject
    public GetItemUseCase getItemUseCase;

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

        getItemUseCase
                .getAll()
                .subscribe(new Observer<List<Item>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(List<Item> items) {
                        onFinished(items);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        findItemsInteractor.findItems(this::onFinished);
    }

    void onItemClicked(String url, String title, String message) {
        if (mainView != null) {
            mainView.startInfoActivity(url, title, message);
        }
    }

    void onDestroy() {
        mainView = null;
    }

    void onFinished(List<Item> items) {
        if (mainView != null) {
            mainView.setItems(items);
            mainView.hideProgress();
        }
    }



    public MainView getMainView() {
        return mainView;
    }


}
