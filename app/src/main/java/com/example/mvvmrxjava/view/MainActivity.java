package com.example.mvvmrxjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mvvmrxjava.R;
import com.example.mvvmrxjava.adapter.MoviesAdapter;
import com.example.mvvmrxjava.model.MovieModel;
import com.example.mvvmrxjava.model.Result;
import com.example.mvvmrxjava.utils.Util;
import com.example.mvvmrxjava.viewmodel.MovieViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;


import static androidx.core.content.ContextCompat.getSystemService;


public class MainActivity extends AppCompatActivity {

    MovieViewModel movieViewModel;

    RecyclerView rvMovies;
    MoviesAdapter moviesAdapter;
    LinearLayoutManager linearLayout;
    List<Result> movieList = new ArrayList<>();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle(getString(R.string.please_wait));
        progressDialog.setCanceledOnTouchOutside(false);

        rvMovies = findViewById(R.id.rvMovies);
        linearLayout = new LinearLayoutManager(this);
        rvMovies.setLayoutManager(linearLayout);

        if (Util.isNetworkConnected(this)) {

            progressDialog.show();

            movieViewModel.getMovieData().observe(this, movieModel ->
            {
                Gson gson = new GsonBuilder().create();

                String res = gson.toJson(movieModel, MovieModel.class);

                try {
                    JSONObject jsonObject = new JSONObject(res);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    Log.e("JSONArrayyyyyyy", jsonArray.toString());

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsons = jsonArray.getJSONObject(i);

                        Result result = new Result();
                        result.setTitle(jsons.optString("title", ""));
                        result.setReleaseDate(jsons.optString("release_date", ""));
                        result.setPosterPath(jsons.optString("poster_path", ""));


                        movieList.add(result);
                    }

                    progressDialog.cancel();
                    moviesAdapter = new MoviesAdapter(this, movieList);
                    rvMovies.setAdapter(moviesAdapter);
                    moviesAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    progressDialog.cancel();
                    e.printStackTrace();
                }


            });
        }else
        {
            progressDialog.cancel();
            Toast.makeText(this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}