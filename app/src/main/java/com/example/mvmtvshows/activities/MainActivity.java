package com.example.mvmtvshows.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mvmtvshows.R;
import com.example.mvmtvshows.adapters.TVShowsAdapter;
import com.example.mvmtvshows.databinding.ActivityMainBinding;
import com.example.mvmtvshows.listeners.TVShowsListener;
import com.example.mvmtvshows.models.TVShow;
import com.example.mvmtvshows.responses.TVShowsResponse;
import com.example.mvmtvshows.viewmodels.MostPopularTVShowsViewModel;
import com.example.mvmtvshows.viewmodels.WatchlistViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TVShowsListener {

    private MostPopularTVShowsViewModel viewModel;
    private ActivityMainBinding activityMainBinding;
    private List<TVShow> tvShows = new ArrayList<>();
    private TVShowsAdapter tvShowsAdapter;
    private int currentPage = 1;
    private int totalAvailablePages = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        doIntialization();
    }

    private void doIntialization() {
        activityMainBinding.tvShowsRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        tvShowsAdapter = new TVShowsAdapter(tvShows, this);
        activityMainBinding.tvShowsRecyclerView.setAdapter(tvShowsAdapter);
        activityMainBinding.tvShowsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!activityMainBinding.tvShowsRecyclerView.canScrollVertically(1)) {
                    if (currentPage <= totalAvailablePages) {
                        currentPage += 1;
                        getMostPopularTVShows();
                    }
                }
            }
        });
        activityMainBinding.imageWatchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), WatchlistActivity.class));
            }
        });
        activityMainBinding.imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
            }
        });
        getMostPopularTVShows();
    }


    private void getMostPopularTVShows() {
        toggleLoading();
        viewModel.getMostPopularTVShows(currentPage).observe(this, new Observer<TVShowsResponse>() {
            @Override
            public void onChanged(TVShowsResponse mostPopularTVShowsResponse) {
                MainActivity.this.toggleLoading();
                if (mostPopularTVShowsResponse != null) {
                    totalAvailablePages = mostPopularTVShowsResponse.getTotalPages();
                    if (mostPopularTVShowsResponse.getTvShows() != null) {
                        int oldCount = tvShows.size();
                        tvShows.addAll(mostPopularTVShowsResponse.getTvShows());
                        tvShowsAdapter.notifyItemRangeInserted(oldCount, tvShows.size());
                    }
                }
            }
        });
    }

    private void toggleLoading() {
        if (currentPage == 1) {
            if (activityMainBinding.getIsLoading() != null && activityMainBinding.getIsLoading()) {
                activityMainBinding.setIsLoading(false);
            } else {
                activityMainBinding.setIsLoading(true);
            }
        } else {
            if (activityMainBinding.getIsLoadingMore() != null && activityMainBinding.getIsLoadingMore()) {
                activityMainBinding.setIsLoadingMore(false);
            } else {
                activityMainBinding.setIsLoadingMore(true);
            }
        }
    }

    @Override
    public void onTVShowClicked(TVShow tvShow) {
        Intent intent = new Intent(getApplicationContext(), TVShowDetailsActivity.class);
//        intent.putExtra("id", tvShow.getId());
//        intent.putExtra("name", tvShow.getName());
//        intent.putExtra("startDate", tvShow.getStartDate());
//        intent.putExtra("country", tvShow.getCountry());
//        intent.putExtra("network", tvShow.getNetwork());
//        intent.putExtra("status", tvShow.getStatus());
        intent.putExtra("tvShow", tvShow);
        startActivity(intent);
    }
}