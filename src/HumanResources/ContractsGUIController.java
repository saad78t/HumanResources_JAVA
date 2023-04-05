package HumanResources;
import static HumanResources.ContractsDialogeController.getFieldId; 
import humanresourcesemployee.*;
import java.io.PrintStream;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import humanhesourcescontract.Contract;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import humanhesourcescontract.Contract.*;

/**
 * A class to manage events in the humanresource's interface.
 * 
 * @author Saad Turky
 * @version 11.05.2019
 * @version 25.03.2019 - Added Contracts Processing
 */
public class ContractsGUIController implements ModalControllerInterface<HumanResources>, Initializable{
	
	/**
     *  Working on adding a new contract
     */
	  @Override
	    public void initialize(URL url, ResourceBundle bundle) {
	        alusta();      
	    }

	  	  
       @FXML private TextField editContractNumber;
	   @FXML private TextField editEmployeeNumber;
	   @FXML private TextField editAnnualHolidays;
	   @FXML private TextField editDateofStarting;
	   @FXML private TextField editDateofexpirey;
	   @FXML private TextField editDailyHours;
	   @FXML private TextField editAdditionalTime;
	   @FXML private TextField editContractperiod; 
	   @FXML private Label labelVirhe; 
	   @FXML private GridPane gridContract;
	   @FXML private ScrollPane panelContract; 
	   @FXML private ListChooser<Contract> chooserContracts;
	   @FXML private Label labelVirhe1; //for the search
	   @FXML private TextField hakuehto;
	   @FXML private ComboBoxChooser<String> cbKentat;
	   

	   @FXML void handleHakuehto() {
//		   if ( employeeKohdalla != null )
//	            hae(employeeKohdalla.getEmployeeNumber()); 
		   hae(0);
	    }
	   
	   
	   @FXML private void handleNew() { // TODO: move inside a new method
	   newContract();
//		 try {	
//		    	//humanresources.newContract();
//		    	Contract cont= humanresources.newContract();
//		    	hae(cont.getContractNumber());
//			}catch (RuntimeException e) {System.err.println();}
}
		    

	   public void setHumanResources(HumanResources hr) {
		    	this.humanresources=hr;
	}
	  
	 
	 @FXML void handleSearch() {
//		   Dialogs.showMessageDialog("Ei osata viel‰ lis‰t‰");
	   }
	  
	 
	    /**
	     * Processing a save command
	     */
	    @FXML private void handleSave() {
	        tallenna();
	    }
	    
	    
	    @FXML void handleDelete() {
//	    	  Dialogs.showMessageDialog("Ei osata viel‰ lis‰t‰");
	    	deleteContract();
	    }

	    
	    @FXML   void handleEdit() {
//	    	  Dialogs.showMessageDialog("Ei osata viel‰ lis‰t‰");
	    	edit(1);   
	    }

	    
	    /**
	     * K‰sitell‰‰n lopetusk‰sky
	     */
	    @FXML private void handleExit() {
	        tallenna();
	        Platform.exit();
	    }
	    
	    
	    @FXML void handleFirst() {
//	    	 Dialogs.showMessageDialog("Ei osata viel‰ lis‰t‰");
	    	int n =0;
	    	n=chooserContracts.getSelectedIndex();  
	    	n =0;
	    	chooserContracts.setSelectedIndex(n);
  }

    
	    @FXML void handleLast() {
//	   	 Dialogs.showMessageDialog("Ei osata viel‰ lis‰t‰");   	
	    	int n =0;    	
	    	n=humanresources.getContracts()-1;
	    	chooserContracts.setSelectedIndex(n);
	    }

	    
	    @FXML void handleBack() {
	    	int n =0;
	    	n=chooserContracts.getSelectedIndex();
	    	n--;
	    	if (n<0) Dialogs.showMessageDialog("You are already in the first object");
	    	chooserContracts.setSelectedIndex(n);
	    }
	    

