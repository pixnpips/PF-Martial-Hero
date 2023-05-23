package Model;

import Application.Main;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Player {

    private String name;
    private Circle C1;

    private int num;

    public Player(String n, int num){
        this.name=n;
        this.C1=new Circle(100);
        if(num==1) {
            this.C1.setFill(Color.BLUE);
        }else this.C1.setFill(Color.RED);
    }

    public Circle getShape(){
        return this.C1;
    }

}
