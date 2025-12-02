package com.example.dataeditor2025;

import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller2 {
    public ListView<Movie> allMoviesList;

    public void initialize() throws Exception {
        Film.readFilmData();
        Video.readVideosData();

        // This makes the ListView's ObservableList contain the SAME objects as the original ArrayList
        for (Movie eachMovie : Movie.getAllMovies()) {
            allMoviesList.getItems().add(eachMovie);
        }

    }

}
