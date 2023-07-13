package com.fezekanzama;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fezekanzama.Backend.Player;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PlayerTwoController implements Initializable{

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

    private Boolean botStatus = false; 

    private Boolean submitReady = true;

    //onclick button action
    @FXML
    private void switchToResultsScreen() throws IOException {
        checkTextfields(); 
        if(submitReady){
            App.playerTwo = initiatePlayer();
            App.setRoot("resultsScreen");
        }

        setSubmitReady(true);
    }

    //initialize page
    @Override
    public void initialize(URL fxmlURL, ResourceBundle resource) {
        //ensure all alerts are off/invisible 
        alertLabelVisibility(playerTypeAlert, false);
        alertLabelVisibility(playerNameAlert, false);
        alertLabelVisibility(selectionAlert, false);
        checkBot(botStatus);

    }

    //set bot status -- if player one is already a bot, botStatus set to true (passed in from Player One)
    public void setBot(String botStatus){
        if(botStatus.equals("Bot")){
            this.botStatus = true;
        }
    }

    //if a bot has already been initialized retract the option to select player type 
    private void checkBot(Boolean isBot){
        if(isBot){
            playerType.setVisible(false);
            playerType.managedProperty().bind(playerType.visibleProperty());
            alertLabelVisibility(playerTypeAlert, false);
            playerType.setValue("Human");
        }
        else{
            managePlayerTypeChoice();
        }
        
    }

    //set playerType - ensure that textfields and prompts are only visible if the playertype is 'Humans'
    private void managePlayerTypeChoice(){
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

    //initiate player based on either already determined bot status from player one or from choice made in comboBox
    private Player initiatePlayer(){
        if(botStatus || playerType.getValue().equals("Human")){
            return App.initiatePlayer(playerNameTextField.getText(), selectionTextField.getText());
        }
        else{
            return App.initiatePlayer("Bot");
        }
    }

    //setting alert label visibility
    private void alertLabelVisibility(Label label, Boolean visibility){
        label.setVisible(visibility);
        label.managedProperty().bind(label.visibleProperty());
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

    //set submitReady bool
    private void setSubmitReady(Boolean submitReady){
        this.submitReady = submitReady;
    }
    
}
