package HumanResources;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Omistaja
 * @version 24.4.2019
 *
 */
public class TietueDialogeMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("TietueDialogeView.fxml"));
            final Pane root = ldr.load();
            //final TietueDialogeGUIController tietuedialogeCtrl = (TietueDialogeGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("tietuedialoge.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("TietueDialoge");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    
    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}