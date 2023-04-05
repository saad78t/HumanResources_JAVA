package HumanResources;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * to start employee dialoge 
 * @author Saad Turky
 * @version 1.0, 12.4.2019
 *
 */
public class EmployeeDialogeMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("EmployeeDialogeGUIView.fxml"));
            final Pane root = ldr.load();
            //final EmployeeDialogeGUIController employeedialogeCtrl = (EmployeeDialogeGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("employeedialoge.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("EmployeeDialoge");
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