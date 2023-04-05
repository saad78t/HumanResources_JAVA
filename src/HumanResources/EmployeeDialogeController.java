package HumanResources;
import java.net.URL;
import java.util.ResourceBundle;
import humanresourcesemployee.Employee;
import humanresourcesemployee.HumanResources;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
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
 * Asking for employee's information by creating a new dialogue for it
 * @author Saad Turky
 * @version 9.5.2019
 *
 */
public class EmployeeDialogeController implements ModalControllerInterface<Employee>,Initializable  {
	 
	 @FXML  private TextField editEmployeeNumber;
	 @FXML private TextField editName; 
	 @FXML private TextField editSurName;
	 @FXML private TextField editNationality; 
	 @FXML private TextField editPostalCode;	    
	 @FXML private TextField editPostalAddress;	    
	 @FXML private TextField editHourRealization;
	 @FXML private TextField editAddress;
     @FXML private TextField editSalary;       
     @FXML private TextField editDateofBirth;   
     @FXML private TextField editPhoneNumber;
     @FXML private TextField editSpeciality;   
     @FXML private ScrollPane panelEmployee;
    // @FXML private GridPane gridEmployee;
     

     @FXML private Label labelVirhe;
     
     @FXML private GridPane gridEmployee;
     
     
	 @Override
     public void initialize(URL url, ResourceBundle bundle) {
		 alusta();
}
  
	 
	  @FXML  void handleCancel() {
		  employeeKohdalla = null;
		  ModalController.closeStage(labelVirhe);
	    }

	  
	  @FXML  void handleOk() {
		  if ( employeeKohdalla != null && employeeKohdalla.getName().trim().equals("") ) {
			           naytaVirhe("Nimi ei saa olla tyhjä");
		           return;
			        }
			      ModalController.closeStage(labelVirhe);
		    }
 
	    

	// ========================================================    
	        private Employee employeeKohdalla;
	        private TextField edits[];
	        private static Employee apuemployee = new Employee(); // Jäsen jolta voidaan kysellä tietoja.
	        private static int kentta = 0;
	        
	        
	        /**
	         * Creating GridPane employee information
	         * @param gridEmployee where the data is created
	         * @return created text fields
	         */
	        public static TextField[] luoKentat(GridPane gridEmployee) {
	        	gridEmployee.getChildren().clear();
	            TextField[] edits = new TextField[apuemployee.getKenttia()];
	            
	            for (int i=0, k = apuemployee.ekaKentta(); k < apuemployee.getKenttia(); k++, i++) {
	                Label label = new Label(apuemployee.getKysymys(k));
	                gridEmployee.add(label, 0, i);
	                TextField edit = new TextField();
	                edits[k] = edit;
	                edit.setId("e"+k);
	                gridEmployee.add(edit, 1, i);
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
//	        edits = luoKentat(gridEmployee);
////	        int i=0;
//	        for(TextField edit: edits) {
////	        	final int k = i++;
//	        	edit.setOnKeyReleased(e -> kasitteleMuutosEmployeers((TextField)(e.getSource())));
//	        	panelEmployee.setFitToHeight(true);
	    	edits = luoKentat(gridEmployee);
	    	       for (TextField edit : edits)
	    	           if ( edit != null )
	    	               edit.setOnKeyReleased( e -> kasitteleMuutosEmployeers((TextField)(e.getSource())));
	    	       panelEmployee.setFitToHeight(true);
	        }
	   

	    
	    @Override
      public void setDefault(Employee oletus) {
          employeeKohdalla = oletus;
          naytaEmployee(edits, employeeKohdalla);
      }
  
	          
      @Override
      public Employee getResult() {
          return employeeKohdalla;
      }
      
	          
      /**
       * What to do when a dialog is displayed
       */
      @Override
      public void handleShown() {
    	  kentta = Math.max(apuemployee.ekaKentta(), Math.min(kentta, apuemployee.getKenttia()-1));
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
        * A change to a employee is being processed
        * @param edit changed field
        */
          protected void kasitteleMuutosEmployeers(TextField editt) {
              if (employeeKohdalla == null) return;
              int k = getFieldId(editt,apuemployee.ekaKentta());
              String s = editt.getText();
              String virhe = null;
//              switch(k) {
//              case 1 : virhe = employeeKohdalla.setName(s); break;
//              case 2 : virhe = employeeKohdalla.setSurName(s); break;
//              case 3 : virhe = employeeKohdalla.setNationality(s); break;
//              case 4 : virhe = employeeKohdalla.setAddress(s); break;
//              default:
//              }
              virhe = employeeKohdalla.aseta(k,s); 
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
     * Showing employee information to TextField components
     * @param edits table with text fields
     * @param I share an employee to show
     */
    public static void naytaEmployee(TextField[] edits, Employee employee) {
        if (employee == null) return;
        
        for (int k = employee.ekaKentta(); k < employee.getKenttia(); k++) {
            edits[k].setText(employee.anna(k));
        }
        
//         edits[0].setText(String.valueOf(employee.getEmployeeNumber()));       
//         edits[1].setText(employee.getName());
//         edits[2].setText(employee.getSurName());                         
//         edits[3].setText(employee.getNationality());
//         edits[7].setText(employee.getAddress());
        //editName.setText(employee.getName());
   }
      
    
    public void naytaEmployee( Employee employee) {
        naytaEmployee(edits,employee );       
   }
    
    
    /**
      * Creating a employee's question dialog and restoring the same record as modified or zero
      * TODO: korjattava toimimaan
      * @param modalityStage which is modal, for null = application
      * @param oletus mitä dataan näytetään oletuksena
      * @return null if Cancel is pressed, otherwise filled record
      */
     public static Employee kysyEmployee(Stage modalityStage, Employee oletus, int k) {
         return ModalController.showModal(
                     EmployeeDialogeController.class.getResource("EmployeeDialogeView.fxml"),
                     "Edit Of Employees",
                        modalityStage, oletus, 
                        ctrl -> setKentta(kentta)
                     );
         }


	private static void setKentta(int kenttaa) {
	    	  kentta=kenttaa;
	      }	  
  }