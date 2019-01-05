package com.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController extends Controller {
    private AnchorPane content;

    private Button reviewNotecardsHome;

    private Button notecardManagerHome;

    private Button helpHome;

    private Button exitHome;


    private FXMLLoader homeLoader = new FXMLLoader(this.getClass().getResource("ReviewNotecards.fxml"));

    private FXMLLoader reviewNotecardsLoader = new FXMLLoader(this.getClass().getResource("ReviewNotecards.fxml"));


    /**
     * This method is called when the "review notecards" button is pressed in the main scene. It switches scenes to "ReviewNotecards.fxml".
     *
     * @param event
     * @throws IOException
     */
    public void onReviewNotecards(ActionEvent event) throws IOException {
        switchScenes(event, "ReviewNotecards.fxml");
    }

    /**
     * On pressing the button exit the program will be terminated.
     *
     * @param
     */
    public void onExit(ActionEvent event) {
        System.exit(0);
    }


}
