package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.GlobalData;

import java.util.Objects;

public class MainMenu extends Application {

    public ImageView newGame = new ImageView(); //TODO : Lambda
    public ImageView continueGame = new ImageView(); //TODO : Lambda
    public ImageView profileMenu = new ImageView();
    public ImageView scoreboardMenu = new ImageView();
    public ImageView settings = new ImageView();
    public ImageView exit = new ImageView();
    public Pane pane;
    public Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Pane pane = new Pane();
        this.pane = pane;
        pane.setPrefSize(500, 600);
        pane.setStyle("-fx-background-color: #acbcff");
        designMainMenu();
        Scene scene = new Scene(pane);
        stage.setTitle("aaGame");
        stage.getIcons().add(new Image(Objects.requireNonNull(RegisterMenu.class.getResource
                ("/images/aaIcon.png")).toExternalForm()));
        stage.setScene(scene);
        stage.show();
    }

    private void designMainMenu() {
        designNewGame();
        designContinueGame();
        designProfileMenu();
        designScoreBoard();
        designSettings();
        designExit();
    }

    private void designNewGame() {
        newGame.setImage(new Image(Objects.requireNonNull
                (RegisterMenu.class.getResource("/images/gaming.png")).toExternalForm()));
        newGame.setFitHeight(64);
        newGame.setFitWidth(64);
        newGame.setLayoutX(125);
        newGame.setLayoutY(50);
        newGame.setOnMouseClicked(mouseEvent -> {
            try {
                new GameMenu().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        pane.getChildren().add(newGame);
    }

    private void designContinueGame() {
        continueGame.setImage(new Image(Objects.requireNonNull
                (RegisterMenu.class.getResource("/images/continueGame.png")).toExternalForm()));
        continueGame.setFitHeight(64);
        continueGame.setFitWidth(64);
        continueGame.setLayoutX(305);
        continueGame.setLayoutY(50);
        continueGame.setOnMouseClicked(mouseEvent -> {
            //TODO: continueGame
        });
        pane.getChildren().add(continueGame);
    }

    private void designProfileMenu() {
        profileMenu.setImage(new Image(Objects.requireNonNull
                (RegisterMenu.class.getResource("/images/user.png")).toExternalForm()));
        profileMenu.setFitHeight(64);
        profileMenu.setFitWidth(64);
        profileMenu.setLayoutX(125);
        profileMenu.setLayoutY(258);
        profileMenu.setOnMouseClicked(mouseEvent -> {
            try {
                new ProfileMenu().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        pane.getChildren().add(profileMenu);
    }

    private void designScoreBoard() {
        scoreboardMenu.setImage(new Image(Objects.requireNonNull
                (RegisterMenu.class.getResource("/images/scoreboard.png")).toExternalForm()));
        scoreboardMenu.setFitHeight(64);
        scoreboardMenu.setFitWidth(64);
        scoreboardMenu.setLayoutX(305);
        scoreboardMenu.setLayoutY(258);
        scoreboardMenu.setOnMouseClicked(mouseEvent -> {
            //ScoreBoardMenu
        });
        pane.getChildren().add(scoreboardMenu);
    }

    private void designSettings() {
        settings.setImage(new Image(Objects.requireNonNull
                (RegisterMenu.class.getResource("/images/settings.png")).toExternalForm()));
        settings.setFitHeight(64);
        settings.setFitWidth(64);
        settings.setLayoutX(125);
        settings.setLayoutY(466);
        settings.setOnMouseClicked(mouseEvent -> {
            new SettingsMenu().start(stage);
        });
        pane.getChildren().add(settings);
    }

    private void designExit() {
        exit.setImage(new Image(Objects.requireNonNull
                (RegisterMenu.class.getResource("/images/logout.png")).toExternalForm()));
        exit.setFitHeight(64);
        exit.setFitWidth(64);
        exit.setLayoutX(305);
        exit.setLayoutY(466);
        exit.setOnMouseClicked(mouseEvent -> {
            GlobalData.setCurrentUser(null);
            GlobalData.isGuest = false;
            new RegisterMenu().start(stage);
        });
        pane.getChildren().add(exit);
    }
}
