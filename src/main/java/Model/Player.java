package Model;

import javafx.scene.Node;

public class Player {


    private String name;
    private int wins;

    private int energy;


    public Player(String n){
        this.name=n;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public String getName(){
        return this.name;
    }

    public int getWins(){
        return this.wins;
    }

    public int getEnergy() {
        return energy;
    }


}
