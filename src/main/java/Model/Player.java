package Model;

import javafx.scene.Node;

public class Player {

    private String name;

    private int wins;

    public Player(String n){
        this.name=n;

    }

    public String getName(){
        return  this.name;
    }

    public int getWins(){
        return this.wins;
    }

}
