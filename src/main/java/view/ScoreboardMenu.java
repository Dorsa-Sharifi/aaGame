package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.print.PageOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GlobalData;
import model.ScoreBoard;
import model.Settings;
import model.User;

import java.util.ArrayList;
import java.util.Objects;

public class ScoreboardMenu extends Application {

    public Stage stage;
    public Pane pane;
    public ScrollPane scrollPane = new ScrollPane();
    public VBox scoresBox = new VBox();
    public ArrayList<ScoreBoard> scores = new ArrayList<>();
    public ImageView back = new ImageView();
    public Button sortBasedOnDifficulty = new Button();

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Pane pane = new Pane();
        this.pane = pane;
        pane.setPrefSize(500, 600);
        pane.setStyle("-fx-background-color: #97dffc");
        designScoreboardMenu();
        Scene scene = new Scene(pane);
        stage.setTitle("aaGame");
        stage.getIcons().add(new Image(Objects.requireNonNull(RegisterMenu.class.getResource
                ("/images/aaIcon.png")).toExternalForm()));
        stage.setScene(scene);
        stage.show();
    }

    private void designScoreboardMenu() {
        gatherAllScoresTogether();
        sortScoreRow();
        designScrollPane();
        designButtons();
    }

    private void gatherAllScoresTogether() {
        for (int i = 0; i < GlobalData.getAllUsers().size(); i++) {
            scores.addAll(GlobalData.getAllUsers().get(i).scoreRows);
        }
    }

    private void designScrollPane() {
        scoresBox.setSpacing(10);
        scoresBox.setStyle("-fx-background-color: #858ae3");
        scoresBox.setPrefSize(300, 400);
        scrollPane.setLayoutX(100);
        scrollPane.setLayoutY(100);
        for (int i = 0; i < scores.size(); i++) {
            HBox userBox = new HBox();
            userBox.setPrefSize(300, 20);
            ImageView profile = new ImageView(scores.get(i).getProfile().getImage());
            profile.setFitWidth(50);
            profile.setFitHeight(50);
            Text text = new Text();
            String index = "\t".concat(String.valueOf(i + 1));
            String username = scores.get(i).getUsername();
            String rating = "Score: ".concat(String.valueOf(scores.get(i).getRate()));
            String minutes = "Time: ".concat(String.valueOf(scores.get(i).getMinute()));
            String second = String.valueOf(scores.get(i).getSeconds());
            String difficulty = String.valueOf(scores.get(i).getDifficulty());
            String details = index.concat(" ").concat(username).concat(" ")
                    .concat(rating).concat(" ")
                    .concat(minutes).concat(":").concat(second).concat("\n\tDifficulty: ").concat(difficulty);
            text.setText(details);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
            if (i <= 2)  userBox.setStyle("-fx-background-color: #1316aa;");
            else userBox.setStyle("-fx-background-color: #613dc1;");
            userBox.getChildren().add(profile);
            userBox.getChildren().add(text);
            scoresBox.getChildren().add(userBox);

        }
        scrollPane.setContent(scoresBox);
        pane.getChildren().add(scrollPane);
    }

    private void designButtons() {

        back.setImage(new Image(Objects.requireNonNull
                (RegisterMenu.class.getResource("/images/back.png")).toExternalForm()));
        back.setFitHeight(64);
        back.setFitWidth(64);
        back.setLayoutX(30);
        back.setLayoutY(15);
        back.setOnMouseClicked(mouseEvent -> {
            try {
                new MainMenu().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        sortBasedOnDifficulty.setStyle("-fx-background-color: #9a42ff; -fx-font-family: 'Times New Roman'");
        if (Settings.isEnglish) sortBasedOnDifficulty.setText("Based On" + '\n' + "Difficulty");
        else sortBasedOnDifficulty.setText("مرتب سازی بر حسب سختی");
        sortBasedOnDifficulty.setPrefSize(100, 60);
        sortBasedOnDifficulty.setLayoutX(380);
        sortBasedOnDifficulty.setLayoutY(15);
        sortBasedOnDifficulty.setOnMouseClicked(mouseEvent -> {
            scrollPane.setContent(null);
            pane.getChildren().remove(scrollPane);
            scoresBox = new VBox();
            sortBasedOnLevelDifficulty();
            designScrollPane();
        });
        pane.getChildren().add(back);
        pane.getChildren().add(sortBasedOnDifficulty);
    }

    private void sortScoreRow() {
        for (int i = 0; i < scores.size(); i++) {
            ScoreBoard first = scores.get(i);
            for (int j = i + 1; j < scores.size(); j++) {
                ScoreBoard second = scores.get(j);
                if (second.getRate() > first.getRate()) {
                    scores.remove(i);
                    scores.add(i, second);
                    scores.remove(j);
                    scores.add(j, first);
                    first = scores.get(i);
                    j--;
                } else if (second.getRate() == first.getRate()) {
                    if (second.getMinute() < first.getMinute()) {
                        scores.remove(i);
                        scores.add(i, second);
                        scores.remove(j);
                        scores.add(j, first);
                        first = scores.get(i);
                        j--;
                    } else if (second.getMinute() == first.getMinute() && second.getSeconds() < first.getSeconds()) {
                        scores.remove(i);
                        scores.add(i, second);
                        scores.remove(j);
                        scores.add(j, first);
                        first = scores.get(i);
                        j--;
                    }
                }
            }
        }
    }

    private void sortBasedOnLevelDifficulty() {
        for (int i = 0; i < scores.size(); i++) {
            ScoreBoard first = scores.get(i);
            for (int j = i + 1; j < scores.size(); j++) {
                ScoreBoard second = scores.get(j);
                if (second.getDifficulty() > first.getDifficulty()) {
                    scores.remove(i);
                    scores.add(i, second);
                    scores.remove(j);
                    scores.add(j, first);
                    first = scores.get(i);
                    j--;
                }
            }
        }
    }
}
