package view;

import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.CentralCircle;
import model.Settings;

import java.util.PrimitiveIterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


public class ThrowingAnimation extends Transition {

    private CentralCircle centralCircle;
    private CentralCircle invisibleCircle;
    private Group circleGroup;
    private Pane pane;
    public static double newAngle = 0;

    public ThrowingAnimation(CentralCircle centralCircle, CentralCircle invisibleCircle, Group circleGroup, Pane pane) {
        this.centralCircle = centralCircle;
        this.invisibleCircle = invisibleCircle;
        this.circleGroup = circleGroup;
        this.pane = pane;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(1000));
    }

    @Override
    protected void interpolate(double v) {
        makeAngularTransition();
        if (circleGroup.getBoundsInParent().intersects(invisibleCircle.getBoundsInParent())) {
            checkCollision();
            if (RotateAnimation.phase2) RotateAnimation.checkCollision();
            if (!GameMenu.gameOver) {
                pane.getChildren().remove(circleGroup);
                Circle newCircle = new Circle();
                setCoordinatesOfCircle(newCircle, invisibleCircle.getCenterX(),
                        invisibleCircle.getCenterY() + 140);
                Line newLine = new Line();
                setCoordinatesOfLine(newLine, newCircle.getCenterX(), newCircle.getCenterY(), centralCircle.getCenterX(), centralCircle.getCenterY());
                detectColorOfBalls(newCircle , newLine);
                pane.getChildren().add(newCircle);
                pane.getChildren().add(newLine);
                centralCircle.getCirclesAndLines().add(newCircle);
                centralCircle.getCirclesAndLines().add(newLine);
                Settings.leftBalls--;
                GameMenu.leftBalls.setText("Left Balls: " + Settings.leftBalls);
                setTheNewValueOfProgressBar();
                checkVictory();
                GameMenu.circleGroup = GameMenu.makeTheNewBall();
                this.stop();
            }
        }
        if (GameMenu.gameOver) {
            pane.setStyle("-fx-background-color: red");
            invisibleCircle.setFill(Color.RED);
            GameMenu.timer.cancel();
            this.stop();
        }
    }

    private void checkCollision() {
        for (Node circlesAndLine : centralCircle.getCirclesAndLines()) {
            if (circlesAndLine instanceof Circle) {
                if (circleGroup.getBoundsInParent().intersects(circlesAndLine.getBoundsInParent())) {
                    GameMenu.gameOver = true;
                }
            }
        }
    }

    private void setCoordinatesOfLine(Line line, double startX, double startY, double endX, double endY) {
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);
    }

    private void setCoordinatesOfCircle(Circle circle, double centerX, double centerY) {
        circle.setRadius(20);
        circle.setCenterX(centerX);
        circle.setCenterY(centerY);

    }

    private void setTheNewValueOfProgressBar() {
        GameMenu.score += Settings.increasingValue * 100;
        GameMenu.progressBar.setProgress((double) GameMenu.score / 100);
        GameMenu.freezeBar.setProgress(GameMenu.freezeBar.getProgress() + 0.25);
    }


    private void checkVictory() {
        if (Settings.leftBalls == 0) {
            pane.setStyle("-fx-background-color: green");
            invisibleCircle.setFill(Color.GREEN);
            GameMenu.timer.cancel();
        }
    }

    private void makeAngularTransition() {
        circleGroup.setLayoutX(circleGroup.getLayoutX() + Math.sin(Math.PI / 180 * newAngle) * 1);
        circleGroup.setLayoutY(circleGroup.getLayoutY() + Math.cos(Math.PI / 180 * newAngle) * -5);
        if (circleGroup.getLayoutY() < 0 || circleGroup.getLayoutY() > 600
                || circleGroup.getLayoutX() < 0 || circleGroup.getLayoutX() > 500) {
            GameMenu.gameOver = true;
        }
        if (RotateAnimation.phase1) ((Circle) circleGroup.getChildren().get(0)).setFill(Color.BLUE);
        else if (RotateAnimation.phase2) {
            ((Circle) circleGroup.getChildren().get(0)).setFill(Color.RED);
        } else if (RotateAnimation.phase3) {
            ((Circle) circleGroup.getChildren().get(0)).setFill(Color.YELLOW);
        } else if (RotateAnimation.phase4) {
            ((Circle) circleGroup.getChildren().get(0)).setFill(Color.DARKOLIVEGREEN);
        }
    }

    private void detectColorOfBalls(Circle newCircle , Line newLine) {
            if (RotateAnimation.phase1) {
                newCircle.setFill(Color.BLUE);
                newLine.setStyle("-fx-stroke: BLUE");
            }
            else if (RotateAnimation.phase2) {
                newCircle.setFill(Color.RED);
                newLine.setStyle("-fx-stroke: RED");
            } else if (RotateAnimation.phase3) {
                newCircle.setFill(Color.YELLOW);
                newLine.setStyle("-fx-stroke: YELLOW");
            } else if (RotateAnimation.phase4) {
                newCircle.setFill(Color.DARKOLIVEGREEN);
                newLine.setStyle("-fx-stroke: DARKOLIVEGREEN");
            }

    }

}