	    @FXML void handleNext() {
//	  	Dialogs.showMessageDialog("Ei osata viel‰ lis‰t‰");	
	    	int n =0;
	    	n=chooserContracts.getSelectedIndex();
	    	n++;
	    	chooserContracts.setSelectedIndex(n);
	    	
	    }

	    
	    /**
	     * Tarkistetaan onko tallennus tehty
	     * @return true jos saa sulkaa sovelluksen, false jos ei
	     */
	    public boolean voikoSulkea() {
	        tallenna();
	        return true;
	    }
	    
	    
	    private void edit(int k){
	    	 if ( contractKohdalla == null ) return; 
	    	 try {
	        Contract contract = ContractsDialogeController.kysyContract(null,contractKohdalla.clone(),k); 
	        if ( contract == null ) return; 
	        //System.out.println(contract.getContractNumber());
	        //System.out.println(contractKohdalla.getContractNumber());
	        humanresources.korvaaTaiLisaa(contract); 
	        hae(contract.getContractNumber());
	    	 } catch (CloneNotSupportedException e) { 
	    		            // 
	         } catch (SailoException e) { 
	    	 Dialogs.showMessageDialog(e.getMessage()); 
	    		 } 
//	   		ContractsDialogeController.kysyContract(null,contractKohdalla); 
	   }       	     
	    
	    
	    /**
	     * Tietojen tallennus
	     */
	    private String tallenna() {
	    	 try {
	             humanresources.tallenna();
	             return null;
	         } catch (SailoException ex) {
	             Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
	             return ex.getMessage();
	         }  
	    }

	    
	    @FXML private void handleSalaries() {
	    	ModalController.showModal(SalariesGUIController.class.getResource("SalariesGUIView.fxml"), "Salary", null, humanresources);
	    	/*
	    	  try {
	    		  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SalariesGUIView.fxml"));
	    		  Parent root1 = (Parent) fxmlLoader.load();
	    		  Stage stage = new Stage();
	    		  stage.setTitle("Salaries");
	    		  stage.setScene(new Scene(root1));  
	    		  stage.show();

	          } catch(Exception e) {
	             System.out.println("Can not load Window ");
	          }
	          */
	          
	     }
	    
	    
	    @FXML private void handleMainWindow() {
	    	// TODO: ModalController.closeStage(?? give here a component on your contract...fxml);
	    	
	    	ModalController.showModal(MainWindowGUIController.class.getResource("MainWindowGUIView.fxml"), "mainwindow", null, humanresources);
	    	
	    	/*
	    	try {
	    		  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindowGUIView.fxml"));
	    		  Parent root1 = (Parent) fxmlLoader.load();
	    		  Stage stage = new Stage();
	    		  stage.setTitle("Personal Information");
	    		  stage.setScene(new Scene(root1));  
	    		  stage.show();

	          } catch(Exception e) {
	             System.out.println("Can not load Window ");
	          }
	          */
	     }
	    
	    //===========================================================================================    
	    // T‰st‰ eteenp‰in ei k‰yttˆliittym‰‰n suoraan liittyv‰‰ koodia    
	    
