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
//        System.out.println(evt.getPropertyName());
        switch (evt.getPropertyName()){
            case "x_N1":
//                System.out.println("Neuer X Wert von Node 1:"+ evt.getNewValue());
            this.x_P1=(double) evt.getNewValue();
            break;
            case "x_N2":
//ad
            this.x_P2=(double) evt.getNewValue();
            break;
            case "y_N1":
//                System.out.println("Neuer Y Wert von Node 1:"+ evt.getNewValue());
            this.y_P1=(double) evt.getNewValue();
            break;
            case "y_N2":
//
            this.y_P2=(double) evt.getNewValue();
            break;
            case "attack11":
                System.out.println("Player 1 Attacke 1 :"+ evt.getNewValue());
                this.getHitP2();
            this.attack1_P1=(boolean) evt.getNewValue();
            break;
            case "attack21":
                System.out.println("Player 1 Attacke 2 :"+ evt.getNewValue());
                this.getHitP2();
            this.attack2_P1=(boolean) evt.getNewValue();
            break;
            case "attack12":
                System.out.println("Player 2 Attacke 1 :"+ evt.getNewValue());
            this.attack1_P2=(boolean) evt.getNewValue();
                this.getHitP1();
                break;
            case "attack22":
                System.out.println("Player 2 Attacke 2 :"+ evt.getNewValue());
            this.attack2_P2=(boolean) evt.getNewValue();
                this.getHitP1();
                break;
        }
    }

    public void reducePb1(){
        this.pb1.setProgress(this.pb1.getProgress()-10);
    }

    public void reducePb2(){
        this.pb1.setProgress(this.pb1.getProgress()-10);
    }

    public void getHitP1(){
        System.out.println(Math.abs(x_P2-x_P1));
        if ((Math.abs(x_P2-x_P1)<630&&Math.abs(y_P1-y_P2)<300)&&(attack1_P2||attack2_P2)){
            System.out.println("Player 1 wurde getroffen");
        }
    }

    public void getHitP2(){
        System.out.println(Math.abs(x_P2-x_P1));
        if ((Math.abs(x_P2-x_P1)<630&&Math.abs(y_P1-y_P2)<300)&&(attack1_P1||attack2_P1)){
            System.out.println("Player 2 wurde getroffen");
        }
    }

    public void die(Player p){

    }

}
