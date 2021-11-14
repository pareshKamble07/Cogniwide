package com.example.mvvmrxjava.network;

import com.example.mvvmrxjava.model.MovieModel;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("3/movie/popular?api_key=6e63c2317fbe963d76c3bdc2b785f6d1&page=1")
    Observable<MovieModel> getMovieData();

}
