package com.example.mvvmrxjava.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmrxjava.model.MovieModel;
import com.example.mvvmrxjava.repository.MovieRepo;

public class MovieViewModel extends ViewModel {

    private final MovieRepo movieRepo;

    public MovieViewModel() {
        movieRepo = new MovieRepo();
    }

    public LiveData<MovieModel> getMovieData() {
        return movieRepo.getMovieList();
    }
}
