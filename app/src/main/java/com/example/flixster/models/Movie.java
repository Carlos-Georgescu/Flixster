package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    // fields must be public for parceler
    String title;
    String overview;
    String posterPath;
    String backdropPath;
    int voteAverage;

    // no-arg, empty constructor required for Parceler
    public Movie() {}

    public Movie(JSONObject movie) throws JSONException {
        title = movie.getString("title");
        overview = movie.getString("overview");
        posterPath = movie.getString("poster_path");
        backdropPath = movie.getString("backdrop_path");
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public static List<Movie> fromJsonArray(JSONArray moveJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<Movie>();

        for(int i=0;i<moveJsonArray.length();i++)
        {
            movies.add(new Movie(moveJsonArray.getJSONObject(i)));
        }

        return movies;
    }

    public String getPosterPath() {
        //System.out.println(posterPath);
        return String.format("https://image.tmdb.org/t/p/original/%s", posterPath);

    }

    public String getBackdropPath() {
        //System.out.println(posterPath);
        return String.format("https://image.tmdb.org/t/p/original/%s", backdropPath);

    }



    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
