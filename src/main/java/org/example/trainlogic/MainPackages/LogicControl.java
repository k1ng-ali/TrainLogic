package org.example.trainlogic.MainPackages;
import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.LinkedList;

public class LogicControl {
    private Train train = new Train();
    private Storages storage = new Storages();
    private Config config;
    private User user;
    private boolean append = true;
    private boolean datas = true;

    public LogicControl(){};

    public LogicControl(Config config, User user) {
        this.config = config;
        this.user = user;
    }

    public void LoadDatas(Text text) {
        if(datas) {
            try {
                LinkedList<SerializableEntity> platforms = FileManeger.LoadAll("train.txt");
                for (SerializableEntity platform : platforms) {
                    this.train.add_platform((Platform) platform);
                }
                text.setText("Успещно загружено!");
                text.setFill(Color.GREEN);
                text.setVisible(true);
                View.view("Успещно загружено!\n");
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(event -> {text.setVisible(false);});
                pause.play();
            } catch (Exception e) {
                View.view(e.getMessage());
            }
            try {
                LinkedList<SerializableEntity> storages = FileManeger.LoadAll("strg.txt");
                for (SerializableEntity storage : storages) {
                    this.storage.add_storage((Storage) storage);
                }
                View.view("Успещно загружено!\n");
                text.setText("Успещно загружено!");
                text.setFill(Color.GREEN);
                text.setVisible(true);
                this.append = false;
                this.datas = false;

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(event -> {text.setVisible(false);});
                pause.play();
            } catch (Exception e) {
                View.view(e.getMessage());
            }
        }
        else {
            text.setText("Данные уже загружено!");
            text.setFill(Color.GREEN);
            text.setVisible(true);

            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {text.setVisible(false);});
            pause.play();
        }
    }

    public void SaveDatas(Text text) {
        try {
            FileManeger.SaveAll(this.train.getPlatforms(), "train.txt", append);
            FileManeger.SaveAll(this.storage.getStorages(),
                    "strg.txt", append);
            text.setText("Успещно Сохранено!");
            text.setFill(Color.GREEN);
            text.setVisible(true);
            View.view("Успещно сохранено!\n");

            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {text.setVisible(false);});
            pause.play();
        }
        catch (Exception e)
        {View.view(e.getMessage());}
    }

    public void SetPlatformTable(TableView table, Text text){
        LoadDatas(text);

        table.getColumns().clear();

        TableColumn<Platform, Number> NumberColumn = new TableColumn<>("Номер платформы");
        NumberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNum_platform()));

        TableColumn<Platform, Number> CapacityColumn = new TableColumn<>("Грузоподъемность");
        CapacityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMax_capacity()));

        TableColumn<Platform, Number> MassColumn = new TableColumn<>("Масса груза");
        MassColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCapacity()));

        TableColumn<Platform, String> TypeCargo = new TableColumn<>("Тип Груза");
        TypeCargo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetTypeCargo()));

        table.getColumns().addAll(NumberColumn, CapacityColumn, MassColumn, TypeCargo);

        ObservableList<Platform> data = FXCollections.observableArrayList(train.getPlatforms());
        table.setItems(data);

    }

    public void UpdateDatasPlatform(TableView table, Text text){
        table.getColumns().clear();

        TableColumn<Platform, Number> NumberColumn = new TableColumn<>("Номер платформы");
        NumberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNum_platform()));

        TableColumn<Platform, Number> CapacityColumn = new TableColumn<>("Грузоподъемность");
        CapacityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMax_capacity()));

        TableColumn<Platform, Number> MassColumn = new TableColumn<>("Масса груза");
        MassColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCapacity()));

        TableColumn<Platform, String> TypeCargo = new TableColumn<>("Тип Груза");
        TypeCargo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetTypeCargo()));

        table.getColumns().addAll(NumberColumn, CapacityColumn, MassColumn, TypeCargo);

        ObservableList<Platform> data = FXCollections.observableArrayList(train.getPlatforms());
        try {
            table.setItems(data);
            text.setText("Данные обновлено!");
            text.setFill(Color.GREEN);
            text.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {text.setVisible(false);});
            pause.play();

        }catch (Exception e){
            View.view(e.getMessage());
        }
    }

    public void UpdateDatasStorage(TableView table, Text text){
        table.getColumns().clear();

        TableColumn<Storage, Number> NumberColumn = new TableColumn<>("Номер склада");
        NumberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNum_storage()));

        TableColumn<Storage, Number> CapacityColumn = new TableColumn<>("Вместимость");
        CapacityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMax_capacity()));

        TableColumn<Storage, Number> MassColumn = new TableColumn<>("Масса груза");
        MassColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCapacity()));

        TableColumn<Storage, String> TypeCargo = new TableColumn<>("Тип груза");
        TypeCargo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetTypeCargo()));

        table.getColumns().addAll(NumberColumn, CapacityColumn, MassColumn, TypeCargo);

        ObservableList<Storage> data = FXCollections.observableArrayList(storage.getStorages());
        try {
            table.setItems(data);
            text.setText("Данные обновлено!");
            text.setFill(Color.GREEN);
            text.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {text.setVisible(false);});
            pause.play();

        }catch (Exception e){
            View.view(e.getMessage());
        }
    }

    public void SetStorageTable(TableView table, Text text){
        LoadDatas(text);

        table.getColumns().clear();

        TableColumn<Storage, Number> NumberColumn = new TableColumn<>("Номер склада");
        NumberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNum_storage()));

        TableColumn<Storage, Number> CapacityColumn = new TableColumn<>("Вместимость");
        CapacityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMax_capacity()));

        TableColumn<Storage, Number> MassColumn = new TableColumn<>("Масса груза");
        MassColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCapacity()));

        TableColumn<Storage, String> TypeCargo = new TableColumn<>("Тип груза");
        TypeCargo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetTypeCargo()));

        table.getColumns().addAll(NumberColumn, CapacityColumn, MassColumn, TypeCargo);

        ObservableList<Storage> data = FXCollections.observableArrayList(storage.getStorages());
        table.setItems(data);
    }

    public void SetPlatformCargoType(ChoiceBox<String> platformType, ChoiceBox<String> CargoType,
                                     TextField Mass, TextField MaxMass, TextField Num, Button button) {
        String choosenPtType = platformType.getValue();
        switch (choosenPtType) {
            case "Платформа для жидкостей":
                ObservableList<String> Type1 = FXCollections.observableArrayList("Для нефть",
                        "Для дизельное топливо",
                        "Для мазут");
                CargoType.setItems(Type1);
                CargoType.setValue("Выберите тип груза");
                CargoType.setDisable(false);
                Mass.setDisable(true);
                MaxMass.setDisable(true);
                Num.setDisable(true);
                button.setDisable(true);
                Mass.clear();
                MaxMass.clear();
                Num.clear();
                break;
            case "Платформа для сепучых грузов":
                ObservableList<String> Type2 = FXCollections.observableArrayList("Для зерно",
                        "Для уголь",
                        "Для песок");
                CargoType.setItems(Type2);
                CargoType.setValue("Выберите тип груза");
                CargoType.setDisable(false);
                Mass.setDisable(true);
                MaxMass.setDisable(true);
                Num.setDisable(true);
                button.setDisable(true);
                Mass.clear();
                MaxMass.clear();
                Num.clear();
                break;
            case "Платформа для контейнеров":
                CargoType.setDisable(true);
                Mass.setDisable(false);
                MaxMass.setDisable(false);
                Num.setDisable(false);
                button.setDisable(true);
                Mass.clear();
                MaxMass.clear();
                Num.clear();
                break;
            case "Платформа для автомобилей":
                CargoType.setDisable(true);
                Mass.setDisable(false);
                MaxMass.setDisable(false);
                Num.setDisable(false);
                button.setDisable(true);
                Mass.clear();
                MaxMass.clear();
                Num.clear();
                break;
        }
    }

    public void SetStorageCargoType(ChoiceBox<String> StorageType, ChoiceBox<String> CargoType,
                TextField Mass, TextField MaxMass, TextField Num, Button button) {
            String choosenPtType = StorageType.getValue();
        switch (choosenPtType){
            case "Склад для жидкостей":
                ObservableList<String> Type1 = FXCollections.observableArrayList("Для нефть",
                        "Для дизельное топливо",
                        "Для мазут");
                CargoType.setItems(Type1);
                CargoType.setValue("Выберите тип груза");
                CargoType.setDisable(false);
                Mass.setDisable(true);
                MaxMass.setDisable(true);
                Num.setDisable(true);
                button.setDisable(true);
                Mass.clear();
                MaxMass.clear();
                Num.clear();
                break;
            case "Склад для сепучых грузов":
                ObservableList<String> Type2 = FXCollections.observableArrayList("Для зерно",
                        "Для уголь",
                        "Для песок");
                CargoType.setItems(Type2);
                CargoType.setValue("Выберите тип груза");
                CargoType.setDisable(false);
                Mass.setDisable(true);
                MaxMass.setDisable(true);
                Num.setDisable(true);
                button.setDisable(true);
                Mass.clear();
                MaxMass.clear();
                Num.clear();
                break;
            case  "Склад для контейнеров":
                CargoType.setDisable(true);
                Mass.setDisable(false);
                MaxMass.setDisable(false);
                Num.setDisable(false);
                button.setDisable(true);
                Mass.clear();
                MaxMass.clear();
                Num.clear();
                break;
            case "Склад для автомобилей":
                CargoType.setDisable(true);
                Mass.setDisable(false);
                MaxMass.setDisable(false);
                Num.setDisable(false);
                Mass.clear();
                MaxMass.clear();
                Num.clear();
                button.setDisable(true);
                break;
        }

    }
    public void SetAddButton(Button buttom,
                             TextField Mass, TextField MaxMass, TextField Num){
        if (!Mass.getText().trim().equals("") && !MaxMass.getText().trim().equals("") &&
        !Num.getText().trim().equals("")) {
            buttom.setDisable(false);
        }
        else {buttom.setDisable(true);}
    }

    public void PlatformAdd(ChoiceBox<String> PlatformType, ChoiceBox<String> CargoType,
                            TextField Mass, TextField MaxMass, TextField Num, Text text,
                            Button button){
        String choosenPtType = PlatformType.getValue();
        String choosenCargoType = CargoType.getValue();
        int mass=0;
        int maxMass=-1;
        int num=-1;
        try {
            mass = Integer.parseInt(Mass.getText().trim());
        }catch (NumberFormatException e){
            text.setText("Ощибка: В поле масса введите только цифры");
            text.setFill(Color.RED);
            text.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {text.setVisible(false);});
            pause.play();
            return;
        }
        try {
            maxMass = Integer.parseInt(MaxMass.getText().trim());
        }catch (NumberFormatException e){
            text.setText("Ощибка: В поле вместимость введите только цифры");
            text.setFill(Color.RED);
            text.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {text.setVisible(false);});
            pause.play();
            return;
        }
        try {
            num = Integer.parseInt(Num.getText().trim());
        }catch (NumberFormatException e){
            text.setText("Ощибка: В поле номер введите только цифры");
            text.setFill(Color.RED);
            text.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {text.setVisible(false);});
            pause.play();
            return;
        }
        boolean isloaded;
        if (mass==maxMass || mass!= -1 || maxMass!=-1){
            isloaded = true;
        }
        else {isloaded = false;}
        if (mass <= maxMass) {
            switch (choosenPtType) {
                case "Платформа для жидкостей":
                    LiquidPm liquid = null;
                    switch (choosenCargoType) {
                        case "Для нефть":
                            liquid = new LiquidPm(num, mass, maxMass, isloaded, "Нефть", mass);
                            break;
                        case "Для дизельное топливо":
                            liquid = new LiquidPm(num, mass, maxMass, isloaded, "Дизельное топливо", mass);
                            break;
                        case "Для мазут":
                            liquid = new LiquidPm(num, mass, maxMass, isloaded, "Мазут", mass);
                            break;
                    }
                    if (liquid != null) {
                        try {
                            this.train.add_platform((Platform) liquid);
                            text.setText("Успешно добавлено!");
                            text.setFill(Color.GREEN);
                            text.setVisible(true);
                            PauseTransition pause = new PauseTransition(Duration.seconds(3));
                            pause.setOnFinished(event -> {text.setVisible(false);});
                            pause.play();
                        }catch (Exception e){
                            text.setText("Ощибка!");
                            text.setFill(Color.RED);
                            text.setVisible(true);
                            PauseTransition pause = new PauseTransition(Duration.seconds(3));
                            pause.setOnFinished(event -> {text.setVisible(false);});
                            pause.play();
                        }
                    }
                    break;
                case "Платформа для сепучых грузов":
                    GritsPm grits = null;
                    switch (choosenCargoType) {
                        case "Для зерно":
                            grits = new GritsPm(num, mass, maxMass, isloaded, "Зерно", mass);
                            break;
                        case "Для уголь":
                            grits = new GritsPm(num, mass, maxMass, isloaded, "Уголь", mass);
                            break;
                        case "Для песок":
                            grits = new GritsPm(num, mass, maxMass, isloaded, "Песок", mass);
                            break;
                    }
                    if (grits != null) {
                        try {
                            this.train.add_platform((Platform) grits);
                            text.setText("Успешно добавлено!");
                            text.setFill(Color.GREEN);
                            text.setVisible(true);
                            PauseTransition pause = new PauseTransition(Duration.seconds(3));
                            pause.setOnFinished(event -> {text.setVisible(false);});
                            pause.play();
                        }catch (Exception e){
                            text.setText("Ощибка!");
                            text.setFill(Color.RED);
                            text.setVisible(true);
                            PauseTransition pause = new PauseTransition(Duration.seconds(3));
                            pause.setOnFinished(event -> {text.setVisible(false);});
                            pause.play();
                        }
                    }
                    break;
                case "Платформа для контейнеров":
                    try {
                        ContainerPm container = new ContainerPm(num, mass, maxMass, isloaded);
                        this.train.add_platform((Platform) container);
                        text.setText("Успешно добавлено!");
                        text.setFill(Color.GREEN);
                        text.setVisible(true);
                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.setOnFinished(event -> {text.setVisible(false);});
                        pause.play();
                    }catch (Exception e){
                        text.setText("Ощибка!");
                        text.setFill(Color.RED);
                        text.setVisible(true);
                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.setOnFinished(event -> {text.setVisible(false);});
                        pause.play();
                    }
                    break;
                case "Платформа для автомобилей":
                    try {
                        AvtomobilePm avtomobile = new AvtomobilePm(num, mass, maxMass, isloaded);
                        this.train.add_platform((Platform) avtomobile);
                        text.setText("Успешно добавлено!");
                        text.setFill(Color.GREEN);
                        text.setVisible(true);
                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.setOnFinished(event -> {text.setVisible(false);});
                        pause.play();
                    }catch (Exception e){
                        text.setText("Ощибка!");
                        text.setFill(Color.RED);
                        text.setVisible(true);
                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.setOnFinished(event -> {text.setVisible(false);});
                        pause.play();
                    }
                    break;

            }
            Mass.clear();
            MaxMass.clear();
            Num.clear();
            PlatformType.setValue("Выберите тип Платформы");
            CargoType.setValue("Выберите тип груза");
            Mass.setDisable(true);
            MaxMass.setDisable(true);
            Num.setDisable(true);
            CargoType.setDisable(true);
            button.setDisable(true);
        }
        else {
            text.setText("Ощибка: Масса не должно превыщать грузоподъемность");
            text.setFill(Color.RED);
            text.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {text.setVisible(false);});
            pause.play();
        }
    }

    public void StorageAdd(ChoiceBox<String> StorageType, ChoiceBox<String> CargoType,
                            TextField Mass, TextField MaxMass, TextField Num, Text text,
                           Button button){
        String choosenPtType = StorageType.getValue();
        String choosenCargoType = CargoType.getValue();
        int mass=0;
        int maxMass=-1;
        int num=-1;
        try {
            mass = Integer.parseInt(Mass.getText().trim());
        }catch (NumberFormatException e){
            text.setText("Ощибка: В поле масса введите только цифры");
            text.setFill(Color.RED);
            text.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {text.setVisible(false);});
            pause.play();
            return;
        }
        try {
            maxMass = Integer.parseInt(MaxMass.getText().trim());
        }catch (NumberFormatException e){
            text.setText("Ощибка: В поле вместимость введите только цифры");
            text.setFill(Color.RED);
            text.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {text.setVisible(false);});
            pause.play();
            return;
        }
        try {
            num = Integer.parseInt(Num.getText().trim());
        }catch (NumberFormatException e){
            text.setText("Ощибка: В поле номер введите только цифры");
            text.setFill(Color.RED);
            text.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {text.setVisible(false);});
            pause.play();
            return;
        }
        if (mass <= maxMass) {
            switch (choosenPtType) {
                case "Склад для жидкостей":
                    LiquidStrg liquid = null;
                    switch (choosenCargoType) {
                        case "Для нефть":
                            liquid = new LiquidStrg(num, mass, maxMass, "Нефть", mass);
                            break;
                        case "Для дизельное топливо":
                            liquid = new LiquidStrg(num, mass, maxMass,  "Дизельное топливо", mass);
                            break;
                        case "Для мазут":
                            liquid = new LiquidStrg(num, mass, maxMass,  "Мазут", mass);
                            break;
                    }
                    if (liquid != null) {
                        try {
                            this.storage.add_storage((Storage) liquid);
                            text.setText("Успешно добавлено!");
                            text.setFill(Color.GREEN);
                            text.setVisible(true);
                            PauseTransition pause = new PauseTransition(Duration.seconds(3));
                            pause.setOnFinished(event -> {text.setVisible(false);});
                            pause.play();
                        }catch (Exception e){
                            text.setText("Ощибка!");
                            text.setFill(Color.RED);
                            text.setVisible(true);
                            PauseTransition pause = new PauseTransition(Duration.seconds(3));
                            pause.setOnFinished(event -> {text.setVisible(false);});
                            pause.play();
                        }
                    }
                    break;
                case "Склад для сепучых грузов":
                    GritsStrg grits = null;
                    switch (choosenCargoType) {
                        case "Для зерно":
                            grits = new GritsStrg(num, mass, maxMass, "Зерно", mass);
                            break;
                        case "Для уголь":
                            grits = new GritsStrg(num, mass, maxMass, "Уголь", mass);
                            break;
                        case "Для песок":
                            grits = new GritsStrg(num, mass, maxMass, "Песок", mass);
                            break;
                    }
                    if (grits != null) {
                        try {
                            this.storage.add_storage((Storage) grits);
                            text.setText("Успешно добавлено!");
                            text.setFill(Color.GREEN);
                            text.setVisible(true);
                            PauseTransition pause = new PauseTransition(Duration.seconds(3));
                            pause.setOnFinished(event -> {text.setVisible(false);});
                            pause.play();
                        }catch (Exception e){
                            text.setText("Ощибка!");
                            text.setFill(Color.RED);
                            text.setVisible(true);
                            PauseTransition pause = new PauseTransition(Duration.seconds(3));
                            pause.setOnFinished(event -> {text.setVisible(false);});
                            pause.play();
                        }
                    }
                    break;
                case "Склад для контейнеров":
                    try {
                        ContainerStrg container = new ContainerStrg(num, mass, maxMass);
                        this.storage.add_storage((Storage) container);
                        text.setText("Успешно добавлено!");
                        text.setFill(Color.GREEN);
                        text.setVisible(true);
                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.setOnFinished(event -> {text.setVisible(false);});
                        pause.play();
                    }catch (Exception e){
                        text.setText("Ощибка!");
                        text.setFill(Color.RED);
                        text.setVisible(true);
                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.setOnFinished(event -> {text.setVisible(false);});
                        pause.play();
                    }
                    break;
                case "Склад для автомобилей":
                    try {
                        AvtomobileStrg avtomobile = new AvtomobileStrg(num, mass, maxMass);
                        this.storage.add_storage((Storage) avtomobile);
                        text.setText("Успешно добавлено!");
                        text.setFill(Color.GREEN);
                        text.setVisible(true);
                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.setOnFinished(event -> {text.setVisible(false);});
                        pause.play();
                    }catch (Exception e){
                        text.setText("Ощибка!");
                        text.setFill(Color.RED);
                        text.setVisible(true);
                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.setOnFinished(event -> {text.setVisible(false);});
                        pause.play();
                    }
                    break;

            }
            Mass.clear();
            MaxMass.clear();
            Num.clear();
            StorageType.setValue("Выберите тип Склада");
            CargoType.setValue("Выберите тип груза");
            Mass.setDisable(true);
            MaxMass.setDisable(true);
            Num.setDisable(true);
            CargoType.setDisable(true);
            button.setDisable(true);
        }
        else {
            text.setText("Ощибка: Масса не должно превыщать вместительность");
            text.setFill(Color.RED);
            text.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {text.setVisible(false);});
            pause.play();
        }
    }


    public void setTrain(Train train) {
        this.train = train;
    }

    public void setStorage(Storages storage) {
        this.storage = storage;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
