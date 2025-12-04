package com.example.dataeditor2025;

import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller3_TableView {
    public TableView<Movie> theTable;
    public TableColumn<Movie, Integer> rankColumn;
    public TableColumn<Movie, String> titleColumn;
    public TableColumn<Movie, String> fullTextColumn;

    public void initialize() throws Exception {
        Film.readFilmData();
        Video.readVideosData();

        // This makes the ListView's ObservableList contain the SAME objects as the original ArrayList
        for (Movie eachMovie : Movie.getAllMovies()) {
            theTable.getItems().add(eachMovie);
        }

        // TableView Display: get data values into each row/column/cell
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        fullTextColumn.setCellValueFactory(new PropertyValueFactory<>("toString"));
    }

}
