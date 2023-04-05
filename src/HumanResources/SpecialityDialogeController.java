package HumanResources;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import humanhesourcescontract.Contract;
import humanresourcesemployee.Employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import speciality.Speciality;

/**
 * Asking for speciality's information by creating a new dialogue for it
 * @author Saad Turky
 * @version 9.5.2019
 *
 */
public class SpecialityDialogeController  implements ModalControllerInterface<Speciality>,Initializable  {
	  
	   @FXML private TextField editAla;
	   @FXML private TextField editAloitusvuosi;
       @FXML private TextField editHvko; 
       @FXML private Label labelVirhe;
       @FXML private GridPane gridSpeciality;
	   @FXML private ScrollPane panelSpeciality;
	   
	   
       @Override
       public void initialize(URL url, ResourceBundle bundle) {
  		 alusta();
  }
    
  	 
  	  @FXML  void handleCancel() {
  		  specialityKohdalla = null;
  		  ModalController.closeStage(labelVirhe);
  	    }

  	  
  	  @FXML  void handleOk() {
  		  if ( specialityKohdalla != null && specialityKohdalla.getAla().trim().equals("") ) {
  			           naytaVirhe("Nimi ei saa olla tyhjä");
  		           return;
  			        }
  			      ModalController.closeStage(labelVirhe);
  		    }
  	  
  	// ========================================================    
      private Speciality specialityKohdalla;
      private TextField edits[];
      private static int kentta = 0; 
      private static Speciality apuspeciality = new Speciality();
      
      
      /**
       * Creating GridPane speciality information
       * @param gridSpeciality where the data is created
       * @return created text fields
       */           
      public static TextField[] luoKentat(GridPane gridSpeciality) { 
    	  gridSpeciality.getChildren().clear(); 
          TextField[] edits = new TextField[apuspeciality.getKenttia()];      
          for (int i=0, k = apuspeciality.ekaKentta(); k < apuspeciality.getKenttia(); k++, i++) {
          	Label label = new Label(apuspeciality.getKysymys(k)); 
          	gridSpeciality.add(label, 0, i);    
              TextField edit = new TextField(); 
              edits[k] = edit; 
              edit.setId("e"+k); 
              gridSpeciality.add(edit, 1, i); 
          } 
          return edits; 
 }  
      
                
      /**
       * Empty text fields 
       * @param edits fields to be cleared
       */
 	 public static void tyhjenna(TextField[] edits) {
 	     for (TextField edit : edits)
 	     if (edit != null)  edit.setText("");
 	 }

 	 
 	/**
      * Returns the number from the component id
      * @param obj to be examined
      * @param default what value if id is not decent
      * @return component id number 
      */
 	  public static int getFieldId(Object obj, int oletus) { 
       if ( !( obj instanceof Node)) return oletus; 
       Node node = (Node)obj; 
 	   return Mjonot.erotaInt(node.getId().substring(1),oletus);  
 	   } 
 	 
      
 	   /**
	     * Makes other necessary initializations.
	     */
	    protected void alusta() {
	    	edits=luoKentat(gridSpeciality);
	    	for(TextField edit: edits) 
	    	if ( edit != null ) 
	          edit.setOnKeyReleased( e -> kasitteleMuutosSpecialities((TextField)(e.getSource()))); 
  		  panelSpeciality.setFitToHeight(true); 
	    }
      
      
	    @Override
	      public void setDefault(Speciality oletus) {
	    	specialityKohdalla = oletus;
	          naytaSpeciality(edits, specialityKohdalla);
	      }
	  
		          
	      @Override
	      public Speciality getResult() {
	          return specialityKohdalla;
	      }
	      
		  
	      private static void setKentta(int kenttaa) {
	    	  kentta=kenttaa;
	      }
	      
      
	      /**
	       * What to do when a dialog is displayed
	       */
	      @Override
	      public void handleShown() {
//	    	  editContractNumber.requestFocus();
	    	  kentta = Math.max(apuspeciality.ekaKentta(), Math.min(kentta, apuspeciality.getKenttia()-1));
	    	          edits[kentta].requestFocus();
	      }
	      
	      
	      private void naytaVirhe(String virhe) {
	          if ( virhe == null || virhe.isEmpty() ) {
	              labelVirhe.setText("");
	              labelVirhe.getStyleClass().removeAll("virhe");
	              return;
	          }
	          labelVirhe.setText(virhe);
	          labelVirhe.getStyleClass().add("virhe");
	      }
	      
	      
	      /**
	        * A change to a speciality is being processed
	        * @param edit changed field
	        */
          protected void kasitteleMuutosSpecialities( TextField editt) {
        	  if (specialityKohdalla == null) return; 
              int k = getFieldId(editt,apuspeciality.ekaKentta()); 
              String s = editt.getText(); 
   	          String virhe = null; 
   	          virhe = specialityKohdalla.aseta(k,s);  

              //virhe = employeeKohdalla.aseta(k,s); 
              if (virhe == null) {
                  Dialogs.setToolTipText(editt,"");
                  editt.getStyleClass().removeAll("virhe");
                 naytaVirhe(virhe);
             } else {
                 Dialogs.setToolTipText(editt,virhe);
                 editt.getStyleClass().add("virhe");
                 naytaVirhe(virhe);
              }
       }

          
          /**
           * Showing speciality information to TextField components
           * @param edits table with text fields
           * @param I share a speciality to show
           */
	      public static void naytaSpeciality(TextField[] edits, Speciality speciality) {
	          if (speciality == null) return;
	          for (int k = speciality.ekaKentta(); k < speciality.getKenttia(); k++) { 
	          edits[k].setText(speciality.anna(k)); 	        	   
} 
	     }
	      
	      
	      public void naytaSpeciality(Speciality speciality) {
	    	  naytaSpeciality(edits,speciality);       
	     }
	        
	      
	      /**
	       * Creating a speciality's question dialog and restoring the same record as modified or zero
	       * TODO: korjattava toimimaan
	       * @param modalityStage which is modal, for null = application
	       * @param oletus mitä dataan näytetään oletuksena
	       * @return null if Cancel is pressed, otherwise filled record
	       */
	      public static Speciality kysySpeciality(Stage modalityStage, Speciality oletus, int kentta) {
	          return ModalController.showModal(
	        		  ContractsDialogeController.class.getResource("SpecialityDialogeView.fxml"),
	                      "Edit Specialities",
	                       modalityStage, oletus,
	                       ctrl -> setKentta(kentta)
	                      );
	          }
	      
//	      public static Speciality kysyEmployee(Stage modalityStage, Speciality clone, int k) {
//	 		 return ModalController.showModal(
//	                  SpecialityDialogeController.class.getResource("SpecialityDialogeView.fxml"),
//	                  "MainWindow",
//	                     modalityStage, clone, null 
//	                  );
//	 	}
}