	    private HumanResources humanresources;
//	    private TextArea areaContract = new TextArea();
	    private Contract contractKohdalla;
	    private static Contract apucontract = new Contract();
	    private TextField[] edits;
	    private int kentta = 0;
	          
	  
	    /**
	     * Ask the file name and read it
	     * @return true if successful, false if not
	     */
	     public boolean avaa() {
	          lueTiedosto("contracts.dat");
	           return true;
	        }
	    
	    
	     /**
		     * Ask the file name and read it
		     * @return true if successful, false if not
		     */
	         protected void newContract() {  
	        	  try {  
	                Contract uusi = new Contract();
	                uusi = ContractsDialogeController.kysyContract(null, uusi,1);    
	                if ( uusi == null ) return;
	                uusi.rekisteroi();       
	                humanresources.lisaa(uusi);  
	                hae(uusi.getContractNumber());
	             } catch (SailoException e) {
	                 Dialogs.showMessageDialog("It is not allowed to add more " + e.getMessage());
	                 return;
	             }
	               
//	               hae(uusi.getEmployeeNumber());
	         }
	         
	         
   /**
   † * Makes the other necessary initializations, now replaced with GridPane
   † * one large text field that can print contract's information.
   † * The list of Contracts list is also initialized
   † */
	    private void alusta() {
	    	
	    	cbKentat.clear();
	    	 for (int k = apucontract.ekaKentta(); k < apucontract.getKenttia(); k++) 
	         cbKentat.add(apucontract.getKysymys(k), null); 
	         cbKentat.getSelectionModel().select(0);
	              
    	chooserContracts.clear();	   	
   	    chooserContracts.addSelectionListener(e -> naytaContract());	
   	      	    
 	    edits = ContractsDialogeController.luoKentat(gridContract); 
   	    for (TextField edit: edits)  	   	
        if ( edit != null ) {  	   	
            edit.setEditable(false);  	   	
            edit.setOnMouseClicked(e -> { if ( e.getClickCount() > 1 ) edit(getFieldId(e.getSource(),0)); });  	   	
            edit.focusedProperty().addListener((a,o,n) -> kentta = getFieldId(edit,kentta));     	
 }   
	 
   	    
//	        panelContract.setContent(areaContract);
//	        areaContract.setFont(new Font("Courier New", 12));
//	        panelContract.setFitToHeight(true);
//	        
//	        chooserContracts.clear();
//	        chooserContracts.addSelectionListener(e -> naytaJasen());
//	        
//	        edits = new TextField [] {editContractNumber,editEmployeeNumber,editAnnualHolidays,
//	        		 editDateofStarting,editDateofexpirey,editDailyHours,editAdditionalTime,editContractperiod};
	    }
	    
	    
	    private void naytaVirhe(String virhe) {
	        if ( virhe == null || virhe.isEmpty() ) {
	            labelVirhe1.setText("");
	            labelVirhe1.getStyleClass().removeAll("virhe");
	            return;
	        }
	        labelVirhe1.setText(virhe);
	        labelVirhe1.getStyleClass().add("virhe");
	    }
	    	    
	 
		@SuppressWarnings("unused")
		private void setTitle(String title) {
	        ModalController.getStage(hakuehto).setTitle(title);
	    }
	    
	    
		/**
	    † * Displays the information of the selected contract of the list, temporarily in one large edit field
	    ††*/  
	    private void naytaContract() {
	    	contractKohdalla = chooserContracts.getSelectedObject();

	        if (contractKohdalla == null) {
	        	return;
	        }

	        ContractsDialogeController.naytaContract(edits, contractKohdalla);
	        
//	        areaContract.setText("");
//	        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaContract)) {
//	        	contractKohdalla.tulosta(os);
//	        }
	    }
	    
	    
	  /**
	   * Retrieves contract information from the list
	   * @param enro contract number activated after search
	   */
	    protected void hae(final int enr) {   	
	    	int enro = enr; // jnro j‰senen numero, joka aktivoidaan haun j‰lkeen
	    	 if ( enro <= 0 ) { 
	               Contract kohdalla = contractKohdalla; 
	               if ( kohdalla != null ) enro = kohdalla.getEmployeeNumber(); 
	          }
	    	    
	    	 int k = cbKentat.getSelectionModel().getSelectedIndex()+ apucontract.ekaKentta();
	         String ehto = hakuehto.getText(); 
	         if (ehto.indexOf('*') < 0) ehto = "*" + ehto + "*"; 

//	         if (k > 0 || ehto.length() > 0)
//	             naytaVirhe(String.format("Ei osata hakea (kentt‰: %d, ehto: %s)", k, ehto));
//	         else
//	             naytaVirhe(null);
	    	
	        
             chooserContracts.clear();
             int index = 0;
            
   	 // Collection<Contract> contracts;
   	  var contracts = humanresources.etsiContract(ehto, k);
         int i = 0;
         if (contracts.isEmpty() ) 
             naytaVirhe(String.format("Ei osata hakea (kentt‰: %d, ehto: %s)", k, ehto));
         else 
       	  naytaVirhe(null);
         for (Contract contract:contracts) {
             if (contract.getContractNumber() == enro) index = i;
             chooserContracts.add(contract.getContractNumbers(), contract);
             i++;
         }
   
         chooserContracts.setSelectedIndex(index); // t‰st‰ tulee muutosviesti joka n‰ytt‰‰ j‰senen
         
	         
	         
//	        chooserContracts.clear();
//	        int index = 0;
//	        for (int i = 0; i < humanresources.getContracts(); i++) {
//	            Contract contract = humanresources.annaContract(i);
//	            if (contract.getContractNumber() == enr) index = i;
//	            chooserContracts.add(contract.getContractNumbers(), contract);
//	        }
//	       chooserContracts.setSelectedIndex(index); // t‰st‰ tulee muutosviesti joka n‰ytt‰‰ j‰senen
	    }

	    
	    /**
	     * Formats the humaresource by reading it from a file of the selected name
	     * @param name file from which humanresource information is read
	     * @return null if successful, otherwise error text
	     */
	    protected String lueTiedosto(String c) {
	        //kerhonnimi = nimi;
	       // setTitle("Kerho - " + kerhonnimi);
	        try {
	            humanresources.lueTiedostosta(c);
	            hae(0);
	            return null;
	        } catch (SailoException e) {
	            hae(0);
	            String virhe = e.getMessage(); 
	            if ( virhe != null ) Dialogs.showMessageDialog(virhe);
	            return virhe;
	        }
	     }
	   
	    
	    /**
	     * Removes the selected contract from the list
	     */
	    private void deleteContract() {
	        Contract contract = contractKohdalla;
	        if ( contract == null ) return;
	        if ( !Dialogs.showQuestionDialog("Delete", "Are you sure you want yo delete Contract Number : " + contract.getContractNumber(), "Kyll‰", "Ei") )
	            return;
	        humanresources.poista1(contract);
	        int index = chooserContracts.getSelectedIndex();
	        hae(0);
	        chooserContracts.setSelectedIndex(index);
	    }
	    
	    
//
		@Override
		public HumanResources getResult() {
			// TODO Auto-generated method stub
			return humanresources;
		}


		@Override
		public void handleShown() {
			hae(0);		
		}


		@Override
		public void setDefault(HumanResources hr) {
			this.humanresources = hr;
//			naytaJasen();
		}
}