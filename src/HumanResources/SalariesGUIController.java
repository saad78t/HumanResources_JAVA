package HumanResources;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.*;
import humanhesourcescontract.Contract;
import humanresourcesemployee.Employee;
import humanresourcesemployee.HumanResources;
import humanresourcesemployee.SailoException;
import humanresourcessalaries.Salary;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import speciality.Speciality;

/**
 * A class to manage events in the humanresource's salary.
 * 
 * @author Saad Turky
 * @version 11.05.2019
 * @version 25.03.2019 - Added Salaries Processing
 */
public class SalariesGUIController implements ModalControllerInterface<HumanResources>, Initializable {
	
	
//	   @FXML private ScrollPane panelSalary;    
	   @FXML private GridPane gridSalary;
	   @FXML private TextField editEmployeeNumber;
	   @FXML private TextField editContractNumber;
	   @FXML private TextField editMainsalary;
       @FXML private TextField editMOnth;   
	   @FXML private TextField editYear;
	   @FXML private TextField editBounce;
	   @FXML private TextField editLoans;
	   @FXML private TextField editTransallwance;
	   @FXML private TextField editNetSalary;
       @FXML private Label labelVirhe;
       @FXML private Label labelVirhe1; //for the search
	   @FXML private TextField hakuehto;
	   @FXML private ComboBoxChooser<String> cbKentat;

	
	   @FXML void handleHakuehto() {
//		   if ( salaryKohdalla != null )
//	            hae(salaryKohdalla.getEmployeeNumber()); 
		   hae(0);
	    }
     
	
	   /**
	     *  Working on adding a new salary
	     */
	   @Override
	    public void initialize(URL url, ResourceBundle bundle) {
	        alusta();      
	    }

	
	 @FXML private ScrollPane panelSalary;	  
	 @FXML private ListChooser<Salary> chooserSalaries;
	    

	 @FXML void handleSearch() {
//		   Dialogs.showMessageDialog("Ei osata viel‰ lis‰t‰");
	    }
	
	
	 @FXML private void handleNew() { // TODO: move inside a new method
		 newSalary();
//		 try {	
////		    	humanresources.newSalary();
//		    	Salary sal = humanresources.newSalary();
//		    	hae(sal.getEmployeeNumber());
//			}catch (RuntimeException e) {System.err.println();}
	 }
		    

	   public void setHumanResources(HumanResources hr) {
		    	this.humanresources=hr;
		    	}
	    
	  
	   /**
	     * Processing a save command
	     */
	    @FXML private void handleSave() {
	        tallenna();
	    }
	    
	    @FXML void handleDelete() {
//	    	  Dialogs.showMessageDialog("Ei osata viel‰ lis‰t‰");
	    	deleteSalary();
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
	    	n=chooserSalaries.getSelectedIndex();  
	    	n =0;
	    	chooserSalaries.setSelectedIndex(n);
	    }

	    	    
	    @FXML void handleLast() {
//	    	 Dialogs.showMessageDialog("Ei osata viel‰ lis‰t‰");
	    	int n =0;    	
	    	n=humanresources.getSalaries()-1;
	    	chooserSalaries.setSelectedIndex(n);
	    }

	    
	    @FXML void handleBack() {
//	    	 Dialogs.showMessageDialog("Ei osata viel‰ lis‰t‰");
	    	int n =0;
	    	n=chooserSalaries.getSelectedIndex();
	    	n--;
	    	chooserSalaries.setSelectedIndex(n);
	    }
	    

