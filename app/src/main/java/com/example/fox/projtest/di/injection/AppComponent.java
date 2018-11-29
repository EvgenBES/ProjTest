package com.example.fox.projtest.di.injection;


import com.example.fox.projtest.ui.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void runInject(MainPresenter mainPresenter);
}