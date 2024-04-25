package org.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStage extends Stage {
    public GameStage() throws IOException {
        //Importamos la Vista de bienvenida // We import the welcome view
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/org/example/sudoku/game-view.fxml"));
        //Creamos el Parent // We create the Parent
        Parent root= loader.load();
        //Creamos una nueva Escena // We create a new Scene
        Scene scene = new Scene(root);
        //Insertamos la Escena al Stage // We insert the Scene to the Stage
        setScene(scene);
        //Insertamos un titulo al Stage // We insert a title to the Stage
        setTitle("Sudoku");
        //Insertamos icono de la pestaña // We insert an icon to the window
        getIcons().add(new Image(String.valueOf(getClass().getResource("/org/example/sudoku/image/favicon.png"))));
        //Hacemos que el Stage no se pueda redimensionar // We make sure the Stage cant be resized
        setResizable(false);
        //Hacemos el show al stage // We show to the Stage
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
