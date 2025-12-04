package com.example.dataeditor2025;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller2_ListView {
    public ListView<Movie> allMoviesListView;
    public TextField rankTextField;
    public TextField titleTextField;

    public void initialize() throws Exception {
        Film.readFilmData();
        Video.readVideosData();

        // This makes the ListView's ObservableList contain the SAME objects as the original ArrayList
        for (Movie eachMovie : Movie.getAllMovies()) {
            allMoviesListView.getItems().add(eachMovie);
        }

        // when user selects a row in list, update the text fields where the values will be edited
        allMoviesListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    // oldValue can be null if nothing WAS selected
                    // newValue can be null if nothing IS NOW selected
                    if (newValue != null) {
                        rankTextField.setText(String.valueOf(newValue.getRank()));
                        titleTextField.setText(newValue.getTitle());
                    } else {
                        rankTextField.setText("");
                        titleTextField.setText("");
                    }
                });
    }

}
