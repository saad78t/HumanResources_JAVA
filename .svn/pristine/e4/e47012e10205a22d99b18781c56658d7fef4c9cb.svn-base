package HumanResources;

import java.net.URL;
import javafx.scene.Node; 
import javafx.scene.control.ScrollPane;
import java.util.ResourceBundle;
import humanresourcesemployee.Employee;
import humanresourcesemployee.HumanResources;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import humanhesourcescontract.Contract;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Asking for contract's information by creating a new dialogue for it
 * @author Saad Turky
 * @version 9.5.2019
 *
 */
public class ContractsDialogeController implements ModalControllerInterface<Contract>,Initializable{
	  
	   @FXML private TextField editContractNumber;
	   @FXML private TextField editEmployeeNumber;
	   @FXML private TextField editAnnualHolidays;
	   @FXML private TextField editDateofStarting;
	   @FXML private TextField editDateofexpirey;
	   @FXML private TextField editDailyHours;
	   @FXML private TextField editAdditionalTime;
	   @FXML private TextField editContractperiod; 
	   @FXML private Label labelVirhe;
//	   @FXML private GridPane gridDialogeContract;
	   @FXML private GridPane gridContract;
	   @FXML  private ScrollPane panelContract;
	   

	   
	   @Override
	     public void initialize(URL url, ResourceBundle bundle) {
			 alusta();
	}
	   
	   
	   @FXML void handleCancel() {
		   contractKohdalla = null;
			  ModalController.closeStage(labelVirhe);
	    }

	    
	   @FXML void handleOk() {
		   if ( contractKohdalla != null && contractKohdalla.getContractNumbers().trim().equals("") ) {
	           naytaVirhe("Contract Number ei saa olla tyhjä");
           return;
	        }
	      ModalController.closeStage(labelVirhe);
    }
	    
	   
	// ========================================================    
       private Contract contractKohdalla;
       private TextField edits[];
       private static int kentta = 0; 
       private static Contract apucontract = new Contract(); // Jäsen jolta voidaan kysellä tietoja.
       
       
       /**
        * Creating GridPane contract information
        * @param gridContract where the data is created
        * @return created text fields
        */          
        public static TextField[] luoKentat(GridPane gridContract) { 
        	gridContract.getChildren().clear(); 
            TextField[] edits = new TextField[apucontract.getKenttia()];      
            for (int i=0, k = apucontract.ekaKentta(); k < apucontract.getKenttia(); k++, i++) {
            	Label label = new Label(apucontract.getKysymys(k)); 
            	gridContract.add(label, 0, i);    
                TextField edit = new TextField(); 
                edits[k] = edit; 
                edit.setId("e"+k); 
                gridContract.add(edit, 1, i); 
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
	    	edits=luoKentat(gridContract);
	    	for(TextField edit: edits) 
	    	if ( edit != null ) 
	          edit.setOnKeyReleased( e -> kasitteleMuutosContracts((TextField)(e.getSource()))); 
    		  panelContract.setFitToHeight(true); 
//	        edits = new TextField [] {editContractNumber,editEmployeeNumber,editAnnualHolidays,
//	 editDateofStarting,editDateofexpirey,editDailyHours,editAdditionalTime,editContractperiod};
//	        		
//	        int i=0;
//	        for(TextField edit: edits) {
//	        	final int k = i++;
//	        	edit.setOnKeyReleased(e -> kasitteleMuutosContracts(k,(TextField)(e.getSource())));
//	        	//panelEmployee.setFitToHeight(true);
	        }

 
	    @Override
	      public void setDefault(Contract oletus) {
	    	contractKohdalla = oletus;
	          naytaContract(edits, contractKohdalla);
	      }
	  
		          
	      @Override
	      public Contract getResult() {
	          return contractKohdalla;
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
	    	  kentta = Math.max(apucontract.ekaKentta(), Math.min(kentta, apucontract.getKenttia()));
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
          protected void kasitteleMuutosContracts( TextField editt) {
        	  if (contractKohdalla == null) return; 
              int k = getFieldId(editt,apucontract.ekaKentta()); 
              String s = editt.getText(); 
   	          String virhe = null; 
   	          virhe = contractKohdalla.aseta(k,s);  
//              switch(k) {
//              case 1 : virhe = contractKohdalla.setDateOfStarting(s); break;
//              case 2 : virhe = contractKohdalla.setContractPeriod(s); break;
//              case 3 : virhe = contractKohdalla.setDateOfExpiry(s);   break;
//              case 4 : virhe = contractKohdalla.setAnnualHolidays(s); break;     
//              default:
//              }
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
           * Showing contract information to TextField components
           * @param edits table with text fields
           * @param I share a contract to show
           */
	      public static void naytaContract(TextField[] edits, Contract contract) {
	          if (contract == null) return;
	          for (int k = contract.ekaKentta(); k < contract.getKenttia(); k++) { 
	          edits[k].setText(contract.anna(k)); 	        	   
} 
//	           edits[0].setText(String.valueOf(contract.getEmployeeNumber()));       
//	           edits[1].setText(String.valueOf(contract.getContractNumber()));
//	           edits[2].setText(String.valueOf(contract.getAnnualHolidays()));                        
//	           edits[3].setText(contract.getDateOfStarting());
//	           edits[4].setText(contract.getDateOfExpiry());
//	           edits[7].setText(contract.getContractPeriod());
	          //editContractperiod.setText(contract.getContractPeriod());
	     }
	        
	      
	      public void naytaContract( Contract contract) {
	          naytaContract(edits,contract );       
	     }
	      
 
	      /**
	       * Creating a contract's question dialog and restoring the same record as modified or zero
	       * TODO: korjattava toimimaan
	       * @param modalityStage which is modal, for null = application
	       * @param oletus mitä dataan näytetään oletuksena
	       * @return null if Cancel is pressed, otherwise filled record
	       */
	      public static Contract kysyContract(Stage modalityStage, Contract oletus, int kentta) {
	          return ModalController.showModal(
	        		  ContractsDialogeController.class.getResource("ContractsDialogeView.fxml"),
	                      "Contracts",
	                       modalityStage, oletus,
	                       ctrl -> setKentta(kentta)
	                      );
	          }
}