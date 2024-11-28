package org.example.trainlogic.MainPackages;
import java.io.IOException;

// 1 РАЗРАБОТАВАТЬ МЕТОД ДОБАВЛЕНИЕ АВТОМОЬИЛЯ НА СКЛАД

// Демонстрация программы
public class Main {
    public static void main(String[] args) throws IOException {
        model();
    }

    public static void model() throws IOException {
        Train train = new Train();
        Storages storage = new Storages();
        Config config = new Config("config.properties");
        User user;

        user = Navigation.main_login(config);
        if (user != null) {
            LogMode logMode = new LogMode(user, config);
            if (config.isDebugMode()) logMode.LogWrite("Вход в программу");
            Navigation.main_menu(train, storage, user, config);
            if (config.isDebugMode()) logMode.LogWrite("Выход из пограммы");
        }
        View.view("Выход из программы...");
    }
}


