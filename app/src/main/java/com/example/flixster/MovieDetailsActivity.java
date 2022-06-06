package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie;
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    ImageView ratingPicture;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        ratingPicture = (ImageView) findViewById(R.id.ratingPicture);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);
        button  = (Button) findViewById(R.id.button);

        // unwrap the movie passed in via intent, using its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        //System.out.println(movie.getOverview());
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));


        // set the title and overview
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        // vote average is 0..10, convert to 0..5 by dividing by 2

       // float voteAverage = movie.getVoteAverage().floatValue;
       // System.out.println(voteAverage);
        rbVoteAverage.setMinimumWidth(200);
        rbVoteAverage.setNumStars((int) (movie.getVoteAverage() / 2));

        int radius = 100; // corner radius, higher value = more rounded
        Glide.with(this)
                .load(movie.getBackdropPath())
                .placeholder(R.drawable.flicks_backdrop_placeholder)
                .transform(new RoundedCorners(radius))
                .into(ratingPicture);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MovieDetailsActivity.this, MainActivity.class));
            }
        });


    }
}