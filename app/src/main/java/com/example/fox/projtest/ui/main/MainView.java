package com.example.fox.projtest.ui.main;

import com.example.fox.projtest.entity.Item;

import java.util.List;

public interface MainView {

    void showProgress();

    void showError(String message);

    void hideProgress();

    void setItems(List<Item> items);

    void startInfoActivity(String url, String title, String message);
}