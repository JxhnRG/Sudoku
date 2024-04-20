package org.example.sudoku;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.sudoku.view.GameStage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameStage.getInstance();
    }
}
