
package humanresourcesemployee;
import static humanresourcesemployee.EmployNumberChecking.*;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Comparator;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.ohj2.Mjonot;

/**
 * Asking for employee information by creating a new dialogue for it
 * @author Saad Turky
 * @version 7.5.2019
 */
public class Employee implements Cloneable{
	
	
	/**
     * Returns the number of employee fields
     * @return Number of fields
     */
    public int getKenttia() {
        return 12;
    }


    /**
     * Eka kentt� joka on mielek�s kysytt�v�ksi
     * @return eknn kent�n indeksi
     */
    public int ekaKentta() {
        return 0;
    }
    
    
	public Employee() {
	//	
	}


	//private int tunnusNro;
    private static int seuraavaNro    = 1;
    
    private int        EmployeeNumber ;
    private String     FirstName      = "";
    private String     Surname        = "";
    private String     Nationality    = "";
    private String     DateofBirth    = "";
    private String     speciality     = "";
    private String     Address        = "";
    private String     PostCode       = "";
    private String     PostalAddress  = "";
    private int        Hourrealization = 0;
    private String        PhoneNumber    = "";
    private double     Salary         = 0;
   
    
    /** 
      * Comparison of Employees 
      */ 
     public static class Vertailija implements Comparator<Employee> { 
         private int k;  
          
         @SuppressWarnings("javadoc") 
         public Vertailija(int k) { 
             this.k = k; 
         } 
          
         @Override 
         public int compare(Employee employee1, Employee employee2) { 
             return employee1.getAvain(k).compareToIgnoreCase(employee2.getAvain(k)); 
//        	 return employee1.FirstName.compareToIgnoreCase(employee2.FirstName);
         } 
     } 
    
     
     /** 
       * Gives the contents of the k field as a string
       * @param k The contents of the param k field are restored
       * @return field content as a string
       */ 
      public String getAvain(int k) { 
          switch ( k ) { 
          case 0: return "" + EmployeeNumber; 
          case 1: return "" + FirstName.toUpperCase(); 
          case 2: return "" + Surname; // vaihda vuosi ja pvm kesken��n 
          case 3: return "" +  Nationality; 
          case 4: return "" + DateofBirth;                                 
          case 5: return "" + speciality;                                  
          case 6: return "" +  Address;                                   
          case 7: return "" + PostCode ;                                      
          case 8: return "" + PostalAddress ;                                    
//          case 9: return "" + String.format("%4d", liittymisvuosi);      
          case 9: return "" + Hourrealization;                                  
          case 10: return "" +PhoneNumber;                                       
          case 11: return "" + Salary  ;                                    
          default: return "���li�"; 
          } 
      } 
    
     
	  public static void main(String[] args) {
		Employee aku= new Employee();
		Employee aku2= new Employee();
		
		aku.rekisteroi();
		aku2.rekisteroi();
		
		aku.tulosta(System.out);
		aku2.tulosta(System.out);
		
		aku.vastaaAkuAnkka();
		aku2.vastaaAkuAnkka();
		aku.tulosta(System.out);
		aku2.tulosta(System.out);
		
	}
	
	
	  /**
	    * Provides the employee with the following registration number.
	    * @return new employee EmployeeNumber
	    * @example
	    * <pre name="test">
	    *   Employee aku1 = new Employee();
	    *   aku1.getEmployeeNumber() === 0;
	    *   aku1.rekisteroi();
	    *   Employee aku2 = new Employee();
	    *   aku2.rekisteroi();
	    *   int n1 = aku1.getEmployeeNumber();
	    *   int n2 = aku2.getEmployeeNumber();
	    *   n1 === n2-1;
	    * </pre>
	    */
	 public int rekisteroi() {
//		 if (EmployeeNumber!=0) return EmployeeNumber;
		 EmployeeNumber = seuraavaNro;
	        seuraavaNro++;
	        return EmployeeNumber;
	    }
	 
	 
	 /**
	   * Sets the employee number while ensuring that
	   * the next number is always higher than the highest ever.
	   * @param nr the identification number to be set
	   */
	 public void setEmployeeNumber(int nr){
		 EmployeeNumber = nr;
	        if (EmployeeNumber >= seuraavaNro) seuraavaNro = EmployeeNumber + 1;
	 }


