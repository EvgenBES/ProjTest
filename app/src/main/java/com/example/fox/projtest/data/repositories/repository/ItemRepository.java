package com.example.fox.projtest.data.repositories.repository;

import com.example.fox.projtest.data.model.photo.PhotoResponse;
import com.example.fox.projtest.data.model.post.PostResponse;
import com.example.fox.projtest.entity.Item;

import java.util.List;

import io.reactivex.Observable;

public interface ItemRepository {

    Observable<PhotoResponse> getPhotos ();

    Observable<List<PostResponse>> getPosts ();

    Observable<List<Item>> getAll ();
}
