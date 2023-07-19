package view;

import controller.ProfileController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.GlobalData;
import model.Settings;
import model.User;

import java.util.Objects;

public class ProfileMenu extends Application {

    public TextField username = new TextField();
    public TextField password = new TextField();
    public ImageView myProfile = new ImageView();
    public ImageView man = new ImageView();
    public ImageView woman = new ImageView();
    public ImageView random = new ImageView();
    public Button submit = new Button(); 
    public Button back = new Button();
    public Button delete = new Button();
    public Button quit = new Button();
    public Stage stage;
    public Pane pane;
    public ProfileController profileController = new ProfileController();
    @Override
    public void start(Stage stage) throws Exception {
        ImageView imageView = new ImageView(new Image
                (Objects.requireNonNull
                        (RegisterMenu.class.getResource("/images/man.png")).toExternalForm()));
        User user = new User("doreece","123",imageView);
        GlobalData.setCurrentUser(user);
        this.stage = stage;
        Pane pane = new Pane();
        this.pane = pane;
        pane.setPrefSize(500, 600);
        pane.setStyle("-fx-background-color: #ecc7a3");
        designProfileMenu();
        Scene scene = new Scene(pane);
        stage.setTitle("aaGame");
        stage.getIcons().add(new Image(Objects.requireNonNull(RegisterMenu.class.getResource
                ("/images/aaIcon.png")).toExternalForm()));
        stage.setScene(scene);
        stage.show();
    }

    private void designProfileMenu() {
        designTextFields();
        designProfile();
        designImageViews();
        designButtons();
    }

    private void designTextFields() {
        username.setPromptText(GlobalData.getCurrentUser().getUsername());
        username.setFocusTraversable(false);
        username.setStyle("""
                -fx-prompt-text-fill: Black;
                -fx-background-color: transparent;
                -fx-border-color: transparent transparent #654a20 transparent;
                -fx-border-width: 1;
                -fx-border-insets: 0 0 1 0;
                -fx-border-style: hidden hidden solid hidden""");
        username.setLayoutX(20);
        username.setLayoutY(20);

        password.setPromptText(GlobalData.getCurrentUser().getPassword());
        password.setFocusTraversable(false);
        password.setStyle("""
                -fx-prompt-text-fill: Black;
                -fx-background-color: transparent;
                -fx-border-color: transparent transparent #654a20 transparent;
                -fx-border-width: 1;
                -fx-border-insets: 0 0 1 0;
                -fx-border-style: hidden hidden solid hidden""");
        password.setLayoutX(20);
        password.setLayoutY(60);

        pane.getChildren().add(username);
        pane.getChildren().add(password);
    }

    private void designProfile() {
        myProfile = GlobalData.getCurrentUser().getProfile();
        myProfile.setFitHeight(64);
        myProfile.setFitWidth(64);
        myProfile.setLayoutX(400);
        myProfile.setLayoutY(20);
        pane.getChildren().add(myProfile);
    }

    private void designImageViews() {
        man.setImage(new Image(Objects.requireNonNull
                (RegisterMenu.class.getResource("/images/man.png")).toExternalForm()));
        man.setFitHeight(64);
        man.setFitWidth(64);
        man.setLayoutX(50);
        man.setLayoutY(250);
        man.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                profileController.changeAvatar("man");
            }
        });

        woman.setImage(new Image(Objects.requireNonNull
                (RegisterMenu.class.getResource("/images/woman.png")).toExternalForm()));
        woman.setFitHeight(64);
        woman.setFitWidth(64);
        woman.setLayoutX(215);
        woman.setLayoutY(250);
        woman.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                profileController.changeAvatar("woman");
            }
        });

        random.setImage(new Image(Objects.requireNonNull
                (RegisterMenu.class.getResource("/images/question.png")).toExternalForm()));
        random.setFitHeight(64);
        random.setFitWidth(64);
        random.setLayoutX(380);
        random.setLayoutY(250);
        random.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                profileController.fromSystem(stage);
            }
        });

        pane.getChildren().add(man);
        pane.getChildren().add(woman);
        pane.getChildren().add(random);

    }

    private void designButtons() {

        quit.setStyle("-fx-background-color: #a00909; -fx-font-family: 'Times New Roman'");
        if (Settings.isEnglish) quit.setText("Logout");
        else quit.setText("خروچ");
        quit.setPrefSize(100, 60);
        quit.setLayoutX(15);
        quit.setLayoutY(500);
        quit.setOnMouseClicked(mouseEvent -> {
            stage.close();
            GlobalData.setCurrentUser(null);
            GlobalData.isGuest = false;
            RegisterMenu registerMenu = new RegisterMenu();
            registerMenu.start(new Stage());
        });

        delete.setStyle("-fx-background-color: #a00909; -fx-font-family: 'Times New Roman'");
        if (Settings.isEnglish) delete.setText("Delete Account");
        else delete.setText("پاک کردن اکانت");
        delete.setPrefSize(100, 60);
        delete.setLayoutX(135);
        delete.setLayoutY(500);
        delete.setOnMouseClicked(mouseEvent -> {
            stage.close();
            GlobalData.allUsers.remove(GlobalData.getCurrentUser());
            GlobalData.setCurrentUser(null);
            GlobalData.isGuest = false;
            RegisterMenu registerMenu = new RegisterMenu();
            registerMenu.start(new Stage());
        });

        submit.setStyle("-fx-background-color: #a00909; -fx-font-family: 'Times New Roman'");
        if (Settings.isEnglish) submit.setText("Submit");
        else submit.setText("اعمال");
        submit.setPrefSize(100, 60);
        submit.setLayoutX(255);
        submit.setLayoutY(500);
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                profileController.changeInformation(username.getText(),password.getText());
            }
        });


        back.setStyle("-fx-background-color: #a00909; -fx-font-family: 'Times New Roman'");
        if (Settings.isEnglish) back.setText("Back");
        else back.setText("بازگشت");
        back.setPrefSize(100, 60);
        back.setLayoutX(375);
        back.setLayoutY(500);
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });

        pane.getChildren().add(quit);
        pane.getChildren().add(delete);
        pane.getChildren().add(submit);
        pane.getChildren().add(back);
    }
}
