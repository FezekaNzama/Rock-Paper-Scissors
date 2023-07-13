package com.fezekanzama;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.fezekanzama.Backend.Player;
import com.fezekanzama.Backend.BotPlayer;

/**
 * JavaFX Rock, Paper, Scissors App
 */
public class App extends Application {

    private static Scene scene;
    static Player playerOne; 
    static Player playerTwo;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("welcomeScreen"), 640, 480);
        stage.setTitle("Rock, Paper, Scissors");
        stage.setScene(scene);
        stage.show();
    }

    //Standard scene loader
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    //Player Two scene loader - applies restrictions if player one is a bot
    static void setRoot(String fxml, String botInitiated) throws IOException {
        
        PlayerTwoController playerTwo = new PlayerTwoController();
        playerTwo.setBot(botInitiated);

        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        loader.setController((playerTwo));       
        scene.setRoot(loader.load());
    }

    //FXML loader 
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    //initiate a human player
    static Player initiatePlayer(String playerName, String choice){
        return new Player(playerName, choice);
    }

    //initiate a bot player
    static BotPlayer initiatePlayer(String playerName){
        return new BotPlayer(playerName);
    }

    //return playerOne
    static Player getPlayerOne(){
        return playerOne;
    }

    //return playerTwo
    static Player getPlayerTwo(){
        return playerTwo;
    }

    //return winner
    static String[] decideWinner(){
        if(playerOne.getChoice().equals(playerTwo.getChoice())){
            String winner =  "This Round is Tied.";
            String winnerBreakDown = "Both players chose "+playerOne.getChoice()+".";
            return new String[] {winner, winnerBreakDown};
        }
        else if(playerOne.getChoice().equals("rock") && playerTwo.getChoice().equals("scissors") || playerOne.getChoice().equals("scissors") && playerTwo.getChoice().equals("paper") || playerOne.getChoice().equals("paper") && playerTwo.getChoice().equals("rock")){
            String winner = playerOne.getName()+ " won this round.";
            String winnerBreakDown = playerOne.getName() + " chose "+ playerOne.getChoice()+ " whilst " + playerTwo.getName() +" chose "+playerTwo.getChoice()+ ".";
            return new String[] {winner, winnerBreakDown};
        }
        else{
            String winner = playerTwo.getName()+ " won this round.";
            String winnerBreakDown = playerTwo.getName()+" chose "+playerTwo.getChoice()+ " whilst "+playerOne.getName()+" chose "+playerOne.getChoice()+".";
            return new String[] {winner, winnerBreakDown};
        }

    }

    public static void main(String[] args) {
        launch();
    }

}
