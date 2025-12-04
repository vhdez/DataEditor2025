package com.example.dataeditor2025;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller1_TextFields {
    public TextField movieTitleText;
    public TextArea toStringText;
    int currentMovie = 0;

    public void initialize() throws Exception {
        Film.readFilmData();
        Video.readVideosData();

        Movie firstMovie = Movie.getAllMovies().get(currentMovie);
        movieTitleText.setText(firstMovie.getTitle());
        toStringText.setText(firstMovie.toString());
    }

    public void nextMovie() {
        currentMovie = currentMovie + 1;
        Movie nextMovie = Movie.getAllMovies().get(currentMovie);
        movieTitleText.setText(nextMovie.getTitle());
        toStringText.setText(nextMovie.toString());
    }

    public void previousMovie() {
        currentMovie = currentMovie - 1;
        Movie prevMovie = Movie.getAllMovies().get(currentMovie);
        movieTitleText.setText(prevMovie.getTitle());
        toStringText.setText(prevMovie.toString());
    }

}
