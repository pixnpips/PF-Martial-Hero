package Model;

import javafx.scene.control.ProgressBar;

public class Player {


    private String name;
    private int wins;
   private double energy;
    private ProgressBar Healthbar;


    public Player(String n, int wins){
        this.name=n;
        this.wins=0;
        this.energy=1.0;
        this.Healthbar=null;

    }
    public void setName(String name) {
        this.name = name;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void reduceEnergy() {
//        System.out.println(this.Healthbar.toString());
//        System.out.println(this.Healthbar.getProgress());
        this.Healthbar.setProgress((this.Healthbar.getProgress()-0.05));
    }

    public String getName(){
        return this.name;
    }

    public int getWins(){
        return this.wins;
    }

    public double getEnergy() {
        return Healthbar.getProgress();
    }

    public ProgressBar getHealthbar() {
        return Healthbar;
    }

    public void setHealthbar(ProgressBar healthbar) {
        Healthbar = healthbar;
    }

}
