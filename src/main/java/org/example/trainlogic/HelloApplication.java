package org.example.trainlogic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.trainlogic.MainPackages.Config;
import org.example.trainlogic.MainPackages.LogMode;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("View/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);

        Config config = new Config("config.properties");
        HelloController controller = fxmlLoader.getController();
        controller.setConfig(config);

        stage.setTitle("Hello!");
        stage.setMinHeight(600);
        stage.setMinWidth(900);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}