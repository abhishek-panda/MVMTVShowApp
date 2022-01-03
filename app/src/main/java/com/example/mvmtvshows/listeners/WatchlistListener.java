package com.example.mvmtvshows.listeners;

import com.example.mvmtvshows.models.TVShow;

public interface WatchlistListener {

    void onTVShowClicked(TVShow tvShow);

    void removeTVShowFromWatchlist(TVShow tvShow, int position);

}
