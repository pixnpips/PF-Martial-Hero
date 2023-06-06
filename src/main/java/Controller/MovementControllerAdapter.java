package Controller;

import javafx.scene.Node;
import javafx.scene.Scene;

public class MovementControllerAdapter extends GlobalMoveController{

    private  Node N1;
    private  Node N2;
    private Scene S ;

    public MovementControllerAdapter(Node N1, Node N2, Scene S){
        super(N1, N2, S);
    }

    @Override
    public int getMovement(int i) {
        return super.getMovement(i);
    }
}
