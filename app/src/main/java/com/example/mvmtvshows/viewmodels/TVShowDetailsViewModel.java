package com.example.mvmtvshows.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvmtvshows.database.TVShowsDatabase;
import com.example.mvmtvshows.models.TVShow;
import com.example.mvmtvshows.repositories.TVShowDetailsRepository;
import com.example.mvmtvshows.responses.TVShowDetailsResponse;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class TVShowDetailsViewModel extends AndroidViewModel {

    private final TVShowDetailsRepository tvShowDetailsRepository;
    private final TVShowsDatabase tvShowsDatabase;

    public TVShowDetailsViewModel(@NonNull Application application) {
        super(application);
        tvShowDetailsRepository = new TVShowDetailsRepository();
        tvShowsDatabase = TVShowsDatabase.getTvShowsDatabase(application);
    }

    public LiveData<TVShowDetailsResponse> getTVShowDetails(String tvShowId) {
        return tvShowDetailsRepository.getTVShowDetails(tvShowId);
    }

    public Completable addToWatchlist(TVShow tvShow) {
        return tvShowsDatabase.tvShowDao().addToWatchlist(tvShow);
    }

    public Flowable<TVShow> getTVShowFromWatchlist(String tvShowId) {
        return  tvShowsDatabase.tvShowDao().getTVShowFromWatchlist(tvShowId);
    }

    public Completable removeTVShowFromWatchlist(TVShow tvShow) {
        return tvShowsDatabase.tvShowDao().removeFromWatchlist(tvShow);
    }
}
