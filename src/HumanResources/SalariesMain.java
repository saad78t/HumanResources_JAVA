package HumanResources;

import humanresourcesemployee.HumanResources;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * to start salary class 
 * @author Saad Turky
 * @version 1.0, 12.4.2019
 *
 */
public class SalariesMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("SalariesGUIView.fxml"));
            final Pane root = ldr.load();
            final SalariesGUIController salariesCtrl = (SalariesGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("salaries.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Salaries");
            primaryStage.show();
            
            HumanResources hr=new HumanResources();
            salariesCtrl.setHumanResources(hr);
            
            Application.Parameters params = getParameters();  
            if ( params.getRaw().size() > 0 )  
            	salariesCtrl.lueTiedosto(params.getRaw().get(0));   
            else 
                if ( !salariesCtrl.avaa() ) Platform.exit();
            
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