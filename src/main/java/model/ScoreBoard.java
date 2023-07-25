package model;

import javafx.scene.image.ImageView;

public class ScoreBoard {
    private String username;
    private int minute;
    private int seconds;
    private int rate;
    private ImageView profile;
    private double difficulty;
    public ScoreBoard(String username, int minute , int seconds, int rate, ImageView profile, double difficulty) {
        this.username = username;
        this.minute = minute;
        this.seconds = seconds;
        this.rate = rate;
        this.profile = profile;
        this.difficulty = difficulty;
    }

    public String getUsername() {
        return username;
    }

    public int getMinute() {
        return minute;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getRate() {
        return rate;
    }

    public ImageView getProfile() {
        return profile;
    }

    public double getDifficulty() {
        return difficulty;
    }
}
