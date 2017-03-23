package org.academiadecodigo.roothless;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by codecadet on 08/03/17.
 */
public final class Navigation {

    private final String VIEW_PATH = "/view/";

    private LinkedList<Scene> scenes = new LinkedList<Scene>();
    private Map<String, Initializable> controllers = new HashMap<>();

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private Stage stage;
    private static Navigation instance = null;

   private Navigation(){
   }

    public static synchronized Navigation getInstance(){
        if(instance==null){
            instance=new Navigation();
        }
        return instance;

    }

    public void loadScreen(String view) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEW_PATH + view + ".fxml"));
            Parent root = loader.load();

            controllers.put(view, loader.<Initializable>getController());

            Scene scene= new Scene(root);
            scenes.push(scene);
            setScene(scene);

        } catch (IOException e) {
            System.out.println("Failure to load view " + view + " : " + e.getMessage());
        }


    }

    public Initializable getController(String key){
        return controllers.get(key);
    }

    public void back(){
        if(scenes.isEmpty()){
            return;
        }

        scenes.pop();

        setScene(scenes.peek());
    }

    private void setScene(Scene scene){

        stage.setScene(scene);

        stage.show();
    }


}
