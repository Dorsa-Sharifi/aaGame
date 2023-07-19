package model;

import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class CentralCircle extends Circle {
    public ArrayList<Node> circlesAndLines = new ArrayList<>();

    public CentralCircle(double v, double v1, double v2, Paint paint) {
        super(v, v1, v2, paint);
    }

    public ArrayList<Node> getCirclesAndLines() {
        return circlesAndLines;
    }

    public void addNodes(Node circlesOrLines) {
        circlesAndLines.add(circlesOrLines);
    }
}
