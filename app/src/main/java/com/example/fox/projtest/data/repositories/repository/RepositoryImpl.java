package com.example.fox.projtest.data.repositories.repository;

import com.example.fox.projtest.data.model.photo.Photo;
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
    public Observable<List<String>> getPhotos() {
        return restService
                .getPhotos()
                .map(new Function<PhotoResponse, List<String>>() {
                    @Override
                    public List<String> apply(PhotoResponse photoResponse) throws Exception {
                        final List<String> listUrl = new ArrayList<>();
                        List<Photo> listPhoto = new ArrayList<>();
                        listPhoto = photoResponse.getPhotos().getPhoto();
                        for (Photo photo : listPhoto) {
                            listUrl.add(photo.getUrlZ());
                        }
                        return listUrl;
                    }
                });
    }

    @Override
    public Observable<List<Item>> getPosts() {
        return restService
                .getPosts()
                .map(new Function<List<PostResponse>, List<Item>>() {
                    @Override
                    public List<Item> apply(List<PostResponse> postResponses) throws Exception {
                        List<Item> itemPostList = new ArrayList<>();
                        for (PostResponse post : postResponses) {
                            itemPostList.add(new Item(post.getTitle(), post.getBody(), ""));
                        }
                        return itemPostList;
                    }
                });
    }
}