	 public int getEmployeeNumber() {
	        return EmployeeNumber;
	    }

	 
    /**
     * Prints personal information
     * @param out data stream to be printed
     */
	 public void tulosta(PrintStream out) {
	        out.println(String.format("%03d", EmployeeNumber, 3) + "  " + EmployeeNumber + "  "
	                + FirstName+" "+ Surname+" "+ Nationality);
	        out.println("  " + DateofBirth + "  " + speciality + " " + Address);
	        out.println("  " + PostCode + "  " +PostalAddress+ " " + Hourrealization+
	        		 " "+" W:" + PhoneNumber+"  "+  Salary +" e." );
	 }
	 
	 
	 /**
	  * Prints personal information
	  * @param out data stream to be printed
	  */
	 public void tulosta(OutputStream os) {
	        tulosta(new PrintStream(os));
	    }
	 
	 
	  /**
	    * Gives the contents of the k field as a string
	    * @param k Multiple field content is restored
	    * @return field content as a string
	    */
	    public String anna(int k) {
	        switch ( k ) {
	        case 0: return "" +    EmployeeNumber ;
	        case 1: return "" +    FirstName      ;
	        case 2: return "" +    Surname        ;
	        case 3: return "" +    Nationality    ;
	        case 4: return "" +    DateofBirth    ;
	        case 5: return "" +    speciality     ;
	        case 6: return "" +    Address        ;
	        case 7: return "" +    PostCode       ;
	        case 8: return "" +    PostalAddress  ;
	        case 9: return "" +    Hourrealization;
	        case 10: return "" +   PhoneNumber    ;
	        case 11: return "" +   Salary         ;
	       
	        default: return "���li�";
	        }
	    }
	    
	       
	   /**
	     * Sets the value of the k string as the value of the string imported as a parameter
	     * @param k how many fields will be set
	     * @param The queue that is assigned to the field value
	     * @return null if the setup is successful, otherwise a corresponding error message.
	     * @example
	     * <pre name="test">
	     *   Employee employee = new Employee();
	     *   employee.aseta(1,"Ankka Aku") === null;
	     *   employee.aseta(2,"Saad") === null; 
	     *   employee.aseta(9,194xx) === "error";
	     * </pre>
	     */
	    public String aseta(int k, String jono) {
	        String tjono = jono.trim();
	        StringBuffer sb = new StringBuffer(tjono);
	        switch ( k ) {
	        case 0:
	            setEmployeeNumber(Mjonot.erota(sb, '�', getEmployeeNumber()));
	            return null;
	        case 1:
	            FirstName = tjono;
	            return null;
	        case 2:
	        	Surname = tjono;
	            return null;
	        case 3:                                                  
	        	Nationality = tjono;                                 
	            return null;                                       
	        case 4:                                                      
	            DateofBirth = tjono;                                   
	            return null;    
	        case 5:                                              
	             speciality = tjono;                                 
	            return null;    
	        case 6:                                              
	             Address = tjono;                                 
	            return null;                                            
	        case 7:
	        	PostCode  = tjono;
	            return null;
	        case 8:
	        	PostalAddress = tjono;
	            return null;
	        case 9:
             try {	        	
	        	 Hourrealization =Integer.valueOf(tjono ); 
	        	  return null;
	          } catch (Exception e) { return "Erorr, either you have entered a text in numeric field or real number instead of integer";}
	        		           
	        case 10:
	        	PhoneNumber = tjono; 
	            return null;
	        case 11:
	        try {	 
	        	Salary =Double.valueOf(tjono); 
	            return null;	      
	        } catch (Exception e) { return "Erorr, you have entered a text in a numeric field";}
	        default:
	            return "��li�";
	        }
	    }

	    
	 /**
	   * Returns the question corresponding to k's field
	   * @param k how many fields will be returned (0 start)
	   * @return Question corresponding to the field
	   */
	    
