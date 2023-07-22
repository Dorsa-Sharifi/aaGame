package view;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.CentralCircle;
import model.Settings;

import java.util.Objects;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class GameMenu extends Application {

    public Stage stage;
    public static Pane pane;
    public Scene scene;
    public static boolean gameOver = false;
    public static boolean victory = false;
    public static boolean tab = false;
    public static boolean changeRotateDirection = false;
    public static ProgressBar progressBar;
    public static ProgressBar freezeBar;
    public static Text scoreText;
    public static Text freezeText;
    public static Text gonnaGainScore;
    public static Text leftBalls;
    public static Text throwAngle;
    public static int score = 0;
    public static Timer timer;
    private int seconds = 0;
    private int minute = 0;
    private Label timeLabel;
    public static Group circleGroup;
    public static double newAngle;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Pane pane = new Pane();
        this.pane = pane;
        pane.setPrefSize(500, 600);
        pane.setStyle("-fx-background-color: #acbcff");
        Scene scene = new Scene(pane);
        this.scene = scene;
        designTheMainMap();
        stage.setTitle("aaGame");
        stage.getIcons().add(new Image(Objects.requireNonNull(RegisterMenu.class.getResource
                ("/images/aaIcon.png")).toExternalForm()));
        stage.setScene(scene);
        stage.show();
    }

    private void designTheMainMap() {
        switch (Settings.chosenMap) {
            case 1:
                CentralCircle invisibleCircle = new CentralCircle(250, 250, 120, Color.rgb(172, 188, 255));
                CentralCircle centralCircle = new CentralCircle(250, 250, 30, Color.BLACK);
                pane.getChildren().add(invisibleCircle);
                pane.getChildren().add(centralCircle);
                addBasicElements(centralCircle, invisibleCircle);
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    private void addBasicElements(CentralCircle centralCircle, CentralCircle invisibleCircle) {
        setTimer(pane);
        switch (Settings.chosenMap) {
            case 1:
                Circle circleOne = new Circle();
                setCoordinatesOfCircle(circleOne, invisibleCircle.getCenterX(),
                        invisibleCircle.getCenterY() - 140);
                Line lineOne = new Line();
                setCoordinatesOfLine(lineOne, circleOne.getCenterX(), circleOne.getCenterY(),
                        centralCircle.getCenterX(), centralCircle.getCenterY());
                Circle circleTwo = new Circle();
                setCoordinatesOfCircle(circleTwo, invisibleCircle.getCenterX() + 60,
                        invisibleCircle.getCenterY() - 126);
                Line lineTwo = new Line();
                setCoordinatesOfLine(lineTwo, circleTwo.getCenterX(), circleTwo.getCenterY(),
                        centralCircle.getCenterX(), centralCircle.getCenterY());

                Circle circleThree = new Circle();
                setCoordinatesOfCircle(circleThree, invisibleCircle.getCenterX() - 60,
                        invisibleCircle.getCenterY() - 126);
                Line lineThree = new Line();
                setCoordinatesOfLine(lineThree, circleThree.getCenterX(), circleThree.getCenterY(),
                        centralCircle.getCenterX(), centralCircle.getCenterY());

                Circle circleFour = new Circle();
                setCoordinatesOfCircle(circleFour, invisibleCircle.getCenterX() - 60,
                        invisibleCircle.getCenterY() + 126);
                Line lineFour = new Line();
                setCoordinatesOfLine(lineFour, circleFour.getCenterX(), circleFour.getCenterY(),
                        centralCircle.getCenterX(), centralCircle.getCenterY());

                Circle circleFive = new Circle();
                setCoordinatesOfCircle(circleFive, invisibleCircle.getCenterX() + 60,
                        invisibleCircle.getCenterY() + 126);
                Line lineFive = new Line();
                setCoordinatesOfLine(lineFive, circleFive.getCenterX(), circleFive.getCenterY(),
                        centralCircle.getCenterX(), centralCircle.getCenterY());

                addAllBasicElementsToPane(circleOne, lineOne,
                        circleTwo, lineTwo,
                        circleThree, lineThree,
                        circleFour, lineFour,
                        circleFive, lineFive);
                addAllBasicElementsToCentralCircle(centralCircle, circleOne, lineOne,
                        circleTwo, lineTwo,
                        circleThree, lineThree,
                        circleFour, lineFour,
                        circleFive, lineFive);
                RotateAnimation rotateAnimation = new RotateAnimation(centralCircle, invisibleCircle);
                rotateAnimation.play();
                circleGroup = makeTheNewBall();
                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if (keyEvent.getCode() == KeyCode.SPACE) {
                            ThrowingAnimation throwingAnimation = new ThrowingAnimation
                                    (centralCircle, invisibleCircle, circleGroup, pane);
                            throwingAnimation.play();
                        } else if (keyEvent.getCode() == KeyCode.TAB) {
                            if (freezeBar.getProgress() == 1) {
                                tab = true;
                            }
                        }
                    }
                });
                createProgressBarOfScores();
                createProgressBarOfFreezeOption();
                createLeftBallText();
                createGonnaGainScoreText();
                break;
            case 2:
                break;
            case 3:
                break;
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

    private void addAllBasicElementsToPane(Circle circleOne, Line lineOne,
                                           Circle circleTwo, Line lineTwo,
                                           Circle circleThree, Line lineThree,
                                           Circle circleFour, Line lineFour,
                                           Circle circleFive, Line lineFive) {
        pane.getChildren().add(circleOne);
        pane.getChildren().add(lineOne);
        pane.getChildren().add(circleTwo);
        pane.getChildren().add(lineTwo);
        pane.getChildren().add(circleThree);
        pane.getChildren().add(lineThree);
        pane.getChildren().add(circleFour);
        pane.getChildren().add(lineFour);
        pane.getChildren().add(circleFive);
        pane.getChildren().add(lineFive);
    }


    private void addAllBasicElementsToCentralCircle(CentralCircle centralCircle, Circle circleOne, Line lineOne,
                                                    Circle circleTwo, Line lineTwo, Circle circleThree,
                                                    Line lineThree, Circle circleFour, Line lineFour,
                                                    Circle circleFive, Line lineFive) {
        centralCircle.addNodes(circleOne);
        centralCircle.addNodes(lineOne);
        centralCircle.addNodes(circleTwo);
        centralCircle.addNodes(lineTwo);
        centralCircle.addNodes(circleThree);
        centralCircle.addNodes(lineThree);
        centralCircle.addNodes(circleFour);
        centralCircle.addNodes(lineFour);
        centralCircle.addNodes(circleFive);
        centralCircle.addNodes(lineFive);
    }

    public static Group makeTheNewBall(){
        Group circleGroup = new Group();
        Circle circle = new Circle();
        circle.setRadius(20);
        Text number = new Text(String.valueOf(Settings.leftBalls));
        number.setFill(Color.WHITE);
        circleGroup.setLayoutX(250);
        circleGroup.setLayoutY(550);
        circleGroup.getChildren().add(circle);
        circleGroup.getChildren().add(number);
        pane.getChildren().add(circleGroup);
        return circleGroup;
    }


    private void setTimer(Pane pane) {
        timer = new Timer();
        timeLabel = new Label();
        timeLabel.setLayoutX(450);
        timeLabel.setStyle("-fx-background-color: #35a9c1;-fx-font-size: 20px; -fx-text-alignment: center;");
        timeLabel.setPrefSize(60,30);
        pane.getChildren().add(timeLabel);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seconds++;
                if (seconds >= 60) {
                    minute++;
                    seconds = 0;
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        timeLabel.setText(minute+":"+seconds);
                    }
                });
            }
        }, 0, 1000);

    }

    private void createProgressBarOfScores(){
        scoreText = new Text();
        scoreText.setText("Score: "+ score);
        scoreText.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 15px ;");
        scoreText.setLayoutX(5);
        scoreText.setLayoutY(25);
        progressBar = new ProgressBar();
        progressBar.setProgress(0);
        progressBar.setLayoutX(70);
        progressBar.setLayoutY(10);
        pane.getChildren().add(scoreText);
        pane.getChildren().add(progressBar);
    }

    private void createProgressBarOfFreezeOption() {
        freezeText = new Text();
        freezeText.setText("Freeze Option");
        freezeText.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 15px ;");
        freezeText.setLayoutX(5);
        freezeText.setLayoutY(50);
        freezeBar = new ProgressBar();
        freezeBar.setProgress(0);
        freezeBar.setLayoutX(110);
        freezeBar.setLayoutY(35);
        pane.getChildren().add(freezeText);
        pane.getChildren().add(freezeBar);
    }

    private void createLeftBallText() {
        leftBalls = new Text() ;
        leftBalls.setText("Left Balls: "+ Settings.ballNumbers);
        leftBalls.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 15px ;");
        leftBalls.setLayoutX(5);
        leftBalls.setLayoutY(75);
        pane.getChildren().add(leftBalls);
    }

    private void createGonnaGainScoreText(){
        gonnaGainScore = new Text() ;
        gonnaGainScore.setText("Throw Score: "+ Settings.increasingValue * 100);
        gonnaGainScore.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 15px ;");
        gonnaGainScore.setLayoutX(5);
        gonnaGainScore.setLayoutY(95);
        pane.getChildren().add(gonnaGainScore);
    }

    public static void createThrowAngleText() {
        throwAngle = new Text() ;
        if (Settings.levelDifficulty == 1.0) {
            newAngle = 0;
        } else if (Settings.levelDifficulty == 2.0) {
            newAngle = 2;
        } else if (Settings.levelDifficulty == 3.0) {
            newAngle = 3;
        }
        throwAngle.setText("Throw Angle: " + newAngle);
        throwAngle.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 15px ;");
        throwAngle.setLayoutX(5);
        throwAngle.setLayoutY(115);
        pane.getChildren().add(throwAngle);
    }
}
