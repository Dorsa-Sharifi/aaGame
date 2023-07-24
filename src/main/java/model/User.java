package model;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class User {
    private String username;
    private String password;
    private ImageView profile;
    public ArrayList<Integer> scores = new ArrayList<>();
    public ArrayList<ScoreBoard> scoreRows = new ArrayList<>();

    public User(String username, String password, ImageView profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;
        scoreRows.add(new ScoreBoard("dorsa",1 , 20,2 , null));
        scoreRows.add(new ScoreBoard("dorsa",1 ,10, 2 , null));
        scoreRows.add(new ScoreBoard("dorsa",2 , 1,3 , null));
        scoreRows.add(new ScoreBoard("dorsa",3 , 1,1 , null));
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

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public void addToScores(int newScore) {
        scores.add(newScore);
    }

    public void sortTheScores() {
        Collections.sort(scores);
        for (Integer score : scores) {
            System.out.println(score);
        }
    }
}
