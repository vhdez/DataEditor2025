package com.example.dataeditor2025;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LongStringConverter;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;

public class Controller3_TableView extends Controller0_SuperController {
    public TableView<Film> theTable;
    public TableColumn<Film, Integer> rankColumn;
    public TableColumn<Film, Integer> peakColumn;
    public TableColumn<Film, String> titleColumn;
    public TableColumn<Film, LocalDate> releaseDateColumn;
    public TableColumn<Film, Long> revenueColumn;
    public TextArea toStringText;
    public Button addImageButton;
    public ImageView movieImageView;
    public ChoiceBox<String> viewChoiceBox;
    FileChooser imageFileChooser = new FileChooser();

    public void initialize() throws Exception {
        super.initialize();
        super.setChoice(viewChoiceBox, "Table");

        // TableView Display: get data values into each row/column/cell
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        peakColumn.setCellValueFactory(new PropertyValueFactory<>("peak"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        revenueColumn.setCellValueFactory(new PropertyValueFactory<>("revenue"));

        // TableView Display: detect when a row is selected
        theTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    // oldValue can be null if nothing WAS selected
                    // newValue can be null if nothing IS NOW selected
                    if (newValue == null) {
                        return;
                    }
                    System.out.println("SELECTED ROW FOR: " + newValue);
                    toStringText.setText(newValue.toString());
                    movieImageView.setImage(newValue.getImageData());
                });

        // TableView Edit: when user double-clicks on cell, turn it into a TextField for editing
        rankColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        peakColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        releaseDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        revenueColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));

        // TableView Edit: when user is done editing, store new value in selected data object
        rankColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Film, Integer> t) -> {
                    int selectedRow = t.getTablePosition().getRow();
                    Film selectedFilm = t.getTableView().getItems().get(selectedRow);
                    selectedFilm.setRank(t.getNewValue());
                    toStringText.setText(selectedFilm.toString());
                });
        peakColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Film, Integer> t) -> {
                    int selectedRow = t.getTablePosition().getRow();
                    Film selectedFilm = t.getTableView().getItems().get(selectedRow);
                    selectedFilm.setPeak(t.getNewValue());
                    toStringText.setText(selectedFilm.toString());
                });
        titleColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Film, String> t) -> {
                    int selectedRow = t.getTablePosition().getRow();
                    Film selectedFilm = t.getTableView().getItems().get(selectedRow);
                    selectedFilm.setTitle(t.getNewValue());
                    toStringText.setText(selectedFilm.toString());
                });
        releaseDateColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Film, LocalDate> t) -> {
                    int selectedRow = t.getTablePosition().getRow();
                    Film selectedFilm = t.getTableView().getItems().get(selectedRow);
                    selectedFilm.setReleaseDate(t.getNewValue());
                    toStringText.setText(selectedFilm.toString());
                });
        revenueColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Film, Long> t) -> {
                    int selectedRow = t.getTablePosition().getRow();
                    Film selectedFilm = t.getTableView().getItems().get(selectedRow);
                    selectedFilm.setRevenue(t.getNewValue());
                    toStringText.setText(selectedFilm.toString());
                });

        // This makes the ListView's ObservableList contain the SAME objects as the original ArrayList
        for (Film eachFilm : Film.getAllFilms()) {
            theTable.getItems().add(eachFilm);
        }
        theTable.getSelectionModel().select(0);
    }

    public void addImage() throws Exception {
        Stage mainStage = (Stage)addImageButton.getScene().getWindow();
        File selectedFile = imageFileChooser.showOpenDialog(mainStage);
        FileInputStream input = new FileInputStream(selectedFile);
        Image newImage = new Image(input);

        // store new Image in the selected Model Movie object
        Movie movieToChange = theTable.getSelectionModel().getSelectedItem();
        movieToChange.setImageData(newImage);

        // display Image in the GUI
        movieImageView.setImage(newImage);
    }

}
