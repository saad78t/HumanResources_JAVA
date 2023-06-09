package HumanResources;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * to start specialities dialoge
 * @author Saad Turky
 * @version 20.4.2019
 *
 */
public class SpecialityDialogeMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("SpecialityDialogeView.fxml"));
            final Pane root = ldr.load();
            //final SpecialityDialogeGUIController specialitydialogeCtrl = (SpecialityDialogeGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("specialitydialoge.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("SpecialityDialoge");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        launch(args);
    }
}