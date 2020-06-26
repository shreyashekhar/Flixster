package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.flixster.models.Movie;

import org.parceler.Parcels;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie;

    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVotingAverage;
    TextView adult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        //assign view objects
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        rbVotingAverage = (RatingBar) findViewById(R.id.rbVoteAverage);
        adult = (TextView) findViewById(R.id.adult);

        //unwrap movie passed in as intent, and use simple name as key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        //set Title and Overview from movie
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        //set ratingbar by dividing vote average by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        rbVotingAverage.setRating(voteAverage = voteAverage > 0 ? voteAverage / 2.0f : voteAverage);

        //set adult rating based on true/false
        if (movie.isAdult()) {
            adult.setText("Adult Rating");
        } else {
            adult.setText("Anyone can watch");
        }

    }
}