package view;

import controller.RegisterController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Settings;

import java.util.Objects;
import java.util.Set;

public class RegisterMenu extends Application {

    public Stage stage;
    public Pane pane;
    public Button register = new Button();
    public Button guest = new Button();
    public Button loginButton = new Button();
    public Button back = new Button();
    public TextField enterUsername = new TextField();
    public TextField enterPassword = new TextField();
    public Text header = new Text();
    public Text login = new Text();
    public RegisterController registerController = new RegisterController();


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) {
        this.stage = stage;
        Pane pane = new Pane();
        this.pane = pane;
        pane.setPrefSize(500, 600);
        pane.setStyle("-fx-background-color: #ecc7a3");
        designRegisterMenu();
        Scene scene = new Scene(pane);
        stage.setTitle("aaGame");
        stage.getIcons().add(new Image(Objects.requireNonNull(RegisterMenu.class.getResource
                ("/images/aaIcon.png")).toExternalForm()));
        stage.setScene(scene);
        stage.show();
    }

    public void designRegisterMenu() {
        designHeaderText();
        designTextFields();
        designButtons();
    }

    private void designHeaderText() {
        if (Settings.isEnglish) header.setText("Welcome to aa!");
        else header.setText("به aa خوش آمدید!");
        header.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
        header.setLayoutX(187);
        header.setLayoutY(120);
        pane.getChildren().add(header);
    }

    private void designTextFields() {
        if (Settings.isEnglish) enterUsername.setPromptText("Enter username");
        else enterUsername.setPromptText("نام کاربری");
        enterUsername.setFocusTraversable(false);
        enterUsername.setStyle("""
                -fx-prompt-text-fill: Black;
                -fx-background-color: transparent;
                -fx-border-color: transparent transparent #654a20 transparent;
                -fx-border-width: 1;
                -fx-border-insets: 0 0 1 0;
                -fx-border-style: hidden hidden solid hidden""");
        enterUsername.setLayoutX(177);
        enterUsername.setLayoutY(160);

        if (Settings.isEnglish) enterPassword.setPromptText("Enter password");
        else enterPassword.setPromptText("رمز عبور");
        enterPassword.setFocusTraversable(false);
        enterPassword.setStyle("""
                -fx-prompt-text-fill: Black;
                -fx-background-color: transparent;
                -fx-border-color: transparent transparent #654a20 transparent;
                -fx-border-width: 1;
                -fx-border-insets: 0 0 1 0;
                -fx-border-style: hidden hidden solid hidden""");
        enterPassword.setLayoutX(177);
        enterPassword.setLayoutY(200);

        pane.getChildren().add(enterUsername);
        pane.getChildren().add(enterPassword);

    }

    private void designButtons() {
        register.setStyle("-fx-background-color: #a00909; -fx-font-family: 'Times New Roman'");
        if (Settings.isEnglish) register.setText("Register");
        else register.setText("رجیستر");
        register.setPrefSize(100, 30);
        register.setLayoutX(203);
        register.setLayoutY(250);
        register.setOnMouseClicked(mouseEvent -> {
            if (!enterUsername.getText().isEmpty() && !enterPassword.getText().isEmpty()) {
                registerController.registerNewUser(enterUsername.getText(), enterPassword.getText());
                enterUsername.setText("");
                enterPassword.setText("");
            } else {
                Alert registerAlert = new Alert(Alert.AlertType.ERROR);
                registerAlert.setTitle("Error in Register Menu");
                registerAlert.setHeaderText("Error in Registering new User");
                registerAlert.setContentText("You haven't filled the username or password field properly!");
                registerAlert.showAndWait();
            }
        });

        guest.setStyle("-fx-background-color: #a00909; -fx-font-family: 'Times New Roman'");
        if (Settings.isEnglish) guest.setText("Guest Mode");
        else guest.setText("میهمان");
        guest.setPrefSize(100, 30);
        guest.setLayoutX(203);
        guest.setLayoutY(300);
        guest.setOnMouseClicked(mouseEvent -> registerController.guestUser());

        login.setFill(Color.rgb(160, 9, 9, 1));
        login.setStyle("-fx-font-family: 'Times New Roman'");
        login.setLayoutX(190);
        if (Settings.isEnglish) login.setText("Already have an account?");
        else {
            login.setLayoutX(220);
            login.setText("آیا اکانت دارید ؟");
        }
        login.setLayoutY(400);
        login.setOnMouseClicked(mouseEvent -> enterLoginMenu());

        pane.getChildren().add(register);
        pane.getChildren().add(guest);
        pane.getChildren().add(login);
    }

    private void enterLoginMenu() {
        pane.getChildren().clear();
        pane.setStyle("-fx-background-color: #ecc7a3");
        designHeaderText();
        designTextFields();
        loginButton.setStyle("-fx-background-color: #a00909; -fx-font-family: 'Times New Roman'");
        if (Settings.isEnglish) loginButton.setText("Login");
        else loginButton.setText("ورود");
        loginButton.setPrefSize(100, 30);
        loginButton.setLayoutX(203);
        loginButton.setLayoutY(250);
        loginButton.setOnMouseClicked(mouseEvent -> {
            registerController.loginUser(enterUsername.getText(), enterPassword.getText());
            enterUsername.setText("");
            enterPassword.setText("");
        });

        back.setStyle("-fx-background-color: #a00909; -fx-font-family: 'Times New Roman'");
        if (Settings.isEnglish) back.setText("Register Menu");
        else back.setText("رجیستر منو");
        back.setPrefSize(100, 30);
        back.setLayoutX(203);
        back.setLayoutY(300);
        back.setOnMouseClicked(mouseEvent -> {
            pane.getChildren().clear();
            pane.setStyle("-fx-background-color: #ecc7a3");
            designRegisterMenu();
        });
        pane.getChildren().add(loginButton);
        pane.getChildren().add(back);
    }
}
