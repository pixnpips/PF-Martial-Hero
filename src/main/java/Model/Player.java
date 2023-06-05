package Model;

import javafx.scene.Node;

public class Player {

    private String name;
    private Sprite S;

    private int wins;

    public Player(String n, Sprite S){
        this.name=n;
        this.S=S;
    }

    public Sprite getSprite(){
        return this.S;
    }

    public String getName(){
        return  this.name;
    }

    public int getWins(){
        return this.wins;
    }

}
