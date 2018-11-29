package com.example.fox.projtest.ui.main;

import java.util.List;

public interface MainView {

    void showProgress();

    void hideProgress();

    void setItems(List<String> items);

    void startInfoActivity(String url, String message);
}