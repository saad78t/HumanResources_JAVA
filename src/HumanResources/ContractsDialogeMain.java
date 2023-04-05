package HumanResources;

import humanresourcesemployee.HumanResources;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * to start contracts dialoge
 * @author Saad Turky
 * @version 20.4.2019
 *
 */
public class ContractsDialogeMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("ContractsDialogeView.fxml"));
            final Pane root = ldr.load();
//            final ContractsDialogeController contractsdialogeCtrl = (ContractsDialogeController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("contractsdialoge.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("ContractsDialoge");
            
//            HumanResources hr=new HumanResources();
//            contractsdialogeCtrl.setHumanResources(hr);
            
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