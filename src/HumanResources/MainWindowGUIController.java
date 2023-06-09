package HumanResources;


//import humanresourcesemployee.HumanResources;
import humanresourcesemployee.*;

import java.util.Collection;
import java.util.List; 
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import speciality.Speciality;
//import javafx.scene.control.TextArea;
//import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

/**
  * A class to manage events in the humanresource's interface.
  * 
  * @author Saad Turky
  * @version 11.05.2019
  * @version 25.03.2019 - Added Employees Processing
  * @version 28.04.2019 - Added Specialities Processing 
  */
public class MainWindowGUIController implements ModalControllerInterface<HumanResources>, Initializable {
	
	/**
     * Working on adding a new member
     */
	  @Override
	    public void initialize(URL url, ResourceBundle bundle) {
	        alusta();      
	    }

	     @FXML private TextField editEmployeeNumber;
	     @FXML private TextField editName; 
		 @FXML private TextField editPostalCode;	    
		 @FXML private TextField editPostalAddress;	    
		 @FXML private TextField editHourRealization;
	     @FXML private TextField editSalary;
	     @FXML private TextField editSurName;
	     @FXML private TextField editNationality;  
	     @FXML private TextField editDateofBirth;   
	     @FXML private TextField editPhoneNumber;
	     @FXML private TextField editSpeciality;   
	     @FXML private TextField editAddress;
	     @FXML private Label labelVirhe;
	     @FXML private Label labelVirhe1; //for the search
	     @FXML private ScrollPane panelEmployee;
         @FXML private ListChooser<Employee> chooserEmployees;
	     @FXML private ListChooser<Speciality> chooserSPeciality;
		 @FXML private StringGrid<Speciality> tableSpeciality;
		 @FXML private GridPane gridEmployee;
		 
				 
		    @FXML void handleAbout() {
		    	ModalController.showModal(SalariesGUIController.class.getResource("AboutView.fxml"), "About", null, humanresources);
		    }
		   
		 
		  @FXML private TextField hakuehto;
		  @FXML private ComboBoxChooser<String> cbKentat;
		    
		  
		  @FXML  void handleDeletespeciality() {
                deleteSpeciality();
		    }

		  
		   @FXML void handleHakuehto() {
//			   if ( employeeKohdalla != null )
//		            hae(employeeKohdalla.getEmployeeNumber()); 
			   hae(0);
		    }

		   
	  @FXML void handleSPeciality() {
		 newSpeciality();
	    }
	  
	   
	   @FXML void handleEditSpeciality() {
	     editSpeciality();
	    }

		
	   @FXML void handleSearch() {
//		   Dialogs.showMessageDialog("Ei osata viel� lis�t�");
	    }	
	
	   
    @FXML private void handleNew() { // inside new method newEmployee()    
    	newEmployee();
    	
//	try {	
////    	humanresources.newEmployee(); //this instruction causes adding two objects
//    	Employee emp= humanresources.newEmployee();
//    	hae(emp.getEmployeeNumber());
//	}catch (RuntimeException e) {System.err.println();}	
}
    
    
    /**
     * @param humanresource HumanResource used in this interface
     */
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
//    	  Dialogs.showMessageDialog("Ei osata viel� lis�t�");
    	deleteEmployee();
    }

    
    @FXML void handleEdit() {
    	  //Dialogs.showMessageDialog("Ei osata viel� lis�t�");    
    	edit(1);    	
    }

    
    /**
     * K�sitell��n lopetusk�sky
     */
    @FXML private void handleExit() {
        tallenna();
        Platform.exit();
    }
    
    
    @FXML void handleFirst() {
//    	 Dialogs.showMessageDialog("Ei osata viel� lis�t�");
    	int n =0;
    	n=chooserEmployees.getSelectedIndex();  
    	n =0;
    	chooserEmployees.setSelectedIndex(n);
    }
   
   
    @FXML void handleLast() {
//    	 Dialogs.showMessageDialog("Ei osata viel� lis�t�");   	
    	int n =0;    	
    	n=humanresources.getEmployees()-1;
    	chooserEmployees.setSelectedIndex(n);
    }

    
    @FXML void handleBack() {
    	int n =0;
    	n=chooserEmployees.getSelectedIndex();
    	n--;
    	if (n<0) Dialogs.showMessageDialog("You are already in the first object");
    	chooserEmployees.setSelectedIndex(n);
    }
    

    @FXML void handleNext() {
//  	Dialogs.showMessageDialog("Ei osata viel� lis�t�");	
    	int n =0;
    	n=chooserEmployees.getSelectedIndex();
    	n++;
    	chooserEmployees.setSelectedIndex(n);
    }

       
    /**
     * Tarkistetaan onko tallennus tehty
     * @return true jos saa sulkaa sovelluksen, false jos ei
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }
    
//    private void naytaInformaition(Employee employee) {
//    	editName.setText(employee.getName());
//    }
    
    
    private void naytaSpecialities(Employee employee) {
           tableSpeciality.clear();
           if ( employee == null ) return;
           
           List<Speciality> specialities = humanresources.annaSpecialities(employee);
		   if ( specialities.size() == 0 ) return;
		   for (Speciality spe: specialities)
		       naytaSpeciality(spe); 
       }
   
       
       private void naytaSpeciality(Speciality spe) {
           int kenttia = spe.getKenttia(); 
           String[] rivi = new String[kenttia-spe.ekaKentta()]; 
           for (int i=0, k=spe.ekaKentta(); k < kenttia; i++, k++) 
               rivi[i] = spe.anna(k); 
          tableSpeciality.add(spe,rivi);
       }
       
      
//       /**
//        * Tekee uuden tyhj�n harrastuksen editointia varten
//        */
//       private void uusiHarrastus() {
//           if ( jasenKohdalla == null ) return;
//           try {
//               Harrastus uusi = new Harrastus(jasenKohdalla.getTunnusNro());
//               uusi = TietueDialogController.kysyTietue(null, uusi, 0);
//               if ( uusi == null ) return;
//               uusi.rekisteroi();
//               kerho.lisaa(uusi);
//               naytaHarrastukset(jasenKohdalla); 
//              tableSpeciality.selectRow(1000);  // j�rjestet��n viimeinen rivi valituksi
//           } catch (SailoException e) {
//               Dialogs.showMessageDialog("Lis��minen ep�onnistui: " + e.getMessage());
//           }
//       }
    
    
       private void  editSpeciality() {
           int r = tableSpeciality.getRowNr();
           if ( r < 0 ) return; // klikattu ehk� otsikkorivi�
           Speciality spe = tableSpeciality.getObject();
           if ( spe == null ) return;
           int k = tableSpeciality.getColumnNr()+spe.ekaKentta();
           try {
               spe = SpecialityDialogeController.kysySpeciality(null, spe.clone(), k);
               if ( spe == null ) return;
               humanresources.korvaaTaiLisaa(spe); 
               naytaSpecialities(employeeKohdalla);            
               tableSpeciality.selectRow(r);  // j�rjestet��n sama rivi takaisin valituksi
           } catch (CloneNotSupportedException  e) { /* clone on tehty */  
           } catch (SailoException e) {
               Dialogs.showMessageDialog("Ongelmia lis��misess�: " + e.getMessage());
           }
       }     
              
       
    private void edit(int k){
    	 if ( employeeKohdalla == null ) return; 
    	 try {
        Employee employee = EmployeeDialogeController.kysyEmployee(null,employeeKohdalla.clone(),k); 
        if ( employee == null ) return; 
        humanresources.korvaaTaiLisaa(employee); 
        hae(employee.getEmployeeNumber());
    	 } catch (CloneNotSupportedException e) { 
    		            // 
         } catch (SailoException e) { 
    	 Dialogs.showMessageDialog(e.getMessage()); 
    		 } 
//   		EmployeeDialogeController.kysyEmployee(null,employeeKohdalla); 
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
     }
    
    
    @FXML private void handleContracts() {  	
    	ModalController.showModal(ContractsGUIController.class.getResource("ContractsGUIView.fxml"), "Contract", null, humanresources);	
     }
 

  @FXML void handleLogin() {
	 ModalController.showModal(LoginPageGUIController.class.getResource("LoginPageGUIView.fxml"), "Login", null, null);
   }
 
  //===========================================================================================    
 // T�st� eteenp�in ei k�ytt�liittym��n suoraan liittyv�� koodia    
    
    private HumanResources humanresources;
