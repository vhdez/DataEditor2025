package com.example.dataeditor2025;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.text.NumberFormat;
import java.util.Locale;

public class Controller1_TextFields {
    public TextField rankText;
    public TextField peakText;
    public TextField movieTitleText;
    public DatePicker releaseDatePicker;
    public TextField revenueText;
    public TextArea toStringText;
    public Button addImageButton;
    public ImageView movieImageView;
    FileChooser imageFileChooser = new FileChooser();
    int currentMovie = 0;

    public void initialize() throws Exception {
        Film.readFilmData();
        Video.readVideosData();

        Movie firstMovie = Movie.getAllMovies().get(currentMovie);
        updateData(firstMovie);
    }

    void updateData(Movie latestMovie) {
        rankText.setText(String.valueOf(latestMovie.getRank()));
        if (latestMovie instanceof Film) {
            Film firstFilm = (Film) latestMovie;
            peakText.setText(String.valueOf(firstFilm.getPeak()));
        } else {
            peakText.setText("Unknown");
        }
        movieTitleText.setText(latestMovie.getTitle());
        releaseDatePicker.setValue(latestMovie.getReleaseDate());
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        revenueText.setText(currencyFormat.format(latestMovie.getRevenue()));
        toStringText.setText(latestMovie.toString());
        movieImageView.setImage(latestMovie.getImageData());
    }

    public void nextMovie() {
        if (currentMovie < Movie.getAllMovies().size() - 1) {
            currentMovie = currentMovie + 1;
        } else {
            currentMovie = 0;
        }
        Movie nextMovie = Movie.getAllMovies().get(currentMovie);
        updateData(nextMovie);
    }

    public void previousMovie() {
        if (currentMovie > 0) {
            currentMovie = currentMovie - 1;
        } else {
            currentMovie = Movie.getAllMovies().size() - 1;
        }
        Movie prevMovie = Movie.getAllMovies().get(currentMovie);
        updateData(prevMovie);
    }

    public void dataChanged() {
        Movie movieToChange = Movie.getAllMovies().get(currentMovie);
        movieToChange.setTitle(movieTitleText.getText());
        if (movieToChange instanceof Film) {
            Film filmToChange = (Film) movieToChange;
            filmToChange.setPeak(Integer.parseInt(peakText.getText()));
        }
        movieToChange.setRank(Integer.parseInt(rankText.getText()));
        String newRevenue = revenueText.getText();
        newRevenue = newRevenue.replace("$", "");
        newRevenue = newRevenue.replace(",", "");
        movieToChange.setRevenue(Long.parseLong(newRevenue));
        updateData(movieToChange);
    }

    public void editReleaseDate() {
        Movie movieToChange = Movie.getAllMovies().get(currentMovie);
        movieToChange.setReleaseDate(releaseDatePicker.getValue());
        updateData(movieToChange);
    }

    public void addImage() throws Exception {
        Stage mainStage = (Stage)addImageButton.getScene().getWindow();
        File selectedFile = imageFileChooser.showOpenDialog(mainStage);
        FileInputStream input = new FileInputStream(selectedFile);
        Image newImage = new Image(input);

        // store new Image in the selected Model Movie object
        Movie movieToChange = Movie.getAllMovies().get(currentMovie);
        movieToChange.setImageData(newImage);

        // display Image in the GUI
        movieImageView.setImage(newImage);
    }

}
