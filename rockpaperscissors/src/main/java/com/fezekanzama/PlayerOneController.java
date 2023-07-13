package com.fezekanzama;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fezekanzama.Backend.Player;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PlayerOneController implements Initializable{

    @FXML
    private ComboBox<String> playerType;

    @FXML
    private Label playerTypeAlert; 

    @FXML
    private Label playerNamePrompt;

    @FXML
    private TextField playerNameTextField;

    @FXML
    private Label playerNameAlert;

    @FXML
    private Label selectionPrompt;

    @FXML
    private TextField selectionTextField;

    @FXML
    private Label selectionAlert;

    @FXML
    private Button submit;

    private Boolean submitReady = true;

    @Override
    public void initialize(URL fxmlURL, ResourceBundle resource) {

        //ensure all alerts are off/invisible 
        alertLabelVisibility(playerTypeAlert, false);
        alertLabelVisibility(playerNameAlert, false);
        alertLabelVisibility(selectionAlert, false);

        //set playerType - ensure that textfields and prompts are only visible if the playertype is 'Humans'
        playerType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> selected, String oldChoice, String newChoice) {
                Boolean visibility = newChoice.equals("Human");

                playerNamePrompt.setVisible(visibility);
                playerNamePrompt.managedProperty().bind(playerNamePrompt.visibleProperty());
                playerNameTextField.setVisible(visibility);
                playerNameTextField.managedProperty().bind(playerNameTextField.visibleProperty());

                selectionPrompt.setVisible(visibility);
                selectionPrompt.managedProperty().bind(selectionPrompt.visibleProperty());
                selectionTextField.setVisible(visibility);
                selectionTextField.managedProperty().bind(selectionTextField.visibleProperty());
            }
            
        });

    }

    //onclick button action
    @FXML
    private void switchToPlayerTwoScreen() throws IOException {
        checkTextfields(); 
        if(submitReady){
            App.playerOne = initiatePlayer(playerType.getValue());
            App.setRoot("playerTwoScreen", playerType.getValue());
        }

        setSubmitReady(true);
    }

    //initiate player - either as a bot or a human playing depending on comboBox selection
    private Player initiatePlayer(String playerType){
        if(playerType.equals("Bot")){
            return App.initiatePlayer("Bot");
        }

        else{
            return App.initiatePlayer(playerNameTextField.getText(), selectionTextField.getText());
        }
    }

    //set submitReady bool
    private void setSubmitReady(Boolean submitReady){
        this.submitReady = submitReady;
    }

    //checks input entries for form 
    private void checkTextfields(){
        //if playertype not defined -- block submit button and reveal alert
        if(playerType.getValue()==null){
            alertLabelVisibility(playerTypeAlert, true);
            setSubmitReady(false);
        }

        //if player nanme and selection field are left empty / are invalid -- block submit button and reveal alerts
        else if(playerType.getValue().equals("Human")){
            alertLabelVisibility(playerTypeAlert, false);
            if(playerNameTextField.getText().trim().isEmpty()){
                alertLabelVisibility(playerNameAlert, true);
                setSubmitReady(false);
            }
            if(selectionFieldCheck(selectionTextField.getText().trim())){
                alertLabelVisibility(selectionAlert, true);
                setSubmitReady(false);
            }
            

        }
    }

    //condition check for validity of selection field string
    private Boolean selectionFieldCheck(String input){
        if(input.equals("rock")){
            return false;
        }
        else if(input.equals("paper")){
            return false;
        }
        else if(input.equals("scissors")){
            return false;
        }
        else{
            return true;
        }
    }

    //setting alert label visibility
    private void alertLabelVisibility(Label label, Boolean visibility){
        label.setVisible(visibility);
        label.managedProperty().bind(label.visibleProperty());
    }

}