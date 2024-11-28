package org.example.trainlogic;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.trainlogic.MainPackages.Config;
import org.example.trainlogic.MainPackages.LogMode;
import org.example.trainlogic.MainPackages.User;
import org.w3c.dom.Node;


public class HelloController {
    private Config config;
    protected LogMode mode;

    public void setConfig(Config config) {
        this.config = config;
        mode = new LogMode(config);
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox StartPage;

    @FXML
    private VBox SignIn;

    @FXML
    private VBox SignUp;

    @FXML
    private Button StartPageSignInButton;

    @FXML
    private Button StartPageSignUpButton;

    @FXML
    private Button SignInSignInButton;

    @FXML
    private Button SignInSignUpButton;

    @FXML
    private Button SignUpSignInButton;

    @FXML
    private Button SignUpSignUpButton;

    @FXML
    private TextField SignInTextFieldUSerName;

    @FXML
    private PasswordField SignInTextFieldPassword;

    @FXML
    private TextField SignUpTextFieldUserName;

    @FXML
    private PasswordField SignUpTextFieldPassword;

    @FXML
    private CheckBox SignUpCheckBoxUserGroup;

    @FXML
    private Text SignInSignInErrorText;

    @FXML
    private Text SignInSignUpSeccesText;

    @FXML
    void initialize() {
        StartPageSignInButton.setOnAction(actionEvent -> handleVisible(SignIn));
        StartPageSignUpButton.setOnAction(actionEvent -> handleVisible(SignUp));
        SignInSignInButton.setOnAction(actionEvent -> {
            String username = SignInTextFieldUSerName.getText().trim();
            String password = SignInTextFieldPassword.getText().trim();
            SignInHandler(username, password);
        });
        SignInTextFieldUSerName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                SignInTextFieldPassword.requestFocus();
            }
        });
        SignInTextFieldPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String username = SignInTextFieldUSerName.getText().trim();
                String password = SignInTextFieldPassword.getText().trim();
                SignInHandler(username, password);
            }
        });
        SignInSignUpButton.setOnAction(actionEvent -> handleVisible(SignUp));
        SignUpSignInButton.setOnAction(actionEvent -> handleVisible(SignIn));
        SignUpSignUpButton.setOnAction(actionEvent -> {
            String username = SignUpTextFieldUserName.getText().trim();
            String password = SignUpTextFieldPassword.getText().trim();
            String usergroup;
            if (SignUpCheckBoxUserGroup.isSelected()) {
                usergroup = "root";
            }
            else {
                usergroup = "user";
            }
            try {
                SignUpHandler(username, password, usergroup);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        SignUpTextFieldUserName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                SignUpTextFieldPassword.requestFocus();
            }
        });
        SignUpTextFieldPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String username = SignUpTextFieldUserName.getText().trim();
                String password = SignUpTextFieldPassword.getText().trim();
                String usergroup;
                if (SignUpCheckBoxUserGroup.isSelected()) {
                    usergroup = "root";
                }
                else {
                    usergroup = "user";
                }
                try {
                    SignUpHandler(username, password, usergroup);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    @FXML
    private void handleVisible(VBox vbox) {
        StartPage.setVisible(false);
        SignIn.setVisible(false);
        SignUp.setVisible(false);
        vbox.setVisible(true);
    }

    @FXML
    private void SignUpHandler(String username, String password, String userGroup) throws IOException {
        if (config.getUser(username) == null) {
            User user = new User(username, password, userGroup);
            config.addUser(user);
            handleVisible(SignIn);
            SignInSignUpSeccesText.setVisible(true);
            this.mode.setUser(user);
            mode.LogWrite("Регистрация нового пользователья");
        }
        else {
            ShakeAnimation ShakeUsername = new ShakeAnimation(SignUpTextFieldUserName);
            ShakeAnimation ShakePassword = new ShakeAnimation(SignUpTextFieldPassword);

            ShakeUsername.playAnimation();
            ShakePassword.playAnimation();
        }

    }

    @FXML
    private void SignInHandler(String username, String password) {
        User user = config.getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            openDashBoard(user);
        }
        else {
            ShakeAnimation ShakePassword = new ShakeAnimation(SignInTextFieldPassword);
            ShakeAnimation ShakeUserName = new ShakeAnimation(SignInTextFieldUSerName);

            ShakeUserName.playAnimation();
            ShakePassword.playAnimation();
        }
    }

    private void openDashBoard(User user){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/trainlogic/View/HomePage.fxml"));
            loader.setRoot(new AnchorPane());
            Parent root = loader.load();

            HomePageController controller = loader.getController();
            controller.setUser(user);
            controller.setConfig(this.config);
            this.mode.setUser(user);
            controller.setMode(this.mode);

            this.mode.LogWrite("Вход в программу");

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Trainlogic");
            stage.setMinHeight(600);
            stage.setMinWidth(900);
            stage.show();

            Stage currentStage = (Stage) StartPage.getScene().getWindow();
            if (currentStage != null) {
                currentStage.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не удалось загрузить интерфейс");
            alert.setContentText("Проверьте путь к FXML-файлу и его содержимое.");
            alert.showAndWait();
        }
    }


}
