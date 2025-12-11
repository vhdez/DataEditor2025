package com.example.dataeditor2025;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Controller0_SuperController {
    public void initialize() throws Exception {
        ArrayList<Movie> allMovies = Movie.getAllMovies();
        if (allMovies.isEmpty()) {
            Film.readFilmData();
            Video.readVideosData();
        }
    }

    public void setChoice(ChoiceBox<String> viewChoiceBox, String choice) throws Exception {
        viewChoiceBox.getItems().add("Text");
        viewChoiceBox.getItems().add("List");
        viewChoiceBox.getItems().add("Table");
        viewChoiceBox.setValue(choice);

        viewChoiceBox.setOnAction((event) -> {
            String selectedViewName = viewChoiceBox.getSelectionModel().getSelectedItem();
            switchView(viewChoiceBox, selectedViewName);
        });
    }

    public void switchView(ChoiceBox<String> viewChoiceBox, String viewChosen) {
        String fxmlFile = null;
        if (viewChosen.equalsIgnoreCase("Text")) {
            fxmlFile = "View1.fxml";
        } else if (viewChosen.equalsIgnoreCase("List")) {
            fxmlFile = "View2.fxml";
        } else if (viewChosen.equalsIgnoreCase("Table")) {
            fxmlFile = "View3.fxml";
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DataEditorApplication.class.getResource(fxmlFile));
            Scene newScene = new Scene(fxmlLoader.load(), 1000, 400);
            Stage mainStage = (Stage) viewChoiceBox.getScene().getWindow();
            mainStage.setScene(newScene);
        } catch (Exception ex) {
            System.out.println("switchView() " + ex);
        }
    }
}
