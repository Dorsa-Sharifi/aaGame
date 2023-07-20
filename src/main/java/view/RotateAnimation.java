package view;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.CentralCircle;

import java.util.Timer;
import java.util.TimerTask;

public class RotateAnimation extends Transition {
    private CentralCircle centralCircle;
    private CentralCircle invisibleCircle;
    private int freezeOptionIsFinished = 4;

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
}
