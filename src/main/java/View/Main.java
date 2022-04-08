package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import Controller.HomeController;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("home.fxml")));
        primaryStage.setTitle("Home");
        primaryStage.setScene(new Scene(root, 906, 570));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

