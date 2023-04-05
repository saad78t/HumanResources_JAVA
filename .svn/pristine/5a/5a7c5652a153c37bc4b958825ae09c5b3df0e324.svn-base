package HumanResources;

import humanresourcesemployee.HumanResources;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

/**
 * @author Saad Turky
 * @version 11.05.2019
 * 
 * Main program to start the Contracts class
 */
public class ContractsMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("ContractsGUIView.fxml"));
            final Pane root = ldr.load();
            final ContractsGUIController contractsCtrl = (ContractsGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("contracts.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Contracts");
            
            HumanResources hr=new HumanResources();
            contractsCtrl.setHumanResources(hr);
            
             primaryStage.show();
           
             
             Application.Parameters params = getParameters();  
             if ( params.getRaw().size() > 0 )  
            	 contractsCtrl.lueTiedosto(params.getRaw().get(0));   
             else 
                 if ( !contractsCtrl.avaa() ) Platform.exit();
             
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