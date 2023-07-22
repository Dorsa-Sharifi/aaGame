package view;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.CentralCircle;
import model.Settings;

import javax.print.attribute.standard.MediaSize;
import java.util.Timer;
import java.util.TimerTask;

public class RotateAnimation extends Transition {
    private CentralCircle centralCircle;
    private CentralCircle invisibleCircle;
    private int freezeOptionIsFinished = 4;
    private int rotateCounter = 4;
    private boolean changeToCounterClockWise = false;
    private boolean changeToClockWise = false;

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
        if (GameMenu.tab) {
            if (freezeOptionIsFinished == 4) {
                GameMenu.freezeBar.setProgress(0);
                rotate.setAngle(1);
                setFreezeTimer(rotate);
            } else rotate.setAngle(1);
        } else rotate.setAngle(2.5);

        checkPhases(rotate);
        for (Node circlesAndLine : centralCircle.getCirclesAndLines()) {
            circlesAndLine.getTransforms().add(rotate);
        }
        if (GameMenu.gameOver || GameMenu.victory) {
            this.stop();
        }
    }

    private void setFreezeTimer(Rotate rotate) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if (freezeOptionIsFinished != 0) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            freezeOptionIsFinished--;

                        }
                    });
                } else {
                    GameMenu.tab = false;
                    freezeOptionIsFinished = 4;
                    timer.cancel();
                }
            }
        }, 0, 1000);

    }

    private void checkPhases(Rotate rotate) {
        double phase = (double) Settings.leftBalls / Settings.ballNumbers;
        if (phase > 0.5 && phase <= 0.75) { //phase 2
            if (!changeToCounterClockWise && !changeToClockWise) { //set timer to changeToCounterClockwise
                changeToCounterClockWise = true;
                changeDirectionToCounterClockWise();
            } else if (changeToCounterClockWise && !changeToClockWise) {
                rotate.setAngle(-2.5);
            } else if (changeToCounterClockWise && changeToClockWise) { //set timer to changeToClockwise
                changeToCounterClockWise = false;
                changeDirectionToClockWise();
            } else if (!changeToCounterClockWise && changeToClockWise) {
                rotate.setAngle(2.5);
            }

        } else if (phase > 0.25 && phase <= 0.5) { //phase 3
            System.out.println("phase3");

        } else if (phase > 0 && phase <= 0.25) { //phase 4
            System.out.println("phase4");
        }
    }

    private void changeDirectionToCounterClockWise() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if (rotateCounter != 1) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            rotateCounter--;
                        }
                    });
                }
                if (rotateCounter == 1) {
                    rotateCounter = 0 ;
                    changeToCounterClockWise = true;
                    changeToClockWise = true;
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    private void changeDirectionToClockWise() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if (rotateCounter != 4) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            rotateCounter++;
                        }
                    });
                }
                if (rotateCounter == 3) {
                    rotateCounter = 4;
                    changeToClockWise = false;
                    changeToCounterClockWise = false;
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }
}
