package com.fezekanzama.Backend;

import java.util.Random;

public class BotPlayer extends Player{

    public BotPlayer(String name){
        super(name);
        pickChoice();
    }
    
    private final  String[] choiceList = new String[]{"rock", "paper", "scissors"};
    private Random randomSelector = new Random();


    public void pickChoice(){
        int choiceIndex = randomSelector.nextInt(3);
        setChoice(choiceList[choiceIndex]);
    }
}