	    @FXML void handleNext() {
//	    	Dialogs.showMessageDialog("Ei osata viel‰ lis‰t‰");
	    	int n =0;
	    	n=chooserSalaries.getSelectedIndex();
	    	n++;
	    	chooserSalaries.setSelectedIndex(n);	    	
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
	    	 if ( salaryKohdalla == null ) return; 
	    	 try {
	        Salary salary = SalaryDialogeController.kysySalary(null,salaryKohdalla.clone(),k); 
	        if ( salary == null ) return; 
	        humanresources.korvaaTaiLisaa(salary); 
	        hae(salary.getEmployeeNumber());
	    	 } catch (CloneNotSupportedException e) { 
	    		            // 
	         } catch (SailoException e) { 
	    	 Dialogs.showMessageDialog(e.getMessage()); 
	    		 } 
//	   		EmployeeDialogeController.kysyEmployee(null,employeeKohdalla); 
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


	    
	    @FXML private void handleMainWindow() {
	    	
	    	ModalController.showModal(MainWindowGUIController.class.getResource("MainWindowGUIView.fxml"), "mainwindow", null, humanresources);
	    	
	     }
	    
	    
	    @FXML private void handleContracts() {
	    	
	    	ModalController.showModal(ContractsGUIController.class.getResource("ContractsGUIView.fxml"), "Contract", null, humanresources);
	    	
	    }
	    
	    //===========================================================================================    
	    // T‰st‰ eteenp‰in ei k‰yttˆliittym‰‰n suoraan liittyv‰‰ koodia    
	    
	    private HumanResources humanresources;
//	    private TextArea areaSalary = new TextArea();
	    private Salary salaryKohdalla;
	    private static Salary apusalary = new Salary();
	    private TextField[] edits;	    
	    private int kentta = 0; 
	    
	    
	    /**
	     * Ask the file name and read it
	     * @return true if successful, false if not
	     */
	     public boolean avaa() {
	         // uusinimi = KerhonNimiController.kysyNimi(null, kerhonnimi);
	       // if (uusinimi == null) return false;
	          lueTiedosto("salary.dat");
	           return true;
	        }
	     
	     /**
		   * Ask the file name and read it
		   * @return true if successful, false if not
		   */
	     protected void newSalary() {           
	    	 Salary uusi = new Salary();
             uusi = SalaryDialogeController.kysySalary(null, uusi,1);    
             if ( uusi == null ) return;
             uusi.rekisteroi();
           try {   
             humanresources.lisaa(uusi);              
          } catch (SailoException e) {
              Dialogs.showMessageDialog("It is not allowed to add more " + e.getMessage());
              return;
          }
            hae(uusi.getEmployeeNumber());
      }

	     
	     /**
	     † * Makes the other necessary initializations, now replaced with GridPane
	     † * one large text field that can print salary's information.
	     † * The list of salaries list is also initialized
	     † */	     
	    private void alusta() {
//	        panelSalary.setContent(areaSalary);
//	        areaSalary.setFont(new Font("Courier New", 12));
//        panelSalary.setFitToHeight(true);
        
        cbKentat.clear();
    	 for (int k = apusalary.ekaKentta(); k < apusalary.getKenttia(); k++) 
         cbKentat.add(apusalary.getKysymys(k), null); 
         cbKentat.getSelectionModel().select(0);
         
        chooserSalaries.clear();
        chooserSalaries.addSelectionListener(e -> naytaSalary());
        
        edits = SalaryDialogeController.luoKentat(gridSalary);
        for (TextField edit: edits)  
            if ( edit != null ) {  
                edit.setEditable(false);  
                edit.setOnMouseClicked(e -> { if ( e.getClickCount() > 1 ) edit(SalaryDialogeController.getFieldId(e.getSource(),0)); });  
                edit.focusedProperty().addListener((a,o,n) -> kentta = SalaryDialogeController.getFieldId(edit,kentta));  
            }
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
	    † * Displays the information of the selected salary of the list, temporarily in one large edit field
	    ††*/    
	    private void naytaSalary() {
	    	salaryKohdalla = chooserSalaries.getSelectedObject();

	        if (salaryKohdalla == null) {
	        	return;
	        }

	        SalaryDialogeController.naytaSalary(edits, salaryKohdalla);
//	        areaSalary.setText("");
//	        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaSalary)) {
//	        	salaryKohdalla.tulosta(os);
//	        }
	    }
	    
	    
	    /**
		   * Retrieves salary information from the list
		   * @param enro salary number activated after search
		   */
	    protected void hae(final int enr) {
	    	
    	int enro = enr; // jnro j‰senen numero, joka aktivoidaan haun j‰lkeen
    	 if ( enro <= 0 ) { 
               Salary kohdalla = salaryKohdalla; 
               if ( kohdalla != null ) enro = kohdalla.getEmployeeNumber(); 
          }
    	 	 
    	 int k = cbKentat.getSelectionModel().getSelectedIndex()+ apusalary.ekaKentta();
         String ehto = hakuehto.getText(); 
         if (ehto.indexOf('*') < 0) ehto = "*" + ehto + "*"; 

//         if (k > 0 || ehto.length() > 0)
//             naytaVirhe(String.format("Ei osata hakea (kentt‰: %d, ehto: %s)", k, ehto));
//         else
//             naytaVirhe(null);
	    	
         
	        chooserSalaries.clear();
	        int index = 0;
	        
	        var salaries = humanresources.etsiSalary(ehto, k);
	        System.out.println(ehto);
	         int i = 0;
	         
	         if (salaries.isEmpty() ) 
	              naytaVirhe(String.format("Ei osata hakea (kentt‰: %d, ehto: %s)", k, ehto));
	          else 
	        	  naytaVirhe(null);
	         
	         for (Salary salary:salaries) {
	             if (salary.getEmployeeNumber() == enro) index = i;
	             chooserSalaries.add(salary.getEmployeeNumbers(), salary);
	             i++;
	         }
	   
	         chooserSalaries.setSelectedIndex(index); // t‰st‰ tulee muutosviesti joka n‰ytt‰‰ j‰senen
	         
	         
//	        for (int i = 0; i <= humanresources.getSalaries(); i++) {
//	            Salary salary = humanresources.annaSalary(i);
//	            if (salary.getEmployeeNumber() == enro) index = i;
//	            chooserSalaries.add(salary.getEmployeeNumbers(), salary);
//	        }
//	       chooserSalaries.setSelectedIndex(index); // t‰st‰ tulee muutosviesti joka n‰ytt‰‰ j‰senen
	    }
	    
	    
	    /**
	     * Formats the humaresource by reading it from a file of the selected name
	     * @param name file from which humanresource information is read
	     * @return null if successful, otherwise error text
	     */
	    protected String lueTiedosto(String nimi) {
	        //kerhonnimi = nimi;
	       // setTitle("Kerho - " + kerhonnimi);
	        try {
	            humanresources.lueTiedostosta(nimi);
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
	     * Removes the selected salary from the list
	     */
	    private void deleteSalary() {
	        Salary salary = salaryKohdalla;
	        if ( salary == null ) return;
	        if ( !Dialogs.showQuestionDialog("Delete", "Are you sure you want yo delete Contract Number : " + salary.getEmployeeNumber(), "Kyll‰", "Ei") )
	            return;
	        humanresources.poista2(salary);
	        int index = chooserSalaries.getSelectedIndex();
	        hae(0);
	        chooserSalaries.setSelectedIndex(index);
	    }
	       
	    
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
		}
}
