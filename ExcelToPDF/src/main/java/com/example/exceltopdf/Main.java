package com.example.exceltopdf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/window.fxml"));
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setTitle("Excel预览");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        launch(args);
    }

}
