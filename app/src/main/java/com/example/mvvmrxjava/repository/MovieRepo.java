package com.example.mvvmrxjava.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmrxjava.model.MovieModel;
import com.example.mvvmrxjava.network.ApiInterface;
import com.example.mvvmrxjava.utils.Common;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MovieRepo {

    private static final String TAG = "MovieRepo";

    private ApiInterface apiInterface;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MovieRepo() {
        this.apiInterface = Common.getApiInterface();
    }

    public MutableLiveData<MovieModel> getMovieList()
    {
        MutableLiveData<MovieModel> data = new MutableLiveData<>();

        compositeDisposable.add(apiInterface.getMovieData()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(movieModel ->
        {
            if (movieModel != null)
            {
                data.setValue(movieModel);
            }
        }));

        return data;

    }
}
