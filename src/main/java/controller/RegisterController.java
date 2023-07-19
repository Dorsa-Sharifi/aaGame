package controller;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GlobalData;
import model.Settings;
import model.User;
import view.RegisterMenu;

public class RegisterController {
    ImageView imageView = new ImageView(new Image(RegisterMenu.class.getResource("/images/man.png").toExternalForm()));
    public void registerNewUser(String username, String password) {
        User searchForUser = GlobalData.findUserByUsername(username);
        if (searchForUser == null) {

            User newUser = new User(username, password, imageView);
            GlobalData.addToAllUsers(newUser);
            Alert registerAlert = new Alert(Alert.AlertType.CONFIRMATION);
            registerAlert.setTitle("Register Menu");
            registerAlert.setHeaderText("Success in Registering new User");
            registerAlert.setContentText("You registered successfully!");
            registerAlert.showAndWait();
        } else {
            Alert registerAlert = new Alert(Alert.AlertType.ERROR);
            registerAlert.setTitle("Error in Register Menu");
            registerAlert.setHeaderText("Error in  Registering new User");
            registerAlert.setContentText("There's already a user with the entered username!");
            registerAlert.showAndWait();
        }
    }

    public void loginUser(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            Alert registerAlert = new Alert(Alert.AlertType.ERROR);
            registerAlert.setTitle("Error in Login Menu");
            registerAlert.setHeaderText("Error in  Logging User");
            registerAlert.setContentText("You haven't filled the username or password field properly!");
            registerAlert.showAndWait();
        } else {
            User user = GlobalData.findUserByUsername(username);
            if (user != null){
                if (user.getPassword().equals(password)){
                    GlobalData.setCurrentUser(user);
                    Settings.user = user;
                    Alert registerAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    registerAlert.setTitle("Login Menu");
                    registerAlert.setHeaderText("Success in  Logging User");
                    registerAlert.setContentText("You are logged in as "+user.getUsername());
                    registerAlert.showAndWait();
                } else{
                    Alert registerAlert = new Alert(Alert.AlertType.ERROR);
                    registerAlert.setTitle("Error in Login Menu");
                    registerAlert.setHeaderText("Error in  Logging User");
                    registerAlert.setContentText("The entered password is wrong!");
                    registerAlert.showAndWait();
                }
            } else {
                Alert registerAlert = new Alert(Alert.AlertType.ERROR);
                registerAlert.setTitle("Error in Login Menu");
                registerAlert.setHeaderText("Error in  Logging User");
                registerAlert.setContentText("There's no user with given information!");
                registerAlert.showAndWait();
            }
        }
    }

    public void guestUser() {
        GlobalData.isGuest = true;
        User guest = new User("Guest","",imageView);
        GlobalData.setCurrentUser(guest);
    }
}
