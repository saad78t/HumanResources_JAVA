package HumanResources;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * to show the information of the program
 * @author Saad Turky
 * @version 28.4.2019
 *
 */
public class AboutMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("AboutView.fxml"));
            final Pane root = ldr.load();
            //final AboutGUIController aboutCtrl = (AboutGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("about.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("About");
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