package org.example.trainlogic.MainPackages;

import java.io.IOException;

public class LogIn {
    public static User login(Config config){
        View.view("Введите логин: ");
        String inputUsername = Controller.getString();
        View.view("Введите пароль: ");
        String inputPassword = Controller.getString();

        User user = config.getUser(inputUsername);

        if ((user != null) && user.getPassword().equals(inputPassword)) {
            View.view("Добро пажаловать - " + user.getUsername() + "\n");
            return user;
        }
        else {
            View.view("Неверный логин или пароль. \n");
            return null;
        }
    }

    public static User register(Config config){

        View.view("Введите новвый логин: ");
        String inputUsername = Controller.getString();

        if (config.getUser(inputUsername) != null) {
            View.view("Пользоваетль с таким логином уже существует. \n");
            return null;
        }
        View.view("Введите новый пароль: ");
        String inputPassword = Controller.getString();
        View.view("Введите группу (root/user): ");
        String inputGroup = Controller.getString();
        User user = new User(inputUsername, inputPassword, inputGroup);

        try{
            config.addUser(user);
            View.view("Регисрация успешна. \n");
        }catch (IOException e){
            View.view("Ощибка при регистрации: " + e.getMessage() + "\n" );
        }

        return user;
    }
}
