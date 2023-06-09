package humanhesourcescontract;
import static humanresourcesemployee.EmployNumberChecking.*;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Comparator;

import fi.jyu.mit.ohj2.Mjonot;
import humanresourcesemployee.Employee;


/**
 * Asking for contract information by creating a new dialogue for it
 * @author Saad Turky
 * @version 7.5.2019
 */
public class Contract implements Cloneable{
	
	private static int seuraavaNro    = 1;
	
    private int        ContractNumber ; // contract ID
    private int        EmployeeNumber ;
    private int        AnnualHolidays ;
    private String     DateOfStarting = "";
    private String     DateOfExpiry   = "";
    private int        DailyHours ;
    private int        AdditionalTime ;
    private String     ContractPeriod = "";
    
    
    /** 
     * Comparison of Employees
     */ 
    public static class Vertailija implements Comparator<Contract> { 
        private int k;  
         
        @SuppressWarnings("javadoc") 
        public Vertailija(int k) { 
            this.k = k; 
        } 
        
        
        @Override 
        public int compare(Contract contract1, Contract contract2) { 
            return contract1.getAvain(k).compareToIgnoreCase(contract2.getAvain(k)); 
//       	 return employee1.FirstName.compareToIgnoreCase(employee2.FirstName);
        } 
    } 
   
    
    /** 
     * Gives the contents of the k field as a string
     * @param k The contents of the param k field are restored
     * @return field content as a string
     */  
    public String getAvain(int k) { 
        switch ( k ) { 
        case 0: return "" + ContractNumber ;
        case 1: return "" + EmployeeNumber ;
        case 2: return "" + AnnualHolidays ;
        case 3: return "" + DateOfStarting ;
        case 4: return "" + DateOfExpiry   ;   
        case 5: return "" + DailyHours ;      
        case 6: return "" + AdditionalTime ;  
        case 7: return "" + ContractPeriod ;      
       default: return "���li�"; 
        } 
    } 
    
    
    public Contract() {
    	 //
    }
 
    
    /**
     * Returns the number of contract fields
     * @return Number of fields
     */   
     public int getKenttia() {       
         return 8;    
        }
     
     
     /**        
       * Eka kentt� joka on mielek�s kysytt�v�ksi     
       * @return eknn kent�n indeksi 
      */ 
    public int ekaKentta() { 
         return 0; 
   } 
    
    
      /**
	    * Gives the contents of the k field as a string
	    * @param k Multiple field content is restored
	    * @return field content as a string
	    */ 
      public String anna(int k) { 
         switch ( k ) { 
         case 0: return "" + ContractNumber; 
         case 1: return "" + EmployeeNumber; 
         case 2: return "" + AnnualHolidays; 
         case 3: return "" + DateOfStarting; 
         case 4: return "" + DateOfExpiry; 
         case 5: return "" + DailyHours; 
         case 6: return "" + AdditionalTime; 
         case 7: return "" + ContractPeriod; 
         
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
      *   Contract contract = new Contract(); 
      *   contract.aseta(1,1212) === null; 
      *   contract.aseta(2,"aa") === "error";
      *   contract.aseta(6,9) === null; 
      * </pre> 
      */ 
     public String aseta(int k, String jono) { 
        String tjono = jono.trim(); 
        StringBuffer sb = new StringBuffer(tjono);         
        switch ( k ) { 
        case 0: 
             setContractNumber(Mjonot.erota(sb, '�', getContractNumber())); 
            return null; 
        case 1: 
        	EmployeeNumber =Integer.valueOf(tjono);
             return null; 
       case 2: 
    	   try {
    	   AnnualHolidays=Integer.valueOf( tjono) ;    	   
            return null; 
    	   }catch (Exception e) {return "Error, you have entred a text in an Integer field or a double number in an Integer field";}
        case 3:                                             
        	DateOfStarting = tjono;                         
           return null;                                     
       case 4:                                              
    	   DateOfExpiry = tjono;                            
             return null;                                   
        case 5:  
        	try {
        	DailyHours =Integer.valueOf(tjono);             
            return null; 
        	  }catch (Exception e) {return "Error, you have entred a text in an Integer field or a double number in an Integer field";}
       case 6: 
    	   try {
    	   AdditionalTime =Integer.valueOf(tjono); 
             return null; 
    	   }catch (Exception e) {return "Error, you have entred a text in an Integer field or a double number in an Integer field";}
        case 7: 
        	ContractPeriod = tjono; 
            return null; 
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
       case 0: return "ContractNumber";               
       case 1: return "EmployeeNumber";               
       case 2: return "AnnualHolidays";               
       case 3: return "DateOfStarting";               
       case 4: return "DateOfExpiry";                 
       case 5: return "DailyHours";                   
       case 6: return "AdditionalTime";               
       case 7: return "ContractPeriod";                      
       default: return "";   
        } 
 } 
   
    
    public static void main(String[] args) {
		Contract aku= new Contract();
		Contract aku2= new Contract();
		
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
	  * Provides the contract with the following registration number.
	  * @return new contract ContractNumber
	  * @example
	  * <pre name="test">
	  *   Contract aku1 = new Contract();
	  *   aku1.getContractNumber() === 0;
	  *   aku1.rekisteroi();
	  *   Contract aku2 = new Contract();
	  *   aku2.rekisteroi();
	  *   int n1 = aku1.getContractNumber();
	  *   int n2 = aku2.getContractNumber();
	  *   n1 === n2-1;
	  * </pre>
	  */
	 public int rekisteroi() { // this one handles contract ID
//		 if (EmployeeNumber!=0) return EmployeeNumber;
		 ContractNumber = seuraavaNro;
	        seuraavaNro++;
	        return ContractNumber;
	    }
	 
	 
	 public int getEmployeeNumber() {
	        return EmployeeNumber;
	    }
	 

		public String getEmployeeNumbers() {
			return Integer.toString(EmployeeNumber);			
		}
		
	 public void tulosta(PrintStream out) {
	        out.println(String.format("%03d", EmployeeNumber, 3) + "  " + EmployeeNumber + "  "
	                + ContractNumber+" "+  AnnualHolidays+" "+ DateOfStarting);
	        out.println("  " + DateOfExpiry + "  " + DailyHours + " " + AdditionalTime + " "+ ContractPeriod);
	 }
	        
	        //why we need this method
	   	 public void tulosta(OutputStream os) {
	   	        tulosta(new PrintStream(os));
	   	    }
	   	 
	   	 
	   	public void vastaaAkuAnkka(int ContractNumber) {
            EmployeeNumber = rand(1000,9999) ;
            ContractNumber      = rand(1000,9999) ;
            AnnualHolidays      = 20;
            DateOfStarting      ="1.7.2019";
            DateOfExpiry        = "1.6.2020";
            DailyHours          = 8;
            AdditionalTime      = 10;
            ContractPeriod      = "One year";
}


	   	public void setContractNumber(int c) {
			ContractNumber=c;
		}
	   	
	   	
		public int getContractNumber() {
			return ContractNumber;
		}
		
		
		public String getContractNumbers() {
			return Integer.toString(ContractNumber);
			
		}
		
				
		public int getAnnualHolidays() {
			return AnnualHolidays;
		}


		public String setAnnualHolidays(int annualHolidays) {
			AnnualHolidays = annualHolidays;
			return null;
		}


		public String getDateOfStarting() {
			return DateOfStarting;
		}


		public String setDateOfStarting(String dateOfStarting) {
			DateOfStarting = dateOfStarting;
			return null;
		}


		public String getContractPeriod() {
			return ContractPeriod;
		}


		public String setContractPeriod(String contractPeriod) {
			ContractPeriod = contractPeriod;
			return null;
		}
		

		public String getDateOfExpiry() {
			return DateOfExpiry;
		}


		public String setDateOfExpiry(String dateOfExpiry) {
			DateOfExpiry = dateOfExpiry;
			return null;
		}


		public void vastaaAkuAnkka() {
			 int ContractNumber = arvoHetu();
		    vastaaAkuAnkka(ContractNumber);
		   }
		
		
		/**
	     * Returns contract information as a string that can be saved to a file.
	     * @return contract punctuated as a string
	     * @example
	     * <pre name="test">
	     *   Contract contract = new Contract();
	     *   contract.parse("1|1199|20|1.7.2019|1.6.2020|8|10|One");
	     *   contract.toString().startsWith("1|1199|20|1.7.2019|1.6.2020|8|10|One|") === true; // on enemm�kin kuin 3 kentt��, siksi loppu |
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
//	                getContractNumber() + "|" +
//	                //ContractNumber + "|" +
//	                EmployeeNumber + "|" +
//	                AnnualHolidays + "|" +
//	                DateOfStarting + "|" +
//	                DateOfExpiry + "|" +
//	                DailyHours + "|" +
//	                AdditionalTime   + "|" +
//	                ContractPeriod + "|" ;	                 	              
	    }
	    
	    
	   /**
	     * Find out contract information | separated from the string
	     * Makes sure that nextNro is greater than the incoming ContractNumber.
	     * @param row from which employee information is taken
	     * @example
	     * <pre name="test">
	     *   Contract contract = new Contract();;
	     *   contract.parse("1|1199|20|1.7.2019|1.6.2020|8|10|One");
	     *   contract.getContractNumber() === 3;
	     *   contract.toString().startsWith("1|1199|20|1.7.2019|1.6.2020|8|10|One|") === true; // on enemm�kin kuin 3 kentt��, siksi loppu |
	     *   contract.rekisteroi();
	     *   int n = contract.getContractNumber();
	     *   contract.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
	     *   contract.rekisteroi();           // ja tarkistetaan ett� seuraavalla kertaa tulee yht� isompi
	     *   contract.getContractNumber() === n+20+1;    
	     * </pre>
	     */
	    public void parse(String rivi) {	    	
	    	StringBuffer sb = new StringBuffer(rivi);
	          for (int k = 0; k < getKenttia(); k++)
	              aseta(k, Mjonot.erota(sb, '|'));
	          
	          //the old code
//	        StringBuffer sb = new StringBuffer(rivi);
//	        setContractNumber(Mjonot.erota(sb, '|', getContractNumber()));
//	        EmployeeNumber = Mjonot.erota(sb, '|', EmployeeNumber);
//	        AnnualHolidays = Mjonot.erota(sb, '|', AnnualHolidays);
//	        DateOfStarting = Mjonot.erota(sb, '|',  DateOfStarting);
//	        DateOfExpiry = Mjonot.erota(sb, '|',  DateOfExpiry);
//	        DailyHours = Mjonot.erota(sb, '|', DailyHours);
//	        AdditionalTime = Mjonot.erota(sb, '|', AdditionalTime);
	        //Address = Mjonot.erota(sb, '|', Address);        
	    }
	    
	    
	    /**
	     * Examine whether the contract information is the same as the data of the imported contract
	     * @param the contract you are comparing to
	     * @return true if all the information is the same, false otherwise
	     * @example
	     * <pre name="test">
	     *   Contract contract1 = new Contract();
	     *   contract1.parse("1|1199|20|1.7.2019|1.6.2020|8|10|One");
	     *   Contract contract2 = new Contract();
	     *   contract2.parse("1|1199|20|1.7.2019|1.6.2020|8|10|two");
	     *   Contract contract3 = new Contract();
	     *   contract3.parse("1|1199|20|1.7.2019|1.6.2020|8|10|three"); 
	     *   contract1.equals(contract2) === true;
	     *   contract2.equals(contract1) === true;
	     *   contract1.equals(contract3) === false;
	     *   contract3.equals(contract2) === false;
	     * </pre>
	     */
	    public boolean equals(Contract contract) {
	    	  if ( contract == null ) return false;
	          for (int k = 0; k < getKenttia(); k++)
	              if ( !anna(k).equals(contract.anna(k)) ) return false;
	          return true;
	    	//the old code
//	        if ( employee == null ) return false;
//	        return this.toString().equals(employee.toString());
	    }
	    
	    @Override
	    public boolean equals(Object contract) {
	        if ( contract == null ) return false;
	        return this.toString().equals(contract.toString());
//	    	if ( contract instanceof Contract ) return equals((Contract)contract);
//        return false;
	    }


	    @Override
	    public int hashCode() {
	        return ContractNumber;
	    }
	    
	    
	    /**
	      * Make an identical clone of a contract
	      * @return Object cloned contract
	      * @example
	      * <pre name="test">
	      * #THROWS CloneNotSupportedException 
	      *   Contract contract = new Contract();
	      *   contract.parse("1|1199|20|1.7.2019|1.6.2020|8|10|three");
	      *   Contract kopio = contract.clone();
	      *   kopio.toString() === contract.toString();
	      *   contract.parse("1|1199|20|1.7.2019|1.6.2020|8|10|four");
	      *   kopio.toString().equals(contract.toString()) === false;
	      * </pre>
	      */
     @Override
     public Contract clone() throws CloneNotSupportedException {
         Contract New = (Contract) super.clone();
         return New;
     } 
}
