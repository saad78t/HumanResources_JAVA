package HumanResources;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * to start salary dialoge 
 * @author Saad Turky
 * @version 1.0, 12.4.2019
 *
 */
public class SalaryDialogeMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("SalaryDialogeView.fxml"));
            final Pane root = ldr.load();
            //final SalaryDialogeGUIController salarydialogeCtrl = (SalaryDialogeGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("salarydialoge.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("SalaryDialoge");
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