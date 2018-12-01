package com.example.fox.projtest.data.net;


import com.example.fox.projtest.data.model.photo.PhotoResponse;
import com.example.fox.projtest.data.model.post.PostResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RestApi {

    @GET("posts")
    Observable<List<PostResponse>> getPosts();

    @GET("rest/?method=flickr.photos.getRecent&format=json&nojsoncallback=1&extras=url_z&api_key=f0e6fbb5fdf1f3842294a1d21f84e8a6&per_page=100&page=1")
    Observable<PhotoResponse> getPhotos();

}
