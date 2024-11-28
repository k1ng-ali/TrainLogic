package org.example.trainlogic.MainPackages;
import java.io.IOException;
import java.util.LinkedList;

public class Navigation {
    public static User main_login(Config config) throws IOException {
        User user;
        int choice;
        do {
            View.view("Войти . . . . . . . . . . . . . . . . . . . 1\n");
            View.view("Зарегистрироваться. . . . . . . . . . . . . 2\n");
            View.view("Выход . . . . . . . . . . . . . . . . . . . 3\n");
            choice = Controller.get_int();

            switch (choice)
            {
                case 1:
                    user = LogIn.login(config);
                    if (user != null) {return user;};
                    break;
                case 2:
                    user = LogIn.register(config);
                    if (user != null){
                        if (config.isDebugMode()) LogMode.LogWrite("Пользователь "+ user.getUsername() + " Зарегистрирован c правами " + user.getGroup());
                        return user;
                    };
                    break;
                case 3:
                    return null;
            }
        }while (true);
    }
    public static void main_menu(Train train, Storages strg, User user, Config config) throws IOException {
        int choise;
        boolean append = true;
        boolean datas = true;
        do {
            int num_pm, num_strg, capacity;
            String cargo;
            View.view("Вывести информацию платформ. . . . . . . . . . . . . . . . . 1\n");
            View.view("Вывести информацию складов . . . . . . . . . . . . . . . . . 2\n");
            View.view("Добавить платформу . . . . . . . . . . . . . . . . . . . . . 3\n");
            View.view("добавить склад . . . . . . . . . . . . . . . . . . . . . . . 4\n");
            View.view("Добавить автомобилья на склад. . . . . . . . . . . . . . . . 5\n");
            View.view("Загрузить груз на платформу. . . . . . . . . . . . . . . . . 6\n");
            View.view("Разгрузить груз из платформы . . . . . . . . . . . . . . . . 7\n");
            View.view("Настройка админа . . . . . . . . . . . . . . . . . . . . . . 8\n");
            View.view("Сохранить данные на файл . . . . . . . . . . . . . . . . . . 9\n");
            View.view("Загрузить данные из файла. . . . . . . . . . . . . . . . . .10\n");
            View.view("Выход из программы . . . . . . . . . . . . . . . . . . . . .11\n");
            View.view("\nВыберите действие: ");

            choise = Controller.get_int();
            switch (choise)
            {
                case 1:
                    ViewInfo.info_platform(train);
                    break;
                case 2:
                    ViewInfo.info_strg(strg);
                    break;
                case 3:
                    AddObject.add_platform(train, config.isDebugMode(), user);
                    break;
                case 4:
                    AddObject.add_storage(strg, config.isDebugMode(), user );
                    break;
                case 5:
                    try {
                        AddObject.add_avto(strg, config.isDebugMode(), user);
                    }
                    catch (Exception e)
                    {View.view(e.getMessage());}
                    break;
                case 6:
                    try {
                        LoadUnload.load_plt(train, strg, config.isDebugMode());
                    }
                    catch (Exception e)
                    {View.view(e.getMessage());}
                    break;
                case 7:
                    try{
                        LoadUnload.unload_plt(train, strg, config.isDebugMode());
                    }
                    catch (Exception e)
                    {View.view(e.getMessage());}
                    break;
                case 8:
                    View.view(user.getGroup());
                    if (user.getGroup().equalsIgnoreCase("root")){
                        config.AddminSetting(user);
                    }
                    else{
                        View.view("Вы не являетесь админстратором");
                    }
                    break;
                case 9:
                    try {
                        FileManeger.SaveAll(train.getPlatforms(), "train.txt", append);
                        FileManeger.SaveAll(strg.getStorages(), "strg.txt", append);
                        View.view("Успещно сохранено!\n");
                    }
                    catch (Exception e)
                        {View.view(e.getMessage());}
                    break;
                case 10:
                    if (datas) {
                        try {
                            LinkedList<SerializableEntity> platforms = FileManeger.LoadAll("train.txt");
                            for (SerializableEntity platform : platforms) {
                                train.add_platform((Platform) platform);
                            }
                            View.view("Успещно загружено!\n");
                        } catch (Exception e) {
                            View.view(e.getMessage());
                        }
                        try {
                            LinkedList<SerializableEntity> storages = FileManeger.LoadAll("strg.txt");
                            for (SerializableEntity storage : storages) {
                                strg.add_storage((Storage) storage);
                            }
                            View.view("Успещно загружено!\n");
                            append = false;
                            datas = false;
                        } catch (Exception e) {
                            View.view(e.getMessage());
                        }
                    }
                    else {
                        View.view("Данные уже загружены!");
                    }

                default:
                    View.view("Ощибка ввода! Введите целое число от 1 до 11 включительно\n");

            }
        }while(choise != 11);
    }
}
