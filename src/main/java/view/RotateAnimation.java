package view;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.CentralCircle;

public class RotateAnimation extends Transition {
    private CentralCircle centralCircle;
    private CentralCircle invisibleCircle;

    public RotateAnimation(CentralCircle centralCircle, CentralCircle invisibleCircle) {
        this.centralCircle = centralCircle;
        this.invisibleCircle = invisibleCircle;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(1000));
    }

    @Override
    protected void interpolate(double v) {
        Rotate rotate = new Rotate();
        rotate.setPivotX(centralCircle.getCenterX());
        rotate.setPivotY(centralCircle.getCenterY());
        rotate.setAngle(2.5);
        for (Node circlesAndLine : centralCircle.getCirclesAndLines()) {
            circlesAndLine.getTransforms().add(rotate);
        }
    }
}
