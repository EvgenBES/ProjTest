package com.example.fox.projtest.ui.main;

import com.example.fox.projtest.data.repositories.usecases.GetItemUseCase;
import com.example.fox.projtest.di.app.App;
import com.example.fox.projtest.entity.Item;
import com.example.fox.projtest.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainPresenter extends BaseViewModel {

    private MainView mainView;
    private List<Item> listItems = new ArrayList<>();

    @Inject
    public GetItemUseCase getItemUseCase;

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }


    void init () {
        getItemUseCase
                .getPosts()
                .subscribe(new Observer<List<Item>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(List<Item> items) {
                        listItems.addAll(items);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        getItemUseCase
                .getPhotos()
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(List<String> strings) {
                        if (listItems.size() != 0) {
                            for (int i = 0; i < strings.size() - 1; i++) {
                                listItems.get(i).setImageUrl(strings.get(i));
                            }
                        }
                        onFinished(listItems);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
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

}
