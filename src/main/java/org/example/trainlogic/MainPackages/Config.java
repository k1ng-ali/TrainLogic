package org.example.trainlogic.MainPackages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Config{
    private Map<String, User> users = new HashMap<>();
    private boolean debugMode;
    private boolean autotestsMode;
    private String filePath;

    public Config(String filePath) throws IOException {
        this.filePath = filePath;
        Properties props = new Properties();
        File configFile = new File(filePath);
        if(!configFile.exists()){
            configFile.createNewFile();
        }
        FileInputStream input = new FileInputStream(filePath);
        props.load(input);

        // Загружаем пользователей
        for (int i = 1; i <= props.size() / 3; i++) {  // Если 3 свойства на пользователя (username, password, group)
            String usernameKey = "user" + i + ".username";
            String passwordKey = "user" + i + ".password";
            String groupKey = "user" + i + ".group";

            if (props.containsKey(usernameKey)) {
                String username = props.getProperty(usernameKey);
                String password = props.getProperty(passwordKey);
                String group = props.getProperty(groupKey);
                users.put(username, new User(username, password, group));
            }
        }

        // Загружаем глобальные настройки
        this.debugMode = Boolean.parseBoolean(props.getProperty("debug"));
        this.autotestsMode = Boolean.parseBoolean(props.getProperty("autotests"));
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebug(boolean debugMode){this.debugMode =  debugMode;}

    public boolean isAutotestsMode() {
        return autotestsMode;
    }

    public void setAutotestsMode(boolean autotestsMode){this.autotestsMode = autotestsMode;}

    public void addUser(User user) throws IOException {
        if (users.containsKey(user.getUsername())) {
            throw new IllegalArgumentException("Пользователь с таким логином уже существует!\n");
        }

        User newUser = new User(user.getUsername(), user.getPassword(), user.getGroup());
        users.put(user.getUsername(), newUser);

        // Записываем нового пользователя в файл настроек
        Properties props = new Properties();
        FileInputStream input = new FileInputStream(filePath);
        props.load(input);
        input.close();

        int newUserIndex = users.size();
        props.setProperty("user" + newUserIndex + ".username", user.getUsername());
        props.setProperty("user" + newUserIndex + ".password", user.getPassword());
        props.setProperty("user" + newUserIndex + ".group", user.getGroup());

        FileOutputStream output = new FileOutputStream(filePath);
        props.store(output, null);
        output.close();
    }

    public void deleteUser(User user) throws IOException {
        Properties props = new Properties();
        FileInputStream input = new FileInputStream(filePath);
        props.load(input);
        input.close();

        int userIndex = users.size();

        props.remove(props.getProperty("user" + userIndex + ".username", user.getUsername()));
        props.remove(props.getProperty("user" + userIndex + ".password", user.getPassword()));
        props.remove(props.getProperty("user" + userIndex + ".group", user.getGroup()));

        users.remove(user.getUsername());
    }

    public void AddminSetting(User user) throws IOException
    {
        Properties props = new Properties();
        FileInputStream input = new FileInputStream(filePath);
        props.load(input);
        input.close();
        int choice;
        do {
            View.view("Вкл\\выкл режим Отладки . . . . . . . . . . . . 1\n");
            View.view("Вкл\\выкл режим Автотест. . . . . . . . . . . . 2\n");
            View.view("Выход . . . . . . . . . . . . . . . . . . . . . 3\n");

            choice = Controller.get_int();
            switch (choice)
            {
                case 1:
                    if (debugMode){
                        debugMode = false;
                        View.view("Режим отладки выключено\n");
                        LogMode.LogWrite("Пользователь " + user + " выключет режим Отладки");
                        props.setProperty("debug", "false");
                    }
                    else {
                        debugMode = true;
                        View.view("Режим отладки включено\n");
                        LogMode.LogWrite("Пользователь " + user + " включет режим Отладки");
                        props.setProperty("debug", "true");
                    }
                    break;
                case 2:
                    if (autotestsMode){
                        autotestsMode = false;
                        View.view("Режим автотест выключено\n");
                        LogMode.LogWrite("Пользователь " + user + " выключет режим Автотест");
                        props.setProperty("autotests", "false");
                    }
                    else {
                        autotestsMode =  true;
                        View.view("Режим автотест включено\n");
                        LogMode.LogWrite("Пользователь " + user + " включет режим Автотест");
                        props.setProperty("autotests", "true");
                    }
                    break;
                default:
                    View.view("Ощибка ввода! Введите целое число от 1 до 3 включительно\n");
            }
        }while (choice != 3);
        FileOutputStream output = new FileOutputStream(filePath);
        props.store(output, null);
        output.close();
    }


}

