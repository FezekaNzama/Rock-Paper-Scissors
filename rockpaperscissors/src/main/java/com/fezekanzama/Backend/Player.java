package com.fezekanzama.Backend;
public class Player {
    private String name;
    private String choice; 

    public Player(String name, String choice){
        this.name = name;
        this.choice = choice.toLowerCase();
    }

    public Player(String name){
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setChoice(String choice){
        this.choice = choice;
    }

    public String getChoice(){
        return choice; 
    }

}
