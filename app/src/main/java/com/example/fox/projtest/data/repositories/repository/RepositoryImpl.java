package com.example.fox.projtest.data.repositories.repository;

import com.example.fox.projtest.data.model.photo.PhotoResponse;
import com.example.fox.projtest.data.model.post.PostResponse;
import com.example.fox.projtest.data.net.RestService;
import com.example.fox.projtest.entity.Item;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class RepositoryImpl implements ItemRepository {

    private RestService restService;

    @Inject
    public RepositoryImpl(RestService restService) {
        this.restService = restService;
    }

    @Override
    public Observable<PhotoResponse> getPhotos() {
        return null;


    }

    @Override
    public Observable<List<PostResponse>> getPosts() {
        return null;
    }

    @Override
    public Observable<List<Item>> getAll() {
//        List<String> listPhotos = new ArrayList<>();
//
//        restService
//                .getPhotos()
//                .map(new Function<PhotoResponse, List<String>>() {
//                    @Override
//                    public List<String> apply(PhotoResponse photoResponse) throws Exception {
//                        for (int i = 0; i < photoResponse.getPhotos().getPhoto().size() - 1; i++) {
//                            listPhotos.add(photoResponse.getPhotos().getPhoto().get(i).getUrlZ());
//                        }
//                        return null;
//                    }
//                });
//
//        return restService
//                .getPosts()
//                .map(new Function<List<PostResponse>, List<Item>>() {
//                    @Override
//                    public List<Item> apply(List<PostResponse> postResponses) throws Exception {
//                        List<Item> itemList = new ArrayList<>();
//                        for (int i = 0; i < postResponses.size() - 1; i++) {
//                            if (i < listPhotos.size() - 1) {
//                                itemList.add(new Item(
//                                        postResponses.get(i).getTitle(),
//                                        postResponses.get(i).getBody(),
//                                        listPhotos.get(i)));
//                            }
//                        }
//                        return itemList;
//                    }
//                });

        return restService
                .getPosts()
                .map(postResponses -> {
                    List<Item> itemList = new ArrayList<>();
                    for (PostResponse post: postResponses){
                        itemList.add(new Item(post.getTitle(), post.getBody(), ""));
                    }
                    return itemList;
                });
    }

}
