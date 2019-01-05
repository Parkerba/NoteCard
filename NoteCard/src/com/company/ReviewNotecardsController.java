package com.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ReviewNotecardsController extends Controller {


    public void onHome(ActionEvent event) throws IOException {
        switchScenes(event, "NoteCard.fxml");
    }
}

