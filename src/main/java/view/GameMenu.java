package view;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Objects;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class GameMenu extends Application {

    public static Stage stage;
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
    public static int seconds = 0;
    public static int minute = 0;
    private Label timeLabel;
    public static Group circleGroup;
    public static double newAngle = 0;


    @Override
    public void start(Stage stage) throws Exception {
        ImageView imageView = new ImageView(new Image
                (Objects.requireNonNull
                        (RegisterMenu.class.getResource("/images/man.png")).toExternalForm()));
        User user = new User("doreece","123",imageView);
        GlobalData.setCurrentUser(user);
        GlobalData.getAllUsers().add(user);
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
                        } else if (keyEvent.getCode().getName().equals("Right") && RotateAnimation.phase4) {
                            System.out.println("hi");
                            MoveAnimation moveAnimation = new MoveAnimation(circleGroup,pane,"right");
                            moveAnimation.play();
                        }  else if (keyEvent.getCode().getName().equals("Left") && RotateAnimation.phase4) {
                            MoveAnimation moveAnimation = new MoveAnimation(circleGroup,pane,"left");
                            moveAnimation.play();
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
        if (Settings.leftBalls != 0) {
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
        return null;
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
        throwAngle.setText("Throw Angle: " + newAngle);
        throwAngle.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 15px ;");
        throwAngle.setLayoutX(5);
        throwAngle.setLayoutY(115);
        pane.getChildren().add(throwAngle);
    }

    public static void makeTheEndingMenu() {
        GlobalData.getCurrentUser().scoreRows.add(new ScoreBoard(GlobalData.getCurrentUser().getUsername(),
                minute,seconds , score , GlobalData.currentUser.getProfile(), Settings.levelDifficulty));
        VBox vBox = new VBox();
        vBox.setLayoutX(130);
        vBox.setLayoutY(150);
        vBox.setPrefSize(250 , 200);
        vBox.setStyle("-fx-background-color: #8c92ac; -fx-background-radius: 10px");
        Text gameResult = new Text();
        Text scoreText = new Text();
        Text timeText = new Text();
        Button backToMainMenu = new Button();
        Button scoreBoardMenu = new Button();
        gameResult.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 20px ;");
        scoreText.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 15px ;");
        timeText.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 15px ;");
        gameResult.setFill(Color.WHITE);
        scoreText.setFill(Color.WHITE);
        timeText.setFill(Color.WHITE);
        if (Settings.isEnglish){
            backToMainMenu.setText("Main Menu");
            scoreBoardMenu.setText("Scoreboard");
            if (!gameOver){
                gameResult.setText("You won!");
                backToMainMenu.setStyle("-fx-background-color: #115b24; -fx-font-family: 'Times New Roman'");
                scoreBoardMenu.setStyle("-fx-background-color: #115b24; -fx-font-family: 'Times New Roman'");
            }
            else{
                gameResult.setText("You lost!");
                backToMainMenu.setStyle("-fx-background-color: #9a0d0d; -fx-font-family: 'Times New Roman'");
                scoreBoardMenu.setStyle("-fx-background-color: #9a0d0d; -fx-font-family: 'Times New Roman'");
            }
            scoreText.setText("Score: "+score);
            timeText.setText("Time: "+ minute + ":" + seconds);

        } else {
            backToMainMenu.setText("منوی اصلی");
            scoreBoardMenu.setText("جدول امتیاز");
            if (!gameOver){
                gameResult.setText("شما برنده شدید!");
                backToMainMenu.setStyle("-fx-background-color: #115b24; -fx-font-family: 'Times New Roman'");
                scoreBoardMenu.setStyle("-fx-background-color: #115b24; -fx-font-family: 'Times New Roman'");
            }
            else {
                gameResult.setText("آخی باختی!");
                backToMainMenu.setStyle("-fx-background-color: #9a0d0d; -fx-font-family: 'Times New Roman'");
                scoreBoardMenu.setStyle("-fx-background-color: #9a0d0d; -fx-font-family: 'Times New Roman'");
            }
            scoreText.setText( score + " :امتیاز ");
            timeText.setText( minute + ":" + seconds + " :زمان");
            gameResult.setTranslateX(-10);

        }

        backToMainMenu.setPrefSize(80,40);
        backToMainMenu.setTranslateX(30);
        backToMainMenu.setTranslateY(80);
        backToMainMenu.setOnMouseClicked(mouseEvent -> {
            try {
                new MainMenu().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        scoreBoardMenu.setPrefSize(80,40);
        scoreBoardMenu.setTranslateX(130);
        scoreBoardMenu.setTranslateY(40);
        scoreBoardMenu.setOnMouseClicked(mouseEvent -> {
            try {
                new ScoreboardMenu().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        gameResult.setTranslateX(80);
        gameResult.setTranslateY(20);
        scoreText.setTranslateX(30);
        scoreText.setTranslateY(50);
        timeText.setTranslateX(130);
        timeText.setTranslateY(31.5);
        vBox.getChildren().add(gameResult);
        vBox.getChildren().add(scoreText);
        vBox.getChildren().add(timeText);
        vBox.getChildren().add(backToMainMenu);
        vBox.getChildren().add(scoreBoardMenu);
        pane.getChildren().add(vBox);
    }
}
