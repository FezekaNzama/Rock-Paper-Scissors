package com.fezekanzama;

import java.io.IOException;

import javafx.fxml.FXML;

public class WelcomeScreenController {

    @FXML
    private void switchToPlayerOneScreen() throws IOException {
        App.setRoot("playerOneScreen");
    }
}
