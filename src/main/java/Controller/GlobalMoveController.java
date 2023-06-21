package Controller;

import javafx.animation.AnimationTimer;
import java.beans.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class GlobalMoveController extends Thread {


    private PropertyChangeSupport changes;
    private double jump_amount= 50;
    double GRAVITY = 2;
    private double node1JumpVelocity =0;
    private double Node2JumpVelocity =0;

    private Node N1;
    private Node N2;
    private Scene S;

    private boolean moveRightN1;
    private boolean moveLeftN1;

    private boolean moveRightN2;
    private boolean moveLeftN2;


    private double x_N1;
    private double x_N2;

    private double y_N1;
    private double y_N2;

    private boolean N1reachedlimitofPaneL=false;
    private boolean N1reachedlimitofPaneR=false;
    private boolean N2reachedlimitofPaneL=false;
    private boolean N2reachedlimitofPaneR=false;

    private boolean scrollL=false;

    private boolean scrollR=false;

    private boolean hitPlayer1=false;
    private boolean hitPLayer2=false;

    private static final double SCROLL_SPEED =10.0;
    private boolean Airtime1=false;

    private boolean Airtime2=false;
    private static  double node1TranslateSpeed = 10.0;
    private static  double node2TranslateSpeed = 10.0;

    private boolean isCollision=false;

    private SpriteAnimationController SAC1;
    private SpriteAnimationController SAC2;
    private Node BackGroundScrollPane;

    public GlobalMoveController(Node N1, Node N2, Scene S, SpriteAnimationController S1, SpriteAnimationController S2){
        this.N1 =N1;
        this.N2 =N2;
        this.S=S;
        this.SAC1=S1;
        this.SAC2=S2;
        this.BackGroundScrollPane=this.S.lookup("#BackGroundScrollPane");
        this.changes= new PropertyChangeSupport(this);
        System.out.println(BackGroundScrollPane.toString());
    }


    private AnimationTimer translateTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            x_N1=(N1.getTranslateX());
            x_N2=(N2.getTranslateX());
            double xtemp=0;
            double[]posTemp=getNodePositions();
//            System.out.println(x_N1);
//            System.out.println(x_N2);
                if(!checkCollision()) {
                    checkBoarders();
                    if (moveRightN1 &&!N1reachedlimitofPaneR) {
                        xtemp=x_N1 + node1TranslateSpeed;
                        N1.setTranslateX((xtemp));
//                        setx_N1(xtemp);
                        setx_N1(posTemp[0]);
                    }
                    if (moveLeftN1 &&!N1reachedlimitofPaneL) {
                        xtemp=x_N1 - node1TranslateSpeed;
                        N1.setTranslateX((xtemp));
//                        setx_N1(xtemp);
                        setx_N1(posTemp[0]);
                    }
                    if (moveRightN2 &&!N2reachedlimitofPaneR) {
                        xtemp=x_N2 + node2TranslateSpeed;
                        N2.setTranslateX((xtemp));
//                        setx_N2(xtemp);
                        setx_N2(posTemp[2]);
                    }
                    if (moveLeftN2 &&!N2reachedlimitofPaneL) {
                        xtemp=x_N2 - node2TranslateSpeed;
                        N2.setTranslateX((xtemp));
//                        setx_N2(xtemp);
                        setx_N2(posTemp[2]);
                    }
                    resetBoarders();
                }
                else resetNodeCollision();
            }
    };


    private AnimationTimer jumpTimer1= new AnimationTimer() {
        @Override
        public void handle(long now) {
            y_N1=(N1.getTranslateY());
            double ytemp=0;
            double[]posTemp=getNodePositions();
            // Anwenden der Schwerkraft
            node1JumpVelocity += GRAVITY;
            Airtime1=true;
            ytemp=y_N1 + node1JumpVelocity;
            N1.setTranslateY(ytemp);
            sety_N1(posTemp[1]);
            // Überprüfen, ob das Rechteck den Boden berührt
            if (N1.getTranslateY() >=0) {
                // Zurücksetzen der Vertikalgeschwindigkeit
                node1JumpVelocity = 0;
                N1.setTranslateY(0);
                sety_N1(0);
                this.stop();
                SAC1.setIdle();
                Airtime1=false;
            }
        }
    };

    private AnimationTimer jumpTimer2 = new AnimationTimer() {
        @Override
        public void handle(long now) {
            y_N2=(N2.getTranslateY());
            double ytemp=0;
            double[]posTemp=getNodePositions();
            // Anwenden der Schwerkraft
            Airtime2=true;
            Node2JumpVelocity += GRAVITY;
            ytemp=y_N2+ Node2JumpVelocity;
            N2.setTranslateY(ytemp);
            sety_N2(posTemp[3]);
            // Überprüfen, ob das Rechteck den Boden berührt
            if (N2.getTranslateY() >=0) {
                // Zurücksetzen der Vertikalgeschwindigkeit
                Node2JumpVelocity = 0;
                N2.setTranslateY(0);
                sety_N2(0);
                this.stop();
                SAC2.setIdle();
                Airtime2=false;
            }
        }
    };

    private AnimationTimer MapScrollTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            checkBoarders();
            double BackgroundXPostiion= BackGroundScrollPane.getTranslateX();