	    public String getKysymys(int k) {
	        switch ( k ) {        
	        case 0: return     "EmployeeNumber "  ;
	        case 1: return     "FirstName      "  ;
	        case 2: return     "Surname        "  ;
	        case 3: return     "Nationality    "  ;
	        case 4: return     "DateofBirth    "  ;
	        case 5: return     "speciality     "  ;
	        case 6: return     "Address        "  ;
	        case 7: return     "PostCode       "  ;
	        case 8: return     "PostalAddress  "  ;
	        case 9: return     "Hourrealization"  ;
	        case 10: return    "PhoneNumber    "  ;
	        case 11: return    "Salary         "  ;	      
	        default: return    "               "  ;	   
	        }                 
	    }
	    
	    	
	 public void vastaaAkuAnkka(int EmployeeNumber) {
	               EmployeeNumber = 0 + rand(1000,9999);
	               FirstName      = "Ankka";
	               Surname        = "Aku";
	               Nationality    ="Finnish";
	               DateofBirth    = "1.7.1950";
	               PhoneNumber    = "12-123245";
	               speciality     = "Engineer";
	               Address        = "Ankkakuja 6";
	               PostCode       = "12345";
	               PostalAddress  = "ANKKALINNA";
	               Hourrealization = 0;	             
	               Salary         = 200;
	    }

	 
	/**
	 * to get the first name
	 * @return first name
	 */
	public String getName() {
		return FirstName;	
	}
	 
	
	/**
	 * to set the first name
	 * @param FirstName to save the value of first name 
	 * @return null
	 */
	public String setName(String FirstName) {
		 this.FirstName=FirstName;
		 return null;
	}
	
	
//	public String setSurName(String S) {
//		 Surname=S;
//		 return null;
//	}
//	
//	
//	public String getSurName() {
//		return Surname;	
//	}
//	
//
//	public String setNationality(String n) {
//	      Nationality=n;
//		  return null;
//	}
//	
//	public String getNationality() {
//		return Nationality;	
//	}
//	
//	
//	public String setAddress(String a) {
//		Address = a;
//		return null;
//	}
//	
//	
//	  public String getAddress() {
//		return Address;
//	}


	public void vastaaAkuAnkka() { //arvoHetu=Id number
		 int EmployeeNumber = arvoHetu();
	     vastaaAkuAnkka(EmployeeNumber);
	}
	
	  
	  
