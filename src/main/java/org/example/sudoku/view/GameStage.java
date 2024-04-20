package org.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStage extends Stage {
    public GameStage() throws IOException {
        //Importamos la Vista de bienvenida
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/org/example/sudoku/game-view.fxml"));
        //Creamos el Parent
        Parent root= loader.load();
        //Creamos una nueva Escena
        Scene scene = new Scene(root);
        //Insertamos la Escena al Stage
        setScene(scene);
        //Insertamos un titulo al Stage
        setTitle("Sudoku");
        //Hacemos que el Stage no se pueda redimensionar
        setResizable(false);
        //Hacemos el show al stage
        show();
    }
    public static GameStage getInstance() throws IOException{
        return GameStageHolder.INSTANCE = new GameStage();
    }

    public static void deleteInstance(){
        GameStageHolder.INSTANCE.close();
        GameStageHolder.INSTANCE=null;
    }

    private static class GameStageHolder{
        private static GameStage INSTANCE;
    }
}
