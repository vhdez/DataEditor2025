package com.example.dataeditor2025;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;

public class Controller2_ListView extends Controller0_SuperController {
    public ListView<Video> allMoviesListView;
    public ChoiceBox<String> fieldToEditChoiceBox;
    public TextField editTextField;
    public Button updateButton;
    public ChoiceBox<String> viewChoiceBox;
    public Button addImageButton;
    public ImageView movieImageView;
    FileChooser imageFileChooser = new FileChooser();

    public void initialize() throws Exception {
        super.initialize();
        super.setChoice(viewChoiceBox, "List");

        fieldToEditChoiceBox.getItems().add("Rank");
        fieldToEditChoiceBox.getItems().add("Title");
        fieldToEditChoiceBox.getItems().add("Revenue");
        fieldToEditChoiceBox.getItems().add("Release Date");
        fieldToEditChoiceBox.getItems().add("All Sales #");
        fieldToEditChoiceBox.getItems().add("VHS Sales #");
        fieldToEditChoiceBox.getItems().add("DVD Sales #");
        fieldToEditChoiceBox.getItems().add("Blu-Ray Sales #");
        fieldToEditChoiceBox.getSelectionModel().select(0);

        // when user selects a row in list, update the text fields where the values will be edited
        allMoviesListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, selectedVideo) -> {
                    // oldValue can be null if nothing WAS selected
                    // newValue can be null if nothing IS NOW selected
                    if (selectedVideo != null) {
                        movieImageView.setImage(selectedVideo.getImageData());
                        String selectedField = fieldToEditChoiceBox.getSelectionModel().getSelectedItem();
                        if (selectedField.equalsIgnoreCase("Rank")) {
                            editTextField.setText(String.valueOf(selectedVideo.getRank()));
                        } else if (selectedField.equalsIgnoreCase("Title")) {
                            editTextField.setText(String.valueOf(selectedVideo.getTitle()));
                        } else if (selectedField.equalsIgnoreCase("Revenue")) {
                            editTextField.setText(String.valueOf(selectedVideo.getRevenue()));
                        } else if (selectedField.equalsIgnoreCase("Release Date")) {
                            editTextField.setText(String.valueOf(selectedVideo.getReleaseDate()));
                        } else if (selectedField.equalsIgnoreCase("All Sales #")) {
                            editTextField.setText(String.valueOf(selectedVideo.getAllSales()));
                        } else if (selectedField.equalsIgnoreCase("VHS Sales #")) {
                            editTextField.setText(String.valueOf(selectedVideo.getVhsSales()));
                        } else if (selectedField.equalsIgnoreCase("DVD Sales #")) {
                            editTextField.setText(String.valueOf(selectedVideo.getDvdSales()));
                        } else if (selectedField.equalsIgnoreCase("Blu-Ray Sales #")) {
                            editTextField.setText(String.valueOf(selectedVideo.getBluraySales()));
                        }
                    } else {
                        editTextField.setText("");
                    }
                });

        fieldToEditChoiceBox.setOnAction((event) -> {
            Video selectedVideo = allMoviesListView.getSelectionModel().getSelectedItem();
            String selectedField = fieldToEditChoiceBox.getSelectionModel().getSelectedItem();
            if (selectedField.equalsIgnoreCase("Rank")) {
                editTextField.setText(String.valueOf(selectedVideo.getRank()));
            } else if (selectedField.equalsIgnoreCase("Title")) {
                editTextField.setText(String.valueOf(selectedVideo.getTitle()));
            } else if (selectedField.equalsIgnoreCase("Revenue")) {
                editTextField.setText(String.valueOf(selectedVideo.getRevenue()));
            } else if (selectedField.equalsIgnoreCase("Release Date")) {
                editTextField.setText(String.valueOf(selectedVideo.getReleaseDate()));
            } else if (selectedField.equalsIgnoreCase("All Sales #")) {
                editTextField.setText(String.valueOf(selectedVideo.getAllSales()));
            } else if (selectedField.equalsIgnoreCase("VHS Sales #")) {
                editTextField.setText(String.valueOf(selectedVideo.getVhsSales()));
            } else if (selectedField.equalsIgnoreCase("DVD Sales #")) {
                editTextField.setText(String.valueOf(selectedVideo.getDvdSales()));
            } else if (selectedField.equalsIgnoreCase("Blu-Ray Sales #")) {
                editTextField.setText(String.valueOf(selectedVideo.getBluraySales()));
            }
        });

        // This makes the ListView's ObservableList contain the SAME objects as the original ArrayList
        for (Video eachVideo : Video.getAllVideos()) {
            allMoviesListView.getItems().add(eachVideo);
        }

        // Start with first movie selected and it's image displayed
        allMoviesListView.getSelectionModel().select(0);
        movieImageView.setImage(Video.getAllVideos().get(0).getImageData());
    }

    public void updateData() {
        Video selectedVideo = allMoviesListView.getSelectionModel().getSelectedItem();
        String selectedField = fieldToEditChoiceBox.getSelectionModel().getSelectedItem();
        if (selectedField.equalsIgnoreCase("Rank")) {
            selectedVideo.setRank(Integer.parseInt(editTextField.getText()));
        } else if (selectedField.equalsIgnoreCase("Title")) {
            selectedVideo.setTitle(editTextField.getText());
        } else if (selectedField.equalsIgnoreCase("Revenue")) {
            selectedVideo.setRevenue(Long.parseLong(editTextField.getText()));
        } else if (selectedField.equalsIgnoreCase("Release Date")) {
            //editTextField.setText(String.valueOf(selectedVideo.getReleaseDate()));
        } else if (selectedField.equalsIgnoreCase("All Sales #")) {
            selectedVideo.setAllSales(Integer.parseInt(editTextField.getText()));
        } else if (selectedField.equalsIgnoreCase("VHS Sales #")) {
            selectedVideo.setVhsSales(Integer.parseInt(editTextField.getText()));
        } else if (selectedField.equalsIgnoreCase("DVD Sales #")) {
            selectedVideo.setDvdSales(Integer.parseInt(editTextField.getText()));
        } else if (selectedField.equalsIgnoreCase("Blu-Ray Sales #")) {
            selectedVideo.setBluraySales(Integer.parseInt(editTextField.getText()));
        }
        allMoviesListView.refresh();
    }

    public void addImage() throws Exception {
        Stage mainStage = (Stage)addImageButton.getScene().getWindow();
        File selectedFile = imageFileChooser.showOpenDialog(mainStage);
        FileInputStream input = new FileInputStream(selectedFile);
        Image newImage = new Image(input);

        // store new Image in the selected Model Movie object
        Movie movieToChange = allMoviesListView.getSelectionModel().getSelectedItem();
        movieToChange.setImageData(newImage);

        // display Image in the GUI
        movieImageView.setImage(newImage);
    }
}
