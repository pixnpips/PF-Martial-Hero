package Controller;

import Model.AudioPlayer;
import Model.Player;
import javafx.scene.control.ProgressBar;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.SQLException;


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
    private boolean turn_P1;
    private boolean turn_P2;


    private Player P1;
    private Player P2;

    ProgressBar pb1;
    ProgressBar pb2;

    private MapController MC= new MapController();
    private boolean dead;
    AudioPlayer AP = new AudioPlayer();


    public DamageController(GlobalMoveController GMC,SpriteAnimationController S1, SpriteAnimationController S2){
        this.GMC= GMC;
        this.SAC1=S1;
        this.SAC2=S2;
        this.P1=PlayerController.player1;
        this.P2=PlayerController.player2;
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

            case "turn1" :
//                System.out.println("PLayer 1 turn: "+evt.getNewValue());
                this.turn_P1=(boolean)evt.getNewValue();
                break;

            case "turn2" :
//                System.out.println("PLayer 2 turn: "+evt.getNewValue());
                this.turn_P2=(boolean)evt.getNewValue();
                break;

            case "attack11":
//                System.out.println("Player 1 Attacke 1 :"+ evt.getNewValue());
                this.attack1_P1=(boolean) evt.getNewValue();
                if(this.attack1_P1){this.getHit(this.SAC1.getPlayerNum());}
            break;
            case "attack21":
//                System.out.println("Player 1 Attacke 2 :"+ evt.getNewValue());
                this.attack2_P1=(boolean) evt.getNewValue();
                if(this.attack2_P1){this.getHit(this.SAC1.getPlayerNum());}
            break;
            case "attack12":
//                System.out.println("Player 2 Attacke 1 :"+ evt.getNewValue());
                this.attack1_P2=(boolean) evt.getNewValue();
                if(this.attack1_P2){this.getHit(this.SAC2.getPlayerNum());}
                break;
            case "attack22":
//                System.out.println("Player 2 Attacke 2 :"+ evt.getNewValue());
                this.attack2_P2=(boolean) evt.getNewValue();
                if(this.attack2_P2){this.getHit(this.SAC2.getPlayerNum());}
                break;
            case "dead":
                this.dead=(boolean) evt.getNewValue();
                AP.playDeadAudio();
                System.out.println("Tot?! "+ this.dead);
                try {
                    Thread.sleep(1000);
                    die();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    public void getHit(int num){
        boolean b1;
        boolean b2;
        if (num==1) {b1=attack1_P1; b2=attack2_P1;} else{b1=attack1_P2; b2=attack2_P2;}
        if (((Math.abs(x_P2-x_P1)<630&&Math.abs(y_P1-y_P2)<30)&&(b1||b2))&&this.hitDirection(num)){
            if(num==1){
                this.P2.reduceEnergy();
                this.SAC2.setGetHit();
                if(P2.getEnergy()<=0){
                    this.SAC2.setDeadFrames();
                }
            }
            else{
                this.P1.reduceEnergy();
                this.SAC1.setGetHit();
                if(P1.getEnergy()<=0){
                    this.SAC1.setDeadFrames();
                }
            }
        }
    }

    public boolean hitDirection(int num){
        double x1;
        double x2;
        boolean turn;
        if(num==1){x1=x_P1; x2=x_P2; turn=this.SAC1.getturn();}else{x1=x_P2; x2=x_P1;turn=this.SAC2.getturn();}
        return((x1<x2&&!turn)||(x2<x1&&turn));
    }

    public void die(){
        MC.endGame();
    }

}
