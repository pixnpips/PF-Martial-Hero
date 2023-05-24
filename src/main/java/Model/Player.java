package Model;

import Application.Main;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Player {

    private String name;
    private Node N;


    public Player(String n, Node N){
        this.name=n;
        this.N=N;
    }

    public Node getNode(){
        return this.N;
    }

}
