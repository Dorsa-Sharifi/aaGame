package view;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Settings;

import java.util.Objects;

public class SettingsMenu extends Application {

    public Stage stage;
    public Pane pane;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        Pane pane = new Pane();
        this.pane = pane;
        pane.setPrefSize(500, 600);
        pane.setStyle("-fx-background-color: #acbcff");
        designSettingsMenu();
        Scene scene = new Scene(pane);
        stage.setTitle("aaGame");
        stage.getIcons().add(new Image(Objects.requireNonNull(RegisterMenu.class.getResource
                ("/images/aaIcon.png")).toExternalForm()));
        stage.setScene(scene);
        stage.show();
    }

    private void designSettingsMenu() {
        designLevelDifficulty();
        designBallNumber();
        designChangeShortcuts();
        designMaps();
        designColorChange();
        designLanguage();
        designMuteOption();
        designGoBackToMainMenu();
    }

    private void designLevelDifficulty() {
        HBox levelBox = new HBox();
        levelBox.setStyle("-fx-background-color: #5957c9; -fx-background-radius: 5px");
        levelBox.setPrefSize(60, 20);
        Text level = new Text();
        if (Settings.isEnglish) level.setText("Level ");
        else level.setText("سختی");
        levelBox.setLayoutX(30);
        levelBox.setLayoutY(30);
        level.setStyle(" -fx-font-family: 'Times New Roman'; -fx-font-size: 20px;");
        levelBox.getChildren().add(level);
        level.setTranslateX(7);
        Slider levelSlider = new Slider(1, 3, 1);
        levelSlider.setLayoutX(150);
        levelSlider.setLayoutY(30);
        levelSlider.setPrefWidth(100);
        levelSlider.setPrefHeight(2);
        levelSlider.setMaxWidth(100);
        levelSlider.setMajorTickUnit(1);
        if (Settings.isColorBright) levelSlider.setStyle("-fx-control-inner-background: #7977cc");
        else levelSlider.setStyle("-fx-control-inner-background: #193388");
        levelSlider.setMinorTickCount(0);
        levelSlider.setShowTickMarks(true);
        levelSlider.setShowTickLabels(true);
        levelSlider.setSnapToTicks(true);
        levelSlider.setValue(2);
        levelSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                levelSlider.setValue((Double) newValue);
                Settings.levelDifficulty = (Double) newValue;
            }
        });
        pane.getChildren().add(levelBox);
        pane.getChildren().add(levelSlider);
    }

    private void designBallNumber() {
        HBox ballNumBox = new HBox();
        ballNumBox.setStyle("-fx-background-color: #5957c9; -fx-background-radius: 5px");
        ballNumBox.setPrefSize(120, 20);
        Text ballNumber = new Text();
        if (Settings.isEnglish) ballNumber.setText("Ball Number ");
        else {
            ballNumBox.setPrefSize(80, 20);
            ballNumber.setText("تعداد توپ");
        }
        ballNumBox.setLayoutX(30);
        ballNumBox.setLayoutY(100);
        ballNumber.setStyle(" -fx-font-family: 'Times New Roman'; -fx-font-size: 20px;");
        ballNumBox.getChildren().add(ballNumber);
        ballNumber.setTranslateX(7);
        ballNumber.setTranslateY(2);

        Slider ballSlider = new Slider(1, 20, 1);
        ballSlider.setLayoutX(150);
        ballSlider.setLayoutY(110);
        ballSlider.setPrefWidth(300);
        ballSlider.setPrefHeight(2);
        ballSlider.setMaxWidth(300);
        ballSlider.setMajorTickUnit(1);
        if (Settings.isColorBright) ballSlider.setStyle("-fx-control-inner-background: #7977cc");
        else ballSlider.setStyle("-fx-control-inner-background: #193388");
        ballSlider.setMajorTickUnit(1);
        ballSlider.setMinorTickCount(0);
        ballSlider.setShowTickMarks(true);
        ballSlider.setShowTickLabels(true);
        ballSlider.setSnapToTicks(true);
        ballSlider.setValue(10);
        ballSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                ballSlider.setValue((Double) newValue);
                Settings.ballNumbers = (int) Math.floor((Double) newValue);
            }
        });
        pane.getChildren().add(ballNumBox);
        pane.getChildren().add(ballSlider);
    }

    private void designChangeShortcuts() {
        HBox shortcutBox = new HBox();
        shortcutBox.setStyle("-fx-background-color: #5957c9; -fx-background-radius: 5px");
        shortcutBox.setPrefSize(160, 25);
        Text shortcut = new Text();
        if (Settings.isEnglish) shortcut.setText("Change Shortcuts ");
        else {
            shortcutBox.setPrefSize(120, 25);
            shortcut.setText(" تغییر شورتکات");
        }
        shortcutBox.getChildren().add(shortcut);
        shortcut.setTranslateX(7);
        shortcut.setTranslateY(2);
        shortcutBox.setLayoutX(30);
        shortcutBox.setLayoutY(180);
        shortcut.setStyle(" -fx-font-family: 'Times New Roman'; -fx-font-size: 20px;");


        TextField throwBall = new TextField();
        if (Settings.isEnglish) throwBall.setPromptText("Throw Shortcut");
        else throwBall.setPromptText("شورتکات پرتاب");
        throwBall.setFocusTraversable(false);
        throwBall.setMaxWidth(120);
        throwBall.setLayoutX(200);
        throwBall.setLayoutY(180);
        TextField freeze = new TextField();
        if (Settings.isEnglish) freeze.setPromptText("Freeze Shortcut");
        else freeze.setPromptText("شورتکات فریز");
        freeze.setFocusTraversable(false);
        freeze.setMaxWidth(120);
        freeze.setLayoutX(330);
        freeze.setLayoutY(180);
        throwBall.textProperty().addListener((observableValue, oldValue, newValue) -> {
            Settings.throwBallShortcut = newValue;
        });
        freeze.textProperty().addListener((observableValue, oldValue, newValue) -> {
            Settings.freezeShortcut = newValue;
        });
        pane.getChildren().add(shortcutBox);
        pane.getChildren().add(throwBall);
        pane.getChildren().add(freeze);
    }

    private void designMaps() {
        HBox mapBox = new HBox();
        mapBox.setStyle("-fx-background-color: #5957c9; -fx-background-radius: 5px");
        mapBox.setPrefSize(60, 25);
        Text map = new Text();
        if (Settings.isEnglish) map.setText("Maps");
        else {
            mapBox.setPrefSize(50, 25);
            map.setText("نقشه");
        }
        mapBox.getChildren().add(map);
        map.setTranslateX(7);
        map.setTranslateY(2);
        mapBox.setLayoutX(30);
        mapBox.setLayoutY(250);
        map.setStyle(" -fx-font-family: 'Times New Roman'; -fx-font-size: 20px;");

        ImageView map1 = new ImageView();
        map1.setImage(new Image(Objects.requireNonNull
                (RegisterMenu.class.getResource("/images/map1.png")).toExternalForm()));
        map1.setFitHeight(64);
        map1.setFitWidth(64);
        map1.setLayoutX(125);
        map1.setLayoutY(250);
        map1.setOnMouseClicked(mouseEvent -> {
            Settings.chosenMap = 1;
        });

        ImageView map2 = new ImageView();
        map2.setImage(new Image(Objects.requireNonNull
                (RegisterMenu.class.getResource("/images/map2.png")).toExternalForm()));
        map2.setFitHeight(64);
        map2.setFitWidth(64);
        map2.setLayoutX(225);
        map2.setLayoutY(250);
        map2.setOnMouseClicked(mouseEvent -> {
            Settings.chosenMap = 2;
        });


        ImageView map3 = new ImageView();
        map3.setImage(new Image(Objects.requireNonNull
                (RegisterMenu.class.getResource("/images/map3.png")).toExternalForm()));
        map3.setFitHeight(64);
        map3.setFitWidth(64);
        map3.setLayoutX(325);
        map3.setLayoutY(250);
        map3.setOnMouseClicked(mouseEvent -> {
            Settings.chosenMap = 3;
        });
        pane.getChildren().add(mapBox);
        pane.getChildren().add(map1);
        pane.getChildren().add(map2);
        pane.getChildren().add(map3);
    }

    private void designMuteOption() {


    }

    private void designColorChange() {
        HBox colorBox = new HBox();
        colorBox.setStyle("-fx-background-color: #5957c9; -fx-background-radius: 5px");
        colorBox.setPrefSize(60, 25);
        Text color = new Text();
        if (Settings.isEnglish) color.setText("Color");
        else {
            colorBox.setPrefSize(50, 25);
            color.setText("رنگ");
        }
        colorBox.getChildren().add(color);
        color.setTranslateX(7);
        color.setTranslateY(2);
        colorBox.setLayoutX(30);
        colorBox.setLayoutY(350);
        color.setStyle(" -fx-font-family: 'Times New Roman'; -fx-font-size: 20px;");

        HBox brightColor = new HBox();
        brightColor.setStyle("-fx-background-color: #9a9aff; -fx-background-radius: 5px");
        brightColor.setPrefSize(50,25);
        brightColor.setLayoutX(100);
        brightColor.setLayoutY(350);
        brightColor.setOnMouseClicked(mouseEvent -> Settings.isColorBright = true);

        HBox darkColor = new HBox();
        darkColor.setStyle("-fx-background-color: #193388; -fx-background-radius: 5px");
        darkColor.setPrefSize(50,25);
        darkColor.setLayoutX(160);
        darkColor.setLayoutY(350);
        darkColor.setOnMouseClicked(mouseEvent -> Settings.isColorBright = false);

        pane.getChildren().add(colorBox);
        pane.getChildren().add(brightColor);
        pane.getChildren().add(darkColor);

    }

    private void designLanguage() {
        HBox languageBox = new HBox();
        languageBox.setStyle("-fx-background-color: #5957c9; -fx-background-radius: 5px");
        languageBox.setPrefSize(100, 25);
        Text language = new Text();
        if (Settings.isEnglish) language.setText("Language");
        else {
            languageBox.setPrefSize(50, 25);
            language.setText("زبان");
        }
        languageBox.getChildren().add(language);
        language.setTranslateX(7);
        language.setTranslateY(2);
        languageBox.setLayoutX(30);
        languageBox.setLayoutY(400);
        language.setStyle(" -fx-font-family: 'Times New Roman'; -fx-font-size: 20px;");

        ImageView english = new ImageView();
        english.setImage(new Image(Objects.requireNonNull
                (RegisterMenu.class.getResource("/images/english.png")).toExternalForm()));
        english.setFitHeight(64);
        english.setFitWidth(64);
        english.setLayoutX(150);
        english.setLayoutY(400);
        english.setOnMouseClicked(mouseEvent -> {
            Settings.isEnglish = true;
        });

        ImageView persian = new ImageView();
        persian.setImage(new Image(Objects.requireNonNull
                (RegisterMenu.class.getResource("/images/persian.png")).toExternalForm()));
        persian.setFitHeight(64);
        persian.setFitWidth(64);
        persian.setLayoutX(250);
        persian.setLayoutY(400);
        persian.setOnMouseClicked(mouseEvent -> {
            Settings.isEnglish = false;
        });

        pane.getChildren().add(languageBox);
        pane.getChildren().add(english);
        pane.getChildren().add(persian);
    }

    private void designGoBackToMainMenu() {
        ImageView back = new ImageView();
        back.setImage(new Image(Objects.requireNonNull
                (RegisterMenu.class.getResource("/images/back.png")).toExternalForm()));
        back.setFitHeight(64);
        back.setFitWidth(64);
        back.setLayoutX(30);
        back.setLayoutY(500);
        back.setOnMouseClicked(mouseEvent -> {
            try {
                new MainMenu().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        pane.getChildren().add(back);
    }

}
