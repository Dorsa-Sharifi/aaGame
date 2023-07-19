package controller;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.GlobalData;
import model.User;
import view.RegisterMenu;

import java.awt.*;
import java.io.File;
import java.util.Objects;

public class ProfileController {
    public ImageView newProfile = null;
    public void changeAvatar(String pictureName) {

        switch (pictureName){
            case "man":
                manAvatar();
                break;
            case "woman":
                womanAvatar();
                break;
        }
    }

    public void manAvatar(){
        newProfile = new ImageView(new Image(Objects.requireNonNull
                (RegisterMenu.class.getResource("/images/man.png")).toExternalForm()));
        GlobalData.getCurrentUser().setProfile(newProfile);
    }

    public void womanAvatar(){
        newProfile = new ImageView(new Image(Objects.requireNonNull
                (RegisterMenu.class.getResource("/images/woman.png")).toExternalForm()));
        GlobalData.getCurrentUser().setProfile(newProfile);
    }

    public void fromSystem(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null){
            String path = file.getAbsolutePath();
            Image image = new Image(path);
            GlobalData.getCurrentUser().getProfile().setImage(image);
        }
    }

    public void changeInformation(String username,String password){
        if (username == null || password == null){
            Alert profileAlert = new Alert(Alert.AlertType.ERROR);
            profileAlert.setTitle("Error in Profile Menu");
            profileAlert.setHeaderText("Error in changing user's information");
            profileAlert.setContentText("You haven't filled the username or password field properly!");
            profileAlert.showAndWait();
        } else {
            if (notRepeatedUsername(username)) {
                GlobalData.getCurrentUser().setUsername(username);
                GlobalData.getCurrentUser().setPassword(password);
            } else{
                Alert profileAlert = new Alert(Alert.AlertType.ERROR);
                profileAlert.setTitle("Error in Profile Menu");
                profileAlert.setHeaderText("Error in changing user's information");
                profileAlert.setContentText("There's already a user with this username!");
                profileAlert.showAndWait();
            }

        }
    }

    private boolean notRepeatedUsername(String username){
        User user = GlobalData.findUserByUsername(username);
        if (user != null ) {
            if (user == GlobalData.getCurrentUser()) {
                return true;
            } else{
                return false;
            }
        }
        return true;
    }
}