//    private TextArea areaEmployee = new TextArea();
    private Employee employeeKohdalla;
    private TextField[] edits;
    private static Speciality apuspeciality = new Speciality(); 
    private static Employee apuemployee = new Employee();
    private int kentta = 0; 
    
    
    /**
     * Ask the file name and read it
     * @return true if successful, false if not
     */
     public boolean avaa() {
         // uusinimi = KerhonNimiController.kysyNimi(null, kerhonnimi);
       // if (uusinimi == null) return false;
    	// ModalController.showModal(LoginPageGUIController.class.getResource("LoginPageGUIView.fxml"), "Login", null,humanresources);
          lueTiedosto("nimet.dat");
           return true;
        }
   
     
     /**
       * Create a new employee to start editing
       */
         protected void newEmployee() {   
        	  try {   
                Employee uusi = new Employee();
                uusi = EmployeeDialogeController.kysyEmployee(null, uusi,1);    
                if ( uusi == null ) return;
                System.out.println("I am here");
                uusi.rekisteroi();
                humanresources.lisaa(uusi); 
                hae(uusi.getEmployeeNumber());
             } catch (SailoException e) {
                 Dialogs.showMessageDialog("It is not allowed to add more " + e.getMessage());
                 return;
             }            
         }

  
  /**
   * Makes a new blank speciality for editing       
   */
  public void newSpeciality() {
	  
//	  if ( employeeKohdalla == null ) return;
//	        try {
//	            Speciality uusi = new Speciality(employeeKohdalla.getEmployeeNumber());
//	            uusi = EmployeeDialogeController.kysyEmployee(null, uusi, 0);
//	            if ( uusi == null ) return;
//	            uusi.rekisteroi();
//	            humanresources.lisaa(uusi);
//	            naytaSpeciality(employeeKohdalla); 
//	           tableSpeciality.selectRow(1000);  // j�rjestet��n viimeinen rivi valituksi
//	        } catch (SailoException e) {
//	            Dialogs.showMessageDialog("Lis��minen ep�onnistui: " + e.getMessage());
//	        }
//	    }
  
	  
	 if ( employeeKohdalla == null ) return;
//	 Speciality spe = humanresources.luoSpeciality(employeeKohdalla);
     Speciality spe = new Speciality();  
     spe.rekisteroi();  
     spe.vastaaPitsinNyplays(employeeKohdalla.getEmployeeNumber());  
     humanresources.lisaa(spe);  
     hae(employeeKohdalla.getEmployeeNumber());          	 
  }
  
  
   /**
     * Prints employee information
     * @param os data stream to be printed
     * @param share a printable employee
     */
    public void tulosta(PrintStream os, final Employee employee) {
        os.println("----------------------------------------------");
        employee.tulosta(os);
        os.println("----------------------------------------------");
        List<Speciality> specialities = humanresources.annaSpecialities(employee);   
        for (Speciality spe:specialities)
            spe.tulosta(os);  
    }

    
    /**
      * Prints the list of employees in the text area
      * @param text area to be printed
      */
    public void tulostaValitut(TextArea text) {
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(text)) {
            os.println("Tulostetaan kaikki employees");
            for (int i = 0; i < humanresources.getEmployees(); i++) {
            	Employee employee = humanresources.annaEmployee(i);
                tulosta(os, employee);
                os.println("\n\n");
            }
        }
    }

    
    /**
    � * Makes the other necessary initializations, now replaced with GridPane
    � * one large text field that can print employee's information.
    � * The list of Employees list is also initialized
    � */
    private void alusta() {    	
//        panelEmployee.setContent(areaEmployee);
//        areaEmployee.setFont(new Font("Courier New", 12));
//        panelEmployee.setFitToHeight(true);
    	
    	cbKentat.clear();
    	 for (int k = apuemployee.ekaKentta(); k < apuemployee.getKenttia(); k++) 
    	 cbKentat.add(apuemployee.getKysymys(k), null); 
    	 cbKentat.getSelectionModel().select(0); 
   	
        chooserEmployees.clear();
        chooserEmployees.addSelectionListener(e -> naytaEmployee());
        
       edits = EmployeeDialogeController.luoKentat(gridEmployee);
        for (TextField edit: edits)  
            if ( edit != null ) {  
                edit.setEditable(false);  
                edit.setOnMouseClicked(e -> { if ( e.getClickCount() > 1 ) edit(EmployeeDialogeController.getFieldId(e.getSource(),0)); });  
                edit.focusedProperty().addListener((a,o,n) -> kentta = EmployeeDialogeController.getFieldId(edit,kentta));  
            }    
        
        System.out.println("-------specialities----------");
        
     //initialize the speciality table titles
             int eka = apuspeciality.ekaKentta(); 
             int lkm = apuspeciality.getKenttia(); 
             String[] headings = new String[lkm-eka]; 
             for (int i=0, k=eka; k<lkm; i++, k++) headings[i] = apuspeciality.getKysymys(k); 
             tableSpeciality.initTable(headings); 
             tableSpeciality.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
             tableSpeciality.setEditable(false); 
             tableSpeciality.setPlaceholder(new Label("Ei viel� specialities"));           
             
          // This is still bad, not automatically changing if the fields are changed.
            tableSpeciality.setColumnSortOrderNumber(1); 
            tableSpeciality.setColumnSortOrderNumber(2); 
            tableSpeciality.setColumnWidth(1, 60); 
            tableSpeciality.setColumnWidth(2, 60); 
             
            tableSpeciality.setOnMouseClicked( e -> { if ( e.getClickCount() > 1 ) editSpeciality(); } );
            tableSpeciality.setOnKeyPressed( e -> {if ( e.getCode() == KeyCode.F2 ) editSpeciality();}); 
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
    
    
    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }
    
    
    /**
    � * Displays the information of the selected employee of the list, temporarily in one large edit field
    ��*/ 
    private void naytaEmployee() {
    	employeeKohdalla = chooserEmployees.getSelectedObject();
    	haeSpeciality(0);
    	naytaSpecialities(employeeKohdalla);
//    	naytaInformaition(employeeKohdalla);
       if (employeeKohdalla == null) return;{    	    
       }

       
      EmployeeDialogeController.naytaEmployee(edits, employeeKohdalla);
      

//       areaEmployee.setText("");
//      try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaEmployee)) {
//        	employeeKohdalla.tulosta(os);
//       }
    }
    
        
    /**
     * Retrieves employee information from the list
     * @param enro employee number activated after search
     */
    protected void hae(final int enr) {
    	int enro = enr; //jnro employee number activated after search
    	 if ( enro <= 0 ) { 
               Employee kohdalla = employeeKohdalla; 
               if ( kohdalla != null ) enro = kohdalla.getEmployeeNumber(); 
          }

    	 int k = cbKentat.getSelectionModel().getSelectedIndex()+ apuemployee.ekaKentta();;
         String ehto = hakuehto.getText(); 
         if (ehto.indexOf('*') < 0) ehto = "*" + ehto + "*"; 

         chooserEmployees.clear();
              int index = 0;
            
    	 // Collection<Employee> employees;
    	  var employees = humanresources.etsi(ehto, k);
          int i = 0;
          if (employees.isEmpty() ) 
              naytaVirhe(String.format("Ei osata hakea (kentt�: %d, ehto: %s)", k, ehto));
          else 
        	  naytaVirhe(null);
          for (Employee employee:employees) {
              if (employee.getEmployeeNumber() == enro) index = i;
              chooserEmployees.add(employee.getName(), employee);
              i++;
          }
    
         chooserEmployees.setSelectedIndex(index); // t�st� tulee muutosviesti joka n�ytt�� j�senen
          
      

         
         //the old code
//        chooserEmployees.clear();
//        int index = 0;
//        
//        for (int i = 0; i < humanresources.getEmployees(); i++) {
//            Employee employee = humanresources.annaEmployee(i);
//            if (employee.getEmployeeNumber() == enro) index = i;
//            chooserEmployees.add(employee.getName(), employee);
//        }
//        chooserEmployees.setSelectedIndex(index); // t�st� tulee muutosviesti joka n�ytt�� j�senen
    }

       
    /**
     * Retrieves employee's speciality information from the list
     * @param enro employee number activated after search
     */
    protected void haeSpeciality(int enro) {
    	
    	tableSpeciality.clear();
        int index = 0;
        
         List   <Speciality> speciality = humanresources.annaSpecialities(employeeKohdalla);
         for (Speciality spe:  speciality) 
//            if (employee.getEmployeeNumber() == enro) index = i;
        	 tableSpeciality.add(spe);
      
     //    tableSpeciality.setSelectedIndex(enro); // t�st� tulee muutosviesti joka n�ytt�� j�senen
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
            if ( virhe != null )
            	Dialogs.showMessageDialog(virhe);
            return virhe;
        }
     }
   
    
    /**
     * Removes the selected employee from the list
     */
    private void deleteEmployee() {
        Employee employee = employeeKohdalla;
        if ( employee == null ) return;
        if ( !Dialogs.showQuestionDialog("Poisto", "Poistetaanko j�sen: " + employee.getName(), "Kyll�", "Ei") )
            return;
        humanresources.poista(employee);
        int index = chooserEmployees.getSelectedIndex();
        hae(0);
        chooserEmployees.setSelectedIndex(index);
    }

    
    /**
      * Remove the speciality at the selected point in the speciality's table.
      */
      private void deleteSpeciality() {
          int rivi = tableSpeciality.getRowNr();
          if ( rivi < 0 ) return;
         Speciality speciality = tableSpeciality.getObject();
          if ( speciality == null ) return;
          humanresources.poistaSpeciality(speciality);
          naytaSpecialities(employeeKohdalla);
          int harrastuksia = tableSpeciality.getItems().size(); 
          if ( rivi >= harrastuksia ) rivi = harrastuksia -1;
          tableSpeciality.getFocusModel().focus(rivi);
          tableSpeciality.getSelectionModel().select(rivi);
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
//		naytaJasen();
	}
	
	
}
