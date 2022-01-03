package com.example.mvmtvshows.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvmtvshows.repositories.MostPopularTVShowsRepository;
import com.example.mvmtvshows.responses.TVShowsResponse;

public class MostPopularTVShowsViewModel extends ViewModel {

    private MostPopularTVShowsRepository mostPopularTVShowsRepository;

    public MostPopularTVShowsViewModel() {
        mostPopularTVShowsRepository = new MostPopularTVShowsRepository();
    }

    public LiveData<TVShowsResponse> getMostPopularTVShows(int page) {
        return mostPopularTVShowsRepository.getMostPopularTVShows(page);
    }

}