//                System.out.println("Koordinate X Scrollpane:" + BackgroundXPostiion);
            if (N1reachedlimitofPaneL&&!N2reachedlimitofPaneR&&moveLeftN2&&BackgroundXPostiion<740) {
                BackGroundScrollPane.setTranslateX(BackgroundXPostiion+SCROLL_SPEED);
            }
            if (N2reachedlimitofPaneR&&!N1reachedlimitofPaneL&&moveRightN1&&BackgroundXPostiion>-740) {
                BackGroundScrollPane.setTranslateX(BackgroundXPostiion-SCROLL_SPEED);
            }
            if (N2reachedlimitofPaneL&&!N1reachedlimitofPaneR&&moveLeftN1&&BackgroundXPostiion<740) {
                BackGroundScrollPane.setTranslateX(BackgroundXPostiion+SCROLL_SPEED);
            }
            if (N1reachedlimitofPaneR&&!N2reachedlimitofPaneL&&moveRightN2&&BackgroundXPostiion>-740) {
                BackGroundScrollPane.setTranslateX(BackgroundXPostiion+SCROLL_SPEED);
            }
        }
    };

    public void run(){
        this.S.setOnKeyPressed(this::handleKeyPressD);
        this.S.setOnKeyReleased(this::handleKeyReleaseD);
        this.translateTimer.start();
        this.MapScrollTimer.start();
    }

    private boolean handleKeyPressD(KeyEvent event) {
        if (event.getCode() == KeyCode.D) {
             moveRightN1 = true;
             moveLeftN1 =false;
            if(!Airtime1){SAC1.setRun();}
            SAC1.turn(false);
//            System.out.println(this.getState());
        }
        if (event.getCode() == KeyCode.A) {
            moveLeftN1 = true;
            moveRightN1=false;
            if(!Airtime1){SAC1.setRun();}
            SAC1.turn(true);
//            System.out.println(this.getState());
        }
        if (event.getCode() == KeyCode.W) {
            // Führe einen Sprung aus, wenn die Taste "w" gwedrückt wird
            node1JumpVelocity = -jump_amount;
            SAC1.setJump();
            this.jumpTimer1.start();
        }
        if (event.getCode() == KeyCode.E) {
            SAC1.setAttack1();
        }
        if (event.getCode() == KeyCode.Q) {
            SAC1.setAttack2();
        }


        if (event.getCode() == KeyCode.L) {
            moveRightN2 = true;
            moveLeftN2=false;
            if(!Airtime2){SAC2.setRun();}
            SAC2.setBeginn(false);
            SAC2.turn(false);
        }

        if (event.getCode() == KeyCode.J) {
            moveLeftN2 = true;
            moveRightN2=false;
            if(!Airtime2){SAC2.setRun();}
            SAC2.turn(true);
        }

        if (event.getCode() == KeyCode.I ){
            // Führe einen Sprung aus, wenn die Taste "w" gwedrückt wird
            SAC2.setJump();
            Node2JumpVelocity = -jump_amount;
            this.jumpTimer2.start();
//            System.out.println(this.getState());
        }

        if (event.getCode() == KeyCode.U) {
            SAC2.setAttack1();
        }
        if (event.getCode() == KeyCode.O) {
            SAC2.setAttack2();
        }
        return true;
    }

    private void handleKeyReleaseD(KeyEvent event) {
        if (event.getCode() == KeyCode.D) {
            moveRightN1 = false;
            SAC1.setIdle();
        }
        if (event.getCode() == KeyCode.A) {
            moveLeftN1 = false;
            SAC1.setIdle();
        }
        if (event.getCode() == KeyCode.L) {
            moveRightN2 = false;
            SAC2.setIdle();
        }
        if (event.getCode() == KeyCode.J) {
            moveLeftN2 = false;
            SAC2.setIdle();
        }
    }


    public double [] getNodePositions(){
        return new double[] {N1.getBoundsInParent().getCenterX(),this.N1.getBoundsInParent().getCenterY(),this.N2.getBoundsInParent().getCenterX(), this.N2.getBoundsInParent().getCenterY()};
    }


    public boolean checkCollision() {
       // System.out.println(this.isCollision);
        // Überprüfe, ob die Formen tatsächlich kollidieren

        double[] nodePositions= this.getNodePositions();

        if (nodePositions[2]>nodePositions[0] ) {
            this.isCollision = (nodePositions[2]-nodePositions[0]) < 200 && (Math.abs(nodePositions[1]-nodePositions[3]) < 500);
        }else if (nodePositions[2]<nodePositions[0]) {
            this.isCollision = (nodePositions[0]-nodePositions[2]) < 200 && (Math.abs(nodePositions[1]-nodePositions[3]) < 500);
        }
        return this.isCollision;
    }

    private boolean resetNodeCollision(){

        double N1x= this.N1.getBoundsInParent().getCenterX();
        double N2x= this.N2.getBoundsInParent().getCenterX();
        double N1y= this.N1.getTranslateY();
        double N2y= this.N2.getTranslateY();
        double velocity=1;

        if(N1y!=N2y){
            velocity=25;
        }

        if (N1x<N2x){
            this.N1.setTranslateX(N1.getTranslateX()-velocity);
            this.N2.setTranslateX(N2.getTranslateX()+velocity);
            return false;
        }
        if(N1x>N2x){
            this.N1.setTranslateX(N1.getTranslateX()+velocity);
            this.N2.setTranslateX(N2.getTranslateX()-velocity);
            return false;
        }

        return true;
    }

    //hier könnte man schauen das man mit einfachen Threads arbeitet

    public void checkBoarders(){

        double[] nodePositions= this.getNodePositions();

        if(nodePositions[0]<100){
            this.N1reachedlimitofPaneL=true;
        }

        if(nodePositions[0]>1800){
            this.N1reachedlimitofPaneR=true;
        }

        if(nodePositions[2]<100){
            this.N2reachedlimitofPaneL=true;
        }

        if(nodePositions[2]>1800){
            this.N2reachedlimitofPaneR=true;
        }
    }

    public void resetBoarders(){
        this.N1reachedlimitofPaneL=false;
        this.N2reachedlimitofPaneL=false;
        this.N1reachedlimitofPaneR=false;
        this.N2reachedlimitofPaneR=false;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    // Getter und Setter Methoden für jeden Parameter der beiden Sprites für den PropertychangeSupport

    public double getx_N1() {return this.x_N1;}
    public void setx_N1(double newPos) {
        Double oldValue = this.x_N1;
        this.x_N1=newPos;
        this.changes.firePropertyChange("x_N1", oldValue, this.x_N1);
    }


    public double getx_N2() {
        return this.x_N2;
    }
    public void setx_N2(double newPos) {
        Double oldValue = this.x_N2;
        this.x_N2=newPos;
        this.changes.firePropertyChange("x_N2", oldValue, this.x_N2);
    }

    public double gety_N1() {
        return this.y_N1;
    }
    public void sety_N1(double newPos) {
        Double oldValue = this.y_N1;
        this.y_N1=newPos;
        this.changes.firePropertyChange("y_N1", oldValue, this.y_N1);
    }


    public double gety_N2() {
        return this.y_N2;
    }
    public void sety_N2(double newPos) {
        Double oldValue = this.y_N2;
        this.y_N2=newPos;
        this.changes.firePropertyChange("y_N2", oldValue, this.y_N2);
    }
}