   /**
     * Returns employee information as a string that can be saved to a file.
     * @return employee punctuated as a string
     * @example
     * <pre name="test">
     *   Employee employee = new Employee();
     *   employee.parse("   3  |  Ankka Aku   | 030201");
     *   employee.toString().startsWith("3|Ankka Aku|030201|") === true; // on enemm�kin kuin 3 kentt��, siksi loppu |
     * </pre>  
     */
	    @Override
	    public String toString() {
	    	 StringBuffer sb = new StringBuffer("");
	         String erotin = "";
	         for (int k = 0; k < getKenttia(); k++) {
	             sb.append(erotin);
	             sb.append(anna(k));
	             erotin = "|";
	         }
	         return sb.toString();
	         
	    	//the old code
//	        return "" +
//	                getEmployeeNumber() + "|" +
//	                FirstName + "|" +
//	                Surname + "|" +
//	                Nationality + "|" +
//	                DateofBirth + "|" +
//	                PhoneNumber + "|" +
//	                speciality  + "|" +
//	                Address + "|" +
//	                PostCode + "|" +
//	                PostalAddress + "|" +
//	                Hourrealization + "|" +
//	                Salary + "|" ;	              
	    }
	    
	    
	  /**
	     * Find out employee information | separated from the string
	     * Makes sure that nextNro is greater than the incoming EmployeeNumber.
	     * @param row from which employee information is taken
	     * @example
	     * <pre name="test">
	     *   Employee employee = new Employee();;
	     *   employee.parse("   3  |  Ankka Aku   | 030201");
	     *   employee.getEmployeeNumber() === 3;
	     *   employee.toString().startsWith("3|Ankka Aku|030201|") === true; // on enemm�kin kuin 3 kentt��, siksi loppu |
	     *   employee.rekisteroi();
	     *   int n = employee.getEmployeeNumber();
	     *   employee.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
	     *   employee.rekisteroi();           // ja tarkistetaan ett� seuraavalla kertaa tulee yht� isompi
	     *   employee.getEmployeeNumber() === n+20+1;    
	     * </pre>
	     */
	    public void parse(String rivi) {
	    	  StringBuffer sb = new StringBuffer(rivi);
	          for (int k = 0; k < getKenttia(); k++)
	              aseta(k, Mjonot.erota(sb, '|'));
	    	//the old code
//	        StringBuffer sb = new StringBuffer(rivi);
//	        setEmployeeNumber(Mjonot.erota(sb, '|', getEmployeeNumber()));
//	        FirstName = Mjonot.erota(sb, '|', FirstName);
//	        Surname = Mjonot.erota(sb, '|', Surname);
//	        Nationality = Mjonot.erota(sb, '|', Nationality);
//	        DateofBirth = Mjonot.erota(sb, '|', DateofBirth);
//	        PhoneNumber = Mjonot.erota(sb, '|', PhoneNumber);
//	        speciality = Mjonot.erota(sb, '|', speciality);
//	        Address = Mjonot.erota(sb, '|', Address);
//	        PostCode = Mjonot.erota(sb, '|', PostCode);
//	        PostalAddress = Mjonot.erota(sb, '|', PostalAddress);
//	        Hourrealization = Mjonot.erota(sb, '|', Hourrealization);
//	        Salary= Mjonot.erota(sb, '|', Salary);	        
	    }
	    
	    /**
	     * Examine whether the employee information is the same as the data of the imported employee
	     * @param the employee you are comparing to
	     * @return true if all the information is the same, false otherwise
	     * @example
	     * <pre name="test">
	     *   Employee employee1 = new Employee();
	     *   employee1.parse("   3  |  Ankka Aku   | 030201");
	     *   Employee employee2 = new Employee();
	     *   employee2.parse("   3  |  Ankka Aku   | 030201");
	     *   Employee employee3 = new Employee();
	     *   employee3.parse("   3  |  Ankka Aku   | 030202"); 
	     *   employee1.equals(employee2) === true;
	     *   employee2.equals(employee1) === true;
	     *   employee1.equals(employee3) === false;
	     *   employee3.equals(employee2) === false;
	     * </pre>
	     */
	    public boolean equals(Employee employee) {
	    	  if ( employee == null ) return false;
	          for (int k = 0; k < getKenttia(); k++)
	              if ( !anna(k).equals(employee.anna(k)) ) return false;
	          return true;
	    	//the old code
//	        if ( employee == null ) return false;
//	        return this.toString().equals(employee.toString());
	    }

	    @Override
	    public boolean equals(Object employee) {
	    	if ( employee == null ) return false;
	        return this.toString().equals(employee.toString());
	        
//	        if ( employee instanceof Employee ) return equals((Employee)employee);
//	        return false;
	    }
	    
	    
	    @Override
	    public int hashCode() {
	        return EmployeeNumber;
	    }
	    
	    
	    /**
	      * Make an identical clone of an employee
	      * @return Object cloned employee
	      * @example
	      * <pre name="test">
	      * #THROWS CloneNotSupportedException 
	      *   Employee employee = new Employee();
	      *   employee.parse("   3  |  Ankka Aku   | 123");
	      *   Employee kopio = employee.clone();
	      *   kopio.toString() === employee.toString();
	      *   employee.parse("   4  |  Ankka Tupu   | 123");
	      *   kopio.toString().equals(employee.toString()) === false;
	      * </pre>
	      */
	         @Override
	         public Employee clone() throws CloneNotSupportedException {
	             Employee New = (Employee) super.clone();
	             return New;
	         }


//		@Override
//		public int compareTo(Employee employee) {
//			return FirstName.compareTo(employee.FirstName);
//		}	    
	 }
