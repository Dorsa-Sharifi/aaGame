package model;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class User {
    private String username;
    private String password;
    private ImageView profile;
    public ArrayList<ScoreBoard> scoreRows = new ArrayList<>();

    public User(String username, String password, ImageView profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ImageView getProfile() {
        return profile;
    }

    public void setProfile(ImageView profile) {
        this.profile = profile;
    }
}
