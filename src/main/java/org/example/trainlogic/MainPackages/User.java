package org.example.trainlogic.MainPackages;

public class User {
    private String username;
    private String password;
    private String group;

    public User(String username, String password, String group) {
        this.username = username;
        this.password = password;
        this.group = group;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getGroup() {
        return group;
    }

}
