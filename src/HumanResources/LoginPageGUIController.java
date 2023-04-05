package HumanResources;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import humanresourcesemployee.*;
import HumanResources.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.security.auth.spi.LoginModule;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
/**
 * @author Saad Turky
 * @version 9.2.2019
 *
 */
public class LoginPageGUIController implements ModalControllerInterface<String[]> {
	  @FXML private Label labelVirhe;
	  	
       @FXML private TextField textPass;
	   @FXML  private TextField textVastaus;
	   private String user = "";
	   private String pass = "";
	   private String cancel = "";
	   @FXML private void handleLogin() {
//			System.out.println("you clicked me");
//			HumanResources humanresources = new HumanResources();
//			Parent main_window= FXMLLoader.load (getClass().getResource("MainWindowGUIView.fxml"));
//			HumanResources login = new HumanResources();
			
//		    ModalController.showModal(MainWindowGUIController.class.getResource("MainWindowGUIView.fxml"), "Personal Information", null, humanresources);
//			ModalController.closeStage(labelVirhe);
		    
//			Scene scene = new Scene(main_window);
//			Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
//			app.hide();
//			app.setScene(scene);
//			app.show();	    
			
		  user = textVastaus.getText();
		  pass =textPass.getText();
		   ModalController.closeStage(textVastaus);
//		   ModalController.closeStage(textPass);
		
 }
	  	 
	   
	   @FXML private void handleCancel() {
		   
//		   ModalController.closeStage(labelVirhe);
		   cancel="c";
		   ModalController.closeStage(textVastaus);
		   
//		   Platform.exit();
	    }
	   
	   
	   @Override
	    public String[] getResult() {
		   String [] array= {user,pass,cancel};
	        return array;
	    }

	    
	    @Override
	    public void setDefault(String[] oletus) {
//	        textVastaus.setText(oletus);
	    }

	    
	    /**
	     * What to do when a dialog is displayed
	     */
	    @Override
	    public void handleShown() {
	        textVastaus.requestFocus();
//	        textPass.requestFocus();
	    }
	    
	    
	    /**
	     * Creating a name query dialog and returning the name written to it or zero
	     * @param modalityStage which is modal, for null = application
	     * @param oletus which name is displayed by default
	     * @return null if you press Cancel, otherwise written name
	     */
	    public static String kysyNimi(Stage modalityStage, String oletus) {
	        return ModalController.showModal(
	               LoginPageGUIController.class.getResource(" LoginPageGUIView.fxml"),
	                "Login",
	                modalityStage, oletus);
	    }
}