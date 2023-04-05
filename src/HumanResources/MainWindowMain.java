package HumanResources;

import fi.jyu.mit.fxgui.ModalController;
import humanresourcesemployee.HumanResources;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

/**
 *  Main program to start the HumanResource program
 * @author Saad Turky
 * @version 11.05.2019
 * 
 * CANCEL button for Login window 17.5.2019
 */
public class MainWindowMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
   
        	
			/**
			 * while loop for login window
			 * if the the username or password was wrong it will
			 * reload again or press cancel to log out
			 */
        	while (true) { 
        	String[] lo = null;
        	lo = ModalController.showModal(LoginPageGUIController.class.getResource("LoginPageGUIView.fxml"), "Login", null,null);   
        	System.out.println(lo[0].equals("Q")); 

        	if (lo[2].equals("c"))  {
        		Platform.exit();
        		break;
        		}
        	if (!lo[0].equals("q")) continue;	
        	if (!lo[1].equals("")) continue;         
        	break;
        	}
        	
        	        	
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("MainWindowGUIView.fxml"));
            final Pane root = ldr.load();
            final MainWindowGUIController mainwindowCtrl = (MainWindowGUIController) ldr.getController();
            Scene scene = new Scene(root,750,530);
            scene.getStylesheets().add(getClass().getResource("mainwindow.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Personal Information");
           
            primaryStage.setOnCloseRequest((event) -> {
                if ( !mainwindowCtrl.voikoSulkea() ) event.consume();
            });
            
            HumanResources hr=new HumanResources();
            mainwindowCtrl.setHumanResources(hr);
            
              
            primaryStage.show();
          
            Application.Parameters params = getParameters();  
            if ( params.getRaw().size() > 0 )  
            	mainwindowCtrl.lueTiedosto(params.getRaw().get(0));   
            else 
                if ( !mainwindowCtrl.avaa() ) Platform.exit();          
            
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