package Model;

import javafx.scene.Node;

public class Player {

    private String name;
    private Node N;

    private int wins;

    public Player(String n, Node N){
        this.name=n;
        this.N=N;
    }

    public Node getNode(){
        return this.N;
    }

    public String getName(){
        return  this.name;
    }

    public int getWins(){
        return this.wins;
    }

}
