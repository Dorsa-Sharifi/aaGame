package view;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.CentralCircle;
import model.Settings;

import javax.print.attribute.standard.MediaSize;
import javax.swing.*;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class RotateAnimation extends Transition {
    private static CentralCircle centralCircle;
    private CentralCircle invisibleCircle;
    private int freezeOptionIsFinished = 4;
    private int rotateCounter = 4;
    private int visibleCounter = 1;
    private boolean changeToCounterClockWise = false;
    private boolean changeToClockWise = false;
    private boolean decreased = false;
    private boolean increased = true;
    public static boolean phase1 = false;
    public static boolean phase2 = false;
    public static boolean phase3 = false;
    public static boolean phase4 = false;
    private boolean isVisible = true;
    private boolean changeColor = false;
    private int newAngleCounter = 5;

    private Timer makeBallsLargerOrNormal;
    private Timer visibilityOfBalls;
    private Timer clockWise;
    private Timer counterClockWise;

    public RotateAnimation(CentralCircle centralCircle, CentralCircle invisibleCircle) {
        this.centralCircle = centralCircle;
        this.invisibleCircle = invisibleCircle;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(1000));
        phase1 = true;
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
            phase1 = false;
            phase2 = true;
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
            changeTheRadiusOfBalls();
        } else if (phase > 0.25 && phase <= 0.5) { //phase 3
            phase2 = false;
            phase3 = true;
            changeTheRadius(false);
            changeTheVisibilityOfBalls();
        } else if (phase > 0 && phase <= 0.25) { //phase 4
            if (phase3) setNewVisibilityToBalls(false);
            phase3 = false;
            phase4 = true;
            setTimerForAngularChange();
            if (!GameMenu.pane.getChildren().contains(GameMenu.throwAngle)) GameMenu.createThrowAngleText();
        }
    }

    private void changeDirectionToCounterClockWise() {
        Timer timer = new Timer();
        this.counterClockWise = timer;
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
                    rotateCounter = 0;
                    changeToCounterClockWise = true;
                    changeToClockWise = true;
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    private void changeDirectionToClockWise() {
        Timer timer = new Timer();
        this.clockWise = timer;
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

    private void changeTheRadiusOfBalls() {
        Timer timer = new Timer();
        this.makeBallsLargerOrNormal = timer;
        timer.schedule(new TimerTask() {
            public void run() {
                if (phase2) {
                    if (increased && !decreased) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                changeTheRadius(true);
                                increased = false;
                                decreased = true;
                            }
                        });
                    } else if (!increased && decreased) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                changeTheRadius(false);
                                increased = true;
                                decreased = false;
                            }
                        });
                    }
                    if (GameMenu.gameOver) {
                        timer.cancel();
                    }
                }
            }
        }, 0, 1000);
    }

    private void changeTheRadius(boolean type ) {
        for (Node circlesAndLine : centralCircle.circlesAndLines) {
            if (circlesAndLine instanceof Circle) {
                if (type) {
                    ((Circle) circlesAndLine).setRadius(25);
                } else ((Circle) circlesAndLine).setRadius(20);
            }
        }

    }
    public static void checkCollision() {
        for (int i = 0 ; i < centralCircle.circlesAndLines.size() ; i++) {
            for (int j = i + 1 ; j < centralCircle.circlesAndLines.size() ; j++) {
                Node first = centralCircle.circlesAndLines.get(i);
                Node second = centralCircle.circlesAndLines.get(j);
                if (first instanceof Circle && second instanceof Circle) {
                    if (first.getBoundsInParent().intersects(second.getBoundsInParent())){
                        GameMenu.gameOver = true;

                    }
                }
            }
        }
    }

    private void changeTheVisibilityOfBalls() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (phase3) {
                    if (isVisible) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                setNewVisibilityToBalls(true);
                                isVisible = false;
                            }
                        });
                    } else {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                setNewVisibilityToBalls(false);
                                isVisible = true;
                            }
                        });
                    }
                }
            }
        },0,1000);
    }

    private void setNewVisibilityToBalls(boolean status) {
        for (Node circlesAndLine : centralCircle.circlesAndLines) {
            if (status) circlesAndLine.setOpacity(0);
            else circlesAndLine.setOpacity(1);
        }
    }

    private void setTimerForAngularChange() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (phase4) {
                    if (newAngleCounter != 0) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                newAngleCounter--;
                            }
                        });
                    }
                    if (newAngleCounter == 0){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                makeTheTransitionAngular();
                                newAngleCounter = 5;
                            }
                        });
                    }
                }
            }
        },0,1000);
    }


    private void makeTheTransitionAngular() {
        if (Settings.levelDifficulty == 1.0) {
            ThrowingAnimation.newAngle = Math.floor(Math.random() * (7 - (- 7 ) + 1) + -7);
        } else if (Settings.levelDifficulty == 2.0) {
            System.out.println("HI");
            ThrowingAnimation.newAngle = Math.floor(Math.random() * (12 - (- 12 ) + 1) + -12);
        } else if (Settings.levelDifficulty == 3.0) {
            ThrowingAnimation.newAngle = Math.floor(Math.random() * (15 - (- 15 ) + 1) + -15);
        }
        GameMenu.newAngle = ThrowingAnimation.newAngle;
    }

}
