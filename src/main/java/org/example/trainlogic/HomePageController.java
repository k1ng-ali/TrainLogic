package org.example.trainlogic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.trainlogic.MainPackages.*;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;

public class HomePageController {
    private User user;
    private LogMode mode;
    private Config config;
    private LogicControl logicControl = new LogicControl(config, user);

    public void setUser(User user) {
        this.user = user;
    }
    public void setMode(LogMode mode) {
        this.mode = mode;
    }
    public void setConfig(Config config) {
        this.config = config;
    }


    @FXML
    void initialize() {
        PlatformLoad.setOnAction(actionEvent -> {
            logicControl.SetPlatformTable(PlatformTable, PlatformText);
        });
        StorageLoad.setOnAction(actionEvent -> {
            logicControl.SetStorageTable(StorageTable, StorageText);
        });
        PlatformSave.setOnAction(actionEvent -> logicControl.SaveDatas(PlatformText));
        StorageSave.setOnAction(actionEvent -> logicControl.SaveDatas(StorageText));
        UpdateDatasPlatform.setOnAction(actionEvent -> {
            logicControl.UpdateDatasPlatform(PlatformTable, PlatformText);
        });
        UpdateDatasStorage.setOnAction(actionEvent -> {
            logicControl.UpdateDatasStorage(StorageTable, StorageText);
        });
        ObservableList<String> PlatformType = FXCollections.observableArrayList("Платформа для жидкостей",
                "Платформа для сепучых грузов",
                "Платформа для контейнеров",
                "Платформа для автомобилей");
        ObservableList<String> StorageType = FXCollections.observableArrayList("Склад для жидкостей",
                "Склад для сепучых грузов",
                "Склад для контейнеров",
                "Склад для автомобилей");
        PlatformAddType.setItems(PlatformType);
        PlatformAddType.setValue("Выберите тип Платформы");
        StorageAddType.setItems(StorageType);
        StorageAddType.setValue("Выберите тип Склада");
        PlatformAddType.setOnAction(actionEvent -> {
            logicControl.SetPlatformCargoType(PlatformAddType, PlatformAddTypeCargo,
                    PlatformAddMass, PlatformAddMaxMass, PlatformAddNum, PlatformAddButton);
        });
        StorageAddType.setOnAction(actionEvent -> {
            logicControl.SetStorageCargoType(StorageAddType, StorageAddTypeCargo,
                    StorageAddMass, StorageAddMAxMass, StorageAddNum, StorageAddButton);
        });
        PlatformAddTypeCargo.setOnAction(actionEvent -> {
            PlatformAddMass.setDisable(false);
            PlatformAddMaxMass.setDisable(false);
            PlatformAddNum.setDisable(false);
        });
        StorageAddTypeCargo.setOnAction(actionEvent -> {
            StorageAddMass.setDisable(false);
            StorageAddMAxMass.setDisable(false);
            StorageAddNum.setDisable(false);
        });
        PlatformAddNum.setOnKeyPressed(event -> {
           if(event.getCode() == KeyCode.ENTER) {
               PlatformAddMass.requestFocus();
           }
           else {
               logicControl.SetAddButton(PlatformAddButton,
                       PlatformAddMass, PlatformAddMaxMass, PlatformAddNum);
           }
        });
        PlatformAddMass.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                PlatformAddMaxMass.requestFocus();
            }
            else {
                logicControl.SetAddButton(PlatformAddButton,
                        PlatformAddMass, PlatformAddMaxMass, PlatformAddNum);
            }
        });
        PlatformAddMaxMass.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                logicControl.PlatformAdd(PlatformAddType, PlatformAddTypeCargo,
                        PlatformAddMass, PlatformAddMaxMass, PlatformAddNum, PlatformAddText,
                        PlatformAddButton);
            }
            else {
                logicControl.SetAddButton(PlatformAddButton,
                        PlatformAddMass, PlatformAddMaxMass, PlatformAddNum);
            }
        });
        PlatformAddButton.setOnAction(actionEvent -> {
            logicControl.PlatformAdd(PlatformAddType, PlatformAddTypeCargo,
                    PlatformAddMass, PlatformAddMaxMass, PlatformAddNum, PlatformAddText,
                    PlatformAddButton);

        });
        StorageAddNum.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                StorageAddMass.requestFocus();
            }
            else {
                logicControl.SetAddButton(StorageAddButtom,
                        StorageAddMass, StorageAddMAxMass, StorageAddNum);
            }
        });
        StorageAddMass.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                StorageAddMAxMass.requestFocus();
            }
            else {
                logicControl.SetAddButton(StorageAddButtom,
                        StorageAddMass, StorageAddMAxMass, StorageAddNum);
            }
        });
        StorageAddMAxMass.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                logicControl.StorageAdd(StorageAddType, StorageAddTypeCargo,
                        StorageAddMass, StorageAddMAxMass, StorageAddNum, StorageAddText,
                        StorageAddButton);

            }
            else {
                logicControl.SetAddButton(StorageAddButtom,
                        StorageAddMass, StorageAddMAxMass, StorageAddNum);
            }
        });
        StorageAddButton.setOnAction(actionEvent -> {
            logicControl.StorageAdd(StorageAddType, StorageAddTypeCargo,
                    StorageAddMass, StorageAddMAxMass, StorageAddNum, StorageAddText,
                    StorageAddButton);
        });
    }

    @FXML
    private Text PlatformAddText;

    @FXML
    private Text StorageAddText;

    @FXML
    private Button PlatformAddButton;

    @FXML
    private Button StorageAddButton;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab Add;

    @FXML
    private AnchorPane AddPane;

    @FXML
    private TabPane AddTab;

    @FXML
    private Text PlatformText;

    @FXML
    private Text StorageText;

    @FXML
    private Tab AdminSettings;

    @FXML
    private CheckBox AdminSettingsAutotestMode;

    @FXML
    private CheckBox AdminSettingsLogMode;

    @FXML
    private AnchorPane AdminSettingsPane;

    @FXML
    private Button AdminSettingsSaveButtom;

    @FXML
    private Button UpdateDatasPlatform;

    @FXML
    private Button UpdateDatasStorage;

    @FXML
    private VBox AdminSettingsVBox;

    @FXML
    private Tab AvtomobileAdd;

    @FXML
    private VBox AvtomobileAddBox;

    @FXML
    private Button AvtomobileAddButtom;

    @FXML
    private ChoiceBox<?> AvtomobileAddChoiceStorage;

    @FXML
    private TextField AvtomobileAddCount;

    @FXML
    private TextField AvtomobileAddMass;

    @FXML
    private TextField AvtomobileAddModel;

    @FXML
    private AnchorPane AvtomobileAddPane;

    @FXML
    private Button LoadButtom;

    @FXML
    private ChoiceBox<?> LoadChoicePlatform;

    @FXML
    private ChoiceBox<?> LoadChoiceStorage;

    @FXML
    private HBox LoadHBox;

    @FXML
    private TextField LoadMassCargo;

    @FXML
    private AnchorPane LoadPane;

    @FXML
    private Tab LoadTab;

    @FXML
    private Text LoadTextChoice;

    @FXML
    private AnchorPane LoadUnloadPane;

    @FXML
    private Tab LoadUnloadTab;

    @FXML
    private TabPane LoadUnloadTabPane;

    @FXML
    private VBox LoadVBox;

    @FXML
    private AnchorPane MainAnchPane;

    @FXML
    private TabPane MainTab;

    @FXML
    private VBox PlatformAddBox;

    @FXML
    private TextField PlatformAddMass;

    @FXML
    private TextField PlatformAddMaxMass;

    @FXML
    private TextField PlatformAddNum;

    @FXML
    private AnchorPane PlatformAddPane;

    @FXML
    private Tab PlatformAddTab;

    @FXML
    private ChoiceBox<String> PlatformAddType;

    @FXML
    private ChoiceBox<String> PlatformAddTypeCargo;

    @FXML
    private Tab PlatformList;

    @FXML
    private AnchorPane PlatformListPane;

    @FXML
    private Button PlatformLoad;

    @FXML
    private Button PlatformSave;

    @FXML
    private TableView<?> PlatformTable;

    @FXML
    private VBox StorageAddBox;

    @FXML
    private Button StorageAddButtom;

    @FXML
    private TextField StorageAddMAxMass;

    @FXML
    private TextField StorageAddMass;

    @FXML
    private TextField StorageAddNum;

    @FXML
    private AnchorPane StorageAddPane;

    @FXML
    private ChoiceBox<String> StorageAddType;

    @FXML
    private ChoiceBox<String> StorageAddTypeCargo;

    @FXML
    private Tab StorageList;

    @FXML
    private AnchorPane StorageListPane;

    @FXML
    private Button StorageLoad;

    @FXML
    private Button StorageSave;

    @FXML
    private TableView<?> StorageTable;

    @FXML
    private Button UnloadButtom;

    @FXML
    private ChoiceBox<?> UnloadChoicePlatform;

    @FXML
    private ChoiceBox<?> UnloadChoiceStorage;

    @FXML
    private HBox UnloadHBox;

    @FXML
    private TextField UnloadMassCargo;

    @FXML
    private AnchorPane UnloadPane;

    @FXML
    private Tab UnloadTab;

    @FXML
    private Text UnloadTextChoice;

    @FXML
    private VBox UnloadVBox;



}

