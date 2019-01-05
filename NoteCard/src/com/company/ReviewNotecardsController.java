package com.company;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
public class ReviewNotecardsController {



    public void onHome(ActionEvent event) throws IOException{
        switchScenes(event,"NoteCard.fxml");
    }

    /**
         * This method is used to switch scenes on the ActionEvent to the FXML file provided as the String parameter.
         * @param event
         * @param fxml
         * @throws IOException
         */
    public void switchScenes(ActionEvent event, String fxml) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Scene newScene = new Scene(root, 800,450);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.show();
        }
    }

