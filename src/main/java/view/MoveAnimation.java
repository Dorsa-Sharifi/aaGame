package view;

import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MoveAnimation extends Transition {

    private Group circleGroup;
    private Pane pane;
    private String direction;
    public MoveAnimation(Group circleGroup, Pane pane , String direction) {
        this.circleGroup = circleGroup;
        this.pane = pane;
        this.direction = direction;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(500));
    }

    @Override
    protected void interpolate(double v) {
        if (direction.equals("right")) {
            System.out.println("righy");
            circleGroup.setLayoutX(circleGroup.getLayoutX() + 1);
        } else {
            circleGroup.setLayoutX(circleGroup.getLayoutX() - 1);
        }

        if (circleGroup.getLayoutX() <= 20 ) circleGroup.setLayoutX(20);
        if (circleGroup.getLayoutX() >= 480) circleGroup.setLayoutX(480);
    }
}
