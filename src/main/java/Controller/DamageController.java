package Controller;

import Model.Player;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ProgressBar;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DamageController implements PropertyChangeListener {

    private SpriteAnimationController SAC1;
    private SpriteAnimationController SAC2;
    private GlobalMoveController GMC;

    private boolean attack1_P1;
    private boolean attack2_P1;
    private boolean attack1_P2;
    private boolean attack2_P2;

    private double x_P1;
    private double y_P1;
    private double x_P2;
    private double y_P2;


    private Player P1;
    private Player P2;

    ProgressBar pb1;
    ProgressBar pb2;

    public DamageController(GlobalMoveController GMC){
        this.GMC= GMC;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getPropertyName());
        switch (evt.getPropertyName()){
            case "x_N1": System.out.println("Neuer X Wert von Node 1:"+ evt.getNewValue());
            break;
            case "x_N2": System.out.println("Neuer X Wert von Node 2:"+ evt.getNewValue());
            break;
            case "y_N1": System.out.println("Neuer Y Wert von Node 1:"+ evt.getNewValue());
            break;
            case "y_N2": System.out.println("Neuer Y Wert von Node 2:"+ evt.getNewValue());
            break;
            case "attack11": System.out.println("Player 1 Attacke 1 :"+ evt.getNewValue());
            break;
            case "attack21": System.out.println("Player 1 Attacke 2 :"+ evt.getNewValue());
            break;
            case "attack12": System.out.println("Player 2 Attacke 1 :"+ evt.getNewValue());
                break;
            case "attack22": System.out.println("Player 2 Attacke 2 :"+ evt.getNewValue());
                break;
        }
    }

    public void reducePb1(){
        this.pb1.setProgress(this.pb1.getProgress()-10);
    }

    public void reducePb2(){
        this.pb1.setProgress(this.pb1.getProgress()-10);
    }

    public void die(Player p){

    }

}
