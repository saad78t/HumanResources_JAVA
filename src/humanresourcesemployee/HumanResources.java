package humanresourcesemployee;

import java.io.File;
import java.util.*;

import HumanResources.LoginPageMain;
import fi.jyu.mit.fxgui.Dialogs;
import humanhesourcescontract.Contract;
import humanhesourcescontract.Contracts;
import humanresourcessalaries.Salaries;
import humanresourcessalaries.Salary;
import speciality.Specialities;
import speciality.Speciality;


/**
 * HumanResources class that takes care of the employees. Mainly all methods
 * are just "broker methods" for employees.
 *
 * @author Saad Turky
 * @version 1.0, 12.05.2019
 */
public class HumanResources {

private LoginPageMain login;
private Employees employees = new Employees();
private Contracts contracts= new Contracts();
private Salaries salaries = new Salaries();
private Specialities specialities = new Specialities(); 


     /**
       * Removes employee information from employees and specialities
       * @param employee who will be deleted
       * @return how many employees were removed
       * @example
       * <pre name="test">
       * #THROWS Exception
       *   humanresources.etsi("*",0).size() === 2;
       *   humanresources.annaSpecialities(aku1).size() === 2;
       *   humanresources.poista(aku1) === 1;
       *   humanresources.etsi("*",0).size() === 1;
       *   humanresources.annaSpecialities(aku1).size() === 0;
       *   humanresources.annaSpecialities(aku2).size() === 3;
       * </pre>
       */
      public int poista(Employee employee) {
          if ( employee == null ) return 0;
          int ret = employees.poista(employee.getEmployeeNumber()); 
          specialities.poistaEmployeeSpecialities(employee.getEmployeeNumber()); 
          return ret; 
      }

      
      public int poista1(Contract contract) {
          if ( contract == null ) return 0;
          int ret = contracts.poista1(contract.getEmployeeNumber());         
          return ret; 
      }


      public int poista2(Salary salary) {
          if ( salary == null ) return 0;
          int ret = salaries.poista2(salary.getEmployeeNumber());         
          return ret; 
      }
      
      
      /** 
        * Removes this speciality
        * @param speciality to remove speciality
        * @example
        * <pre name="test">
        * #THROWS Exception
        *   humanresources.annaSpecialities(aku1).size() === 2;
        *   humanresources.poistaSpeciality(pitsi11);
        *   humanresources.annaSpecialities(aku1).size() === 1;
        */ 
       public void poistaSpeciality(Speciality speciality) { 
           specialities.poista(speciality); 
       } 


  /**
   *  to show the number of employees
   * @return the number of employees
   */
	public int getEmployees() {
        return employees.getLkm();
    }
	
	
	/**
	 *  to show the number of contracts
	 * @return the number of contracts
	 */
	public int getContracts() {
        return contracts.getLkm();
	}
	
	
	/**
	 *  to show the number of salaries
	 * @return the number of salaries
	 */
	public int getSalaries() {
		return salaries.getLkm();
	}
	
	
	/**
	 *  to show the number of specialities
	 * @return the number of specialities
	 */
	public int getSpecialities() {
        return specialities.getLkm();
	}
	
	
	
	public Employee annaEmployee(int i) throws IndexOutOfBoundsException {
	        return employees.anna(i);
	}
	 
	 
	public Contract annaContract(int i) throws IndexOutOfBoundsException {
        return contracts.anna(i);
}
	
	
	public Salary annaSalary(int i) throws IndexOutOfBoundsException {
        return salaries.anna(i);
}
	
	
	
 public List<Speciality> annaSpecialities(Employee employee) {
	          return specialities.annaSpecialities(employee.getEmployeeNumber());
	 }

	 
	  public void lisaa(Employee employee) throws SailoException {
	        employees.lisaa(employee);
	 }
	  
	  
	  public void lisaa(Contract contract) throws SailoException {
	        contracts.lisaa(contract);
	 }
	  
	  
	  public void lisaa(Salary salary) throws SailoException {
	        salaries.lisaa(salary);
	 }
	
	
	  
