package HumanResources;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import humanresourcesemployee.Employee;
import humanresourcessalaries.Salary;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Asking for salary's information by creating a new dialogue for it
 * @author Saad Turky
 * @version 9.5.2019
 *
 */
public class SalaryDialogeController  implements ModalControllerInterface<Salary>,Initializable {
	 
	   @FXML private ScrollPane panelSalary;    
	   @FXML private GridPane   gridSalary;
	   @FXML private TextField editEmployeeNumber;
	   @FXML private TextField editContractNumber;
	   @FXML private TextField editMainsalary;
       @FXML private TextField editMOnth;   
	   @FXML private TextField editYear;
	   @FXML private TextField editBounce;
	   @FXML private TextField editLoans;
	   @FXML private TextField editTransallwance;
	   @FXML private TextField editNetSalary;
       @FXML private Label     labelVirhe;
       
	   
	    @FXML void keyReleased() {
          count();
	    }


       @Override
       public void initialize(URL url, ResourceBundle bundle) {
  		 alusta();
  }
    
  	 
  	  @FXML  void handleCancel() {
  		  salaryKohdalla = null;
  		  ModalController.closeStage(labelVirhe);
  	    }

  	  
  	  @FXML  void handleOk() {
  		  if ( salaryKohdalla == null ) {
  			           naytaVirhe("Nimi ei saa olla tyhjä");
  		           return;
  			        }
  			      ModalController.closeStage(labelVirhe);
  		    }
   
  	// ========================================================    
      private Salary salaryKohdalla;
      private TextField edits[];
      private static Salary apusalary = new Salary(); // Jäsen jolta voidaan kysellä tietoja.
      private static int kentta = 0;
   
      
      /**
       * Creating GridPane salary information
       * @param gridSalary where the data is created
       * @return created text fields
       */  
      public static TextField[] luoKentat(GridPane gridSalary) {
    	  gridSalary.getChildren().clear();
          TextField[] edits = new TextField[apusalary.getKenttia()];
          
          for (int i=0, k = apusalary.ekaKentta(); k < apusalary.getKenttia(); k++, i++) {
              Label label = new Label(apusalary.getKysymys(k));
              gridSalary.add(label, 0, i);
              TextField edit = new TextField();
              // edit.setOnKeyReleased((e) -> count());
              edits[k] = edit;
              edit.setId("e"+k);
              gridSalary.add(edit, 1, i);
          }
          return edits;
      }
      
      
      /**
       * Empty text fields 
       * @param edits fields to be cleared
       */
      public static void tyhjenna(TextField[] edits) {
          for (TextField edit: edits) 
              if ( edit != null ) edit.setText(""); 
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
	        edits = luoKentat(gridSalary);
	        for(TextField edit: edits) {
	        	if ( edit != null ) 
	        	edit.setOnKeyReleased(e -> kasitteleMuutosSalaried((TextField)(e.getSource())));
//	        	panelSalary.setFitToHeight(true);	        
	 }
	        
//	        editEmployeeNumber = edits[];   
//	        editContractNumber= edits1]; 
	        editMainsalary=edits[2];     
//	        editMOnth =edits[];          
//	        editYear =edits[];           
	        editBounce=edits[5];         
	        editLoans=edits[6];          
	        editTransallwance=edits[7];  
	        editNetSalary=edits[8];  
	        
	    }
      
      
     //
      public void setDefault(Salary oletus) {
          salaryKohdalla = oletus;
          naytaSalary(edits, salaryKohdalla);
      }
  
	          
      @Override
      public Salary getResult() {
          return salaryKohdalla;
      }
      
      @Override
      public void handleShown() {
    	  kentta = Math.max(apusalary.ekaKentta(), Math.min(kentta, apusalary.getKenttia()-1));
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
       * A change to a contract is being processed
       * @param edit changed field
       */ 
      protected void kasitteleMuutosSalaried(TextField editt) {
          if (salaryKohdalla == null) return;
          int k = getFieldId(editt,apusalary.ekaKentta());
          String s = editt.getText();
          String virhe = null;
          virhe = salaryKohdalla.aseta(k,s); 
          if (virhe == null) {
              Dialogs.setToolTipText(editt,"");
              editt.getStyleClass().removeAll("virhe");
             naytaVirhe(virhe);
         } else {
             Dialogs.setToolTipText(editt,virhe);
             editt.getStyleClass().add("virhe");
             naytaVirhe(virhe);
          }
          count();
   }
      
      
      /**
       * Showing salary information to TextField components
       * @param edits table with text fields
       * @param I share a salary to show
       */
      public static void naytaSalary(TextField[] edits, Salary salary) {
          if (salary == null) return;
          
          for (int k = salary.ekaKentta(); k < salary.getKenttia(); k++) {
              edits[k].setText(salary.anna(k));
          }
      }       
          
      
      public void naytaSalary(Salary salary) {
    	  naytaSalary(edits,salary );       
     }
      
      
      /**
       * Creating a salary's question dialog and restoring the same record as modified or zero
       * TODO: korjattava toimimaan
       * @param modalityStage which is modal, for null = application
       * @param oletus mitä dataan näytetään oletuksena
       * @return null if Cancel is pressed, otherwise filled record
       */
      public static Salary kysySalary(Stage modalityStage, Salary oletus, int k) {
          return ModalController.showModal(
                      SalaryDialogeController.class.getResource("SalaryDialogeView.fxml"),
                      "Edit",
                         modalityStage, oletus, 
                         ctrl -> setKentta(kentta)
                      );
          }


 	private static void setKentta(int kenttaa) {
 	    	  kentta=kenttaa;
 }
 	
    /**
     * to calculate employee's salary
     */
    private void count() {
    	double mainsalary = Double.parseDouble(editMainsalary.getText());
    	double bounce = Double.parseDouble(editBounce.getText());
    	double loance = Double.parseDouble(editLoans.getText());
    	double transallwance = Double.parseDouble(editTransallwance.getText()); 
    	double result =(mainsalary + bounce - loance + transallwance);
        editNetSalary.setText(String.valueOf(result));
        salaryKohdalla.aseta(8, String.valueOf(result));
    }
}