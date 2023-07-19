package model;

import java.util.ArrayList;


public class GlobalData {
    public static ArrayList<User> allUsers = new ArrayList<>();
    public static User currentUser;
    public static boolean isGuest;

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static void addToAllUsers(User newUser) {
        allUsers.add(newUser);
    }
    public static User findUserByUsername(String username){
        for (User user : allUsers) {
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        GlobalData.currentUser = currentUser;
    }
}