	  public void lisaa(Speciality spe) {
		  specialities.lisaa(spe);
     }
	  
	
    /** 
      * Returns references in the "table" for employees matching the search criteria
      * @param hakuehto hakuehto  
      * @param k index of the field to be searched 
      * @return found employees of the data structure
      * @throws SailoException If something goes wrong
      * @example 
      * <pre name="test">
      *   #THROWS CloneNotSupportedException, SailoException
      *   Employee employee3 = new Employee(); employee3.rekisteroi();
      *   employee3.aseta(1,"Susi Sepe");
      *   humanresources.lisaa(employee3);
      *   Collection<Employee > loytyneet = humanresources.etsi("*Susi*",1);
      *   loytyneet.size() === 1;
      *   Iterator<Employee> it = loytyneet.iterator();
      *   it.next() == employee3 === true; 
      * </pre>
      */ 
     public Collection<Employee> etsi (String hakuehto, int k){ 
         return employees.etsi(hakuehto, k); 
     } 
     
     
     public Collection<Contract> etsiContract (String hakuehto, int k){ 
         return contracts.etsi(hakuehto, k); 
     } 
     
          
     public Collection<Salary> etsiSalary (String hakuehto, int k){ 
         return salaries.etsi(hakuehto, k); 
     } 
     
     
	  public void korvaaTaiLisaa(Employee employee) throws SailoException {
		  employees.korvaaTaiLisaa(employee);
	  }
	  
	  
	  public void korvaaTaiLisaa(Contract contract) throws SailoException {
		  contracts.korvaaTaiLisaa(contract);
	  }
	  
	  
	  public void korvaaTaiLisaa(Salary salary) throws SailoException {
		  salaries.korvaaTaiLisaa(salary);
	  }
	  
	  
	  public void korvaaTaiLisaa(Speciality speciality) throws SailoException {
		  specialities.korvaaTaiLisaa(speciality);
	  }
	  
	  
	  public void tallenna() throws SailoException { 
		          String virhe = ""; 
	    try { 
		              employees.tallenna(); 
		              contracts.tallenna(); 
		              salaries.tallenna();
		              specialities.tallenna();
          } catch ( SailoException ex ) {
              virhe = ex.getMessage(); 

          } 		   
		         // try { 
		              ///harrastukset.tallenna(); 
		         // } catch ( SailoException ex ) { 
		           //   virhe += ex.getMessage(); 
		        //  } 
		          if ( !"".equals(virhe) ) throw new SailoException(virhe); 
	  }
	  	  	  
	  
	  public void lueTiedostosta(String nimi) throws SailoException { 
	        //employees.lueTiedostosta(nimi); 
	        //harrastukset.lueTiedostosta(nimi); 
	  
	  		employees = new Employees(); // jos luetaan olemassa olevaan niin helpoin tyhjent�� n�in 
	  		contracts = new Contracts();
	  		salaries  = new Salaries();
	        specialities = new Specialities(); 
	       //  setTiedosto(nimi); 
	        employees.lueTiedostosta(); 
	        contracts.lueTiedostosta(); 
	        salaries.lueTiedostosta();
	        specialities.lueTiedostosta(); 
	    } 

		 
	  /** 
	     * Asettaa tiedostojen perusnimet 
	     * @param nimi uusi nimi 
	     */ 
	  /*
	     public void setTiedosto(String nimi) { 
	        File dir = new File(nimi); 
	        dir.mkdirs(); 
	        String hakemistonNimi = ""; 
	        if ( !nimi.isEmpty() ) hakemistonNimi = nimi ; 
	        employees.setTiedostonPerusNimi(hakemistonNimi + "nimet"); 
	        //harrastukset.setTiedostonPerusNimi(hakemistonNimi + "harrastukset"); 
	    }
*/
	   
	     
	public static void main(String[] args) {
		HumanResources humanresources = new HumanResources();
		Employee aku1 = new Employee(), aku2 = new Employee();
        aku1.rekisteroi();
        aku1.vastaaAkuAnkka();
        aku2.rekisteroi();
        aku2.vastaaAkuAnkka();
        
        try {
			humanresources.lisaa(aku1);
		    humanresources.lisaa(aku2);
			 
		    //Speclities classes
		    int id1 = aku1.getEmployeeNumber();
		    int id2 = aku2.getEmployeeNumber();
		    Speciality pitsi11 = new Speciality(id1); pitsi11.vastaaPitsinNyplays(id1); humanresources.lisaa(pitsi11);
		    Speciality pitsi12 = new Speciality(id1); pitsi12.vastaaPitsinNyplays(id1); humanresources.lisaa(pitsi12);
		    Speciality pitsi21 = new Speciality(id2); pitsi21.vastaaPitsinNyplays(id2); humanresources.lisaa(pitsi21);
		    Speciality pitsi22 = new Speciality(id2); pitsi22.vastaaPitsinNyplays(id2); humanresources.lisaa(pitsi22);
		    Speciality pitsi23 = new Speciality(id2); pitsi23.vastaaPitsinNyplays(id2); humanresources.lisaa(pitsi23);

		} catch (SailoException e) {
			 System.out.println("Error: "+e.getMessage());
			 
			 
//			   System.out.println("============= Employees test =================");
//			 for (int i = 0; i < humanresources.getEmployees(); i++) {
//		           Employee employee = humanresources.annaEmployee(i);
//		           System.out.println("Employee paikassa: " + i);
//		           employee.tulosta(System.out);
//		          
//		           
//		           //specialities class
//		            List<Speciality> loytyneet = humanresources.annaSpecialities(employee);
//			           for (Speciality speciality : loytyneet)
//			               speciality.tulosta(System.out);
//		       }

		}
       
        
        
        System.out.println("============= Employees test =================");

        for (int i = 0; i < humanresources.getEmployees(); i++) {
        	Employee employee = humanresources.annaEmployee(i);
            System.out.println("Employee place: " + i);
            employee.tulosta(System.out);
            
            
          //specialities class
            List<Speciality> loytyneet = humanresources.annaSpecialities(employee);
	           for (Speciality speciality : loytyneet)
	               speciality.tulosta(System.out);
        }        
        
}

	
	/**
     * Create a new employee to start editing
     */
    public Employee newEmployee() {
       return employees.newEmployee();
    }

    
    /**
     * Create a new contract to start editing
     */
	public Contract newContract() {
		return contracts.newContract();	
	} 
     
	
	/**
     * Create a new salary to start editing
     */
	public Salary newSalary() {
		return salaries.newSalary();	
	}

	   
//	  public void setLoginPageMain(LoginPageMain lo) {
//	    	this.login=lo;   	
//	    }
//	
	  
//	public Speciality newSpeciality() {
//		return specialities.newSpeciality();	
//	} 
}