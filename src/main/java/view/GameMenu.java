package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import model.CentralCircle;
import model.Settings;

import java.util.Objects;

public class GameMenu extends Application {
    //TODO: ADD THE BASIC ELEMENTS TO THE ARRAYLIST OF CENTRAL CIRCLE
    public Stage stage;
    public Pane pane;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Pane pane = new Pane();
        this.pane = pane;
        pane.setPrefSize(500, 600);
        pane.setStyle("-fx-background-color: #acbcff");
        designTheMainMap();
        Scene scene = new Scene(pane);
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
                addAllBasicElementsToCentralCircle(centralCircle , circleOne, lineOne,
                        circleTwo, lineTwo,
                        circleThree, lineThree,
                        circleFour, lineFour,
                        circleFive, lineFive);
                RotateAnimation rotateAnimation = new RotateAnimation(centralCircle , invisibleCircle) ;
                rotateAnimation.play();
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

}
