package com.example.fox.projtest.data.repositories.usecases;


import com.example.fox.projtest.data.repositories.repository.ItemRepository;
import com.example.fox.projtest.di.executors.PostExecutionThread;
import com.example.fox.projtest.entity.Item;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetItemUseCase extends BaseUseCase {

    private ItemRepository itemRepository;

    @Inject
    public GetItemUseCase(ItemRepository itemRepository,
                          PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.itemRepository = itemRepository;
    }


    public Observable<List<Item>> getPosts() {
        return itemRepository
                .getPosts()
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }

    public Observable<List<String>> getPhotos() {
        return itemRepository
                .getPhotos()
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
