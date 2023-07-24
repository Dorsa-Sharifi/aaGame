package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GlobalData;
import model.ScoreBoard;
import model.User;

import java.util.ArrayList;
import java.util.Objects;

public class ScoreboardMenu extends Application {

    public Stage stage;
    public Pane pane;
    public ScrollPane scrollPane;
    public ArrayList<ScoreBoard> scoreRows;

    @Override
    public void start(Stage stage) throws Exception {
        ImageView imageView = new ImageView(new Image
                (Objects.requireNonNull
                        (RegisterMenu.class.getResource("/images/man.png")).toExternalForm()));
        User user = new User("dorsa", "123", imageView);
        GlobalData.getAllUsers().add(user);
        makeScoreRowForFirstUser();
        this.stage = stage;
        Pane pane = new Pane();
        this.pane = pane;
        pane.setPrefSize(500, 600);
        pane.setStyle("-fx-background-color: #acbcff");
        designScoreboardMenu();
        Scene scene = new Scene(pane);
        stage.setTitle("aaGame");
        stage.getIcons().add(new Image(Objects.requireNonNull(RegisterMenu.class.getResource
                ("/images/aaIcon.png")).toExternalForm()));
        stage.setScene(scene);
        stage.show();
    }

    private void designScoreboardMenu() {
        designScrollPane();
        designButtons();

    }

    private void designScrollPane() {
        this.scrollPane = new ScrollPane();
        for (int i = 0; i < GlobalData.getAllUsers().size(); i++) {
            User allUser = GlobalData.getAllUsers().get(i);
            HBox userBox = new HBox();
            ImageView profile = allUser.getProfile();
            Text text = new Text();
            String details = String.valueOf(i + 1).concat(allUser.getUsername());
            userBox.getChildren().add(profile);
        }
    }

    private void designButtons() {
    }

    private void makeScoreRowForFirstUser() {
        ArrayList<ScoreBoard> scores = GlobalData.getAllUsers().get(0).scoreRows;
        for (int i = 0; i < scores.size(); i++) {
            ScoreBoard first = scores.get(i);
            System.out.println("1st: "+first.getRate());
            for (int j = i + 1; j < scores.size(); j++) {
                ScoreBoard second = scores.get(j);
                System.out.println("2nd: "+second.getRate());
                if (second.getRate() > first.getRate()) {
                    scores.remove(i);
                    scores.add(i,second);
                    scores.remove(j);
                    scores.add(j,first);
                    first = scores.get(i);
                    j--;
                } else if (second.getRate() == first.getRate()) {
                    if (second.getMinute() < first.getMinute() ) {
                        scores.remove(i);
                        scores.add(i,second);
                        scores.remove(j);
                        scores.add(j,first);
                        first = scores.get(i);
                        j--;
                    } else if (second.getMinute() == first.getMinute() && second.getSeconds() < first.getSeconds()){
                        scores.remove(i);
                        scores.add(i,second);
                        scores.remove(j);
                        scores.add(j,first);
                        first = scores.get(i);
                        j--;
                    }
                }
            }
        }
    }

}
