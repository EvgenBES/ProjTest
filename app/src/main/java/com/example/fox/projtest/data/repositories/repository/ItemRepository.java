package com.example.fox.projtest.data.repositories.repository;

import com.example.fox.projtest.entity.Item;

import java.util.List;

import io.reactivex.Observable;

public interface ItemRepository {

    Observable<List<String>> getPhotos ();

    Observable<List<Item>> getPosts ();
}
