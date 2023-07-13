package com.fezekanzama;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ResultsController implements Initializable{

    @FXML 
    private Label winner;

    @FXML
    private Label winnerBreakDown;

    @FXML
    private void switchToPlayerOneScreen() throws IOException {
        App.setRoot("playerOneScreen");
    }

    @FXML
    private void switchToGoodbyeScreen() throws IOException {
        App.setRoot("goodbyeScreen");
    }

    @Override
    public void initialize(URL fxmlURL, ResourceBundle resource) {
        winner.setText(App.decideWinner()[0]);
        winnerBreakDown.setText(App.decideWinner()[1]);
    }


}